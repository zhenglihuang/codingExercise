package com.company;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TransactionController {

    private static String IGNORE_DONUT = "--ignore-donuts";

    ObjectMapper mapper;
    public TransactionController(){
        mapper = new ObjectMapper();
    }

    public void processTransactions(Set<String> arguments) throws Exception {

        PostJsonHelper postJsonHelper = new PostJsonHelper();
        String jsonInString = postJsonHelper.postAllTransactions();

        GetAllTransactionResponse getAllTransactionResponse = null;
        try {
            getAllTransactionResponse = mapper.readValue(jsonInString, GetAllTransactionResponse.class);
        } catch (JsonMappingException ex) {
            System.out.println("Json mapping error:" + ex.toString());
        }

        List<MonthlyReport> monthlyReportList = generateMonthlyReport(getAllTransactionResponse.getTransactions(), arguments);
        Collections.sort(monthlyReportList, new Comparator<MonthlyReport>() {
            @Override
            public int compare(MonthlyReport o1, MonthlyReport o2) {
                if (o1.getYear() != o2.getYear()) {
                    return o1.getYear() - o2.getYear();
                } else {
                    return o1.getMonth() - o2.getMonth();
                }
            }
        });
        getReportResult(monthlyReportList, mapper);
    }

    private void getReportResult(List<MonthlyReport> monthlyReportList, ObjectMapper mapper){
        String line = "{";
        long spendSum = 0;
        long spendCount = 0;
        long incomeSum = 0;
        long incomeCount = 0;
        for (int i = 0; i< monthlyReportList.size(); ++i) {
            MonthlyReport report = monthlyReportList.get(i);
            String yearMonth = String.valueOf(report.getYear()) + "-" + (report.getMonth()<10 ? "0":"") + String.valueOf(report.getMonth());
            String spend = convertCentocentToDecimal(report.getSpending());
            String income = convertCentocentToDecimal(report.getIncome());

            spendSum += report.getSpending();
            spendCount++;
            incomeSum += report.getIncome();
            incomeCount++;

            line += String.format("{\"%s\": {\"spend\": \"%s\", \"income\": \"%s\"}", yearMonth, spend, income);
            System.out.println(line);
            line = "";
        }

        long spendAvg = spendSum/spendCount;
        long incomeAvg = incomeSum/incomeCount;
        String spendAvgStr = convertCentocentToDecimal(spendAvg);
        String incomeAvgStr = convertCentocentToDecimal(incomeAvg);

        line += String.format("{\"average\": {\"spend\": \"%s\", \"income\": \"%s\"}}", spendAvgStr, incomeAvgStr);
        System.out.println(line);
    }

    private String convertCentocentToDecimal(long centocents) {
        String res = "$";
        centocents /= 100;
        res += String.valueOf(centocents/100);
        res += ".";

        long fraction = centocents%100;
        if (fraction < 10) {
            res += "0";
        }
        res += String.valueOf(fraction);

        return res;
    }

    private List<MonthlyReport> generateMonthlyReport(Transaction[] transactionList, Set<String> arguments) {
        List<MonthlyReport> monthlyReportList = new ArrayList<>();
        HashMap<Integer, ArrayList<Long>> monthAmountListMap = new HashMap<>();
        for (Transaction transaction : transactionList) {
            if (ignoreTraction(transaction, arguments)){
                continue;
            }
            String yearMonth = transaction.transactionTime.substring(0, 7);
            int encodedYearMonth = encodeYearMonth(yearMonth);
            if (!monthAmountListMap.containsKey(encodedYearMonth)) {
                monthAmountListMap.put(encodedYearMonth, new ArrayList<>());
            }
            monthAmountListMap.get(encodedYearMonth).add(transaction.amount);
        }

        for (Map.Entry<Integer, ArrayList<Long>> entry : monthAmountListMap.entrySet()) {
            long debitSum = 0;
            long creditSum = 0;
            for (Long amount : entry.getValue()) {
                if (amount >= 0) {
                    creditSum += amount;
                } else {
                    debitSum -= amount;
                }
            }

            MonthlyReport report = new MonthlyReport(entry.getKey()/12, entry.getKey()%12+1, creditSum, debitSum);
            monthlyReportList.add(report);
        }

        return monthlyReportList;
    }

    private boolean ignoreTraction(Transaction transaction, Set<String> arguments) {
        return arguments.contains(IGNORE_DONUT) && transaction.amount > 0 &&
                (transaction.merchant.equalsIgnoreCase("Krispy Kreme Donuts") ||
                        transaction.merchant.equalsIgnoreCase("DUNKIN #336784"));
    }

    private int encodeYearMonth(String yearMonth) {
        if (yearMonth == null || yearMonth.length()!=7){
            return -1;
        }

        int year = Integer.valueOf(yearMonth.substring(0,4));
        int month = Integer.valueOf(yearMonth.substring(5,7));

        return year*12+month-1;
    }

}