package com.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hzl on 3/17/2017.
 */
public class MonthlyReport {
    private int year;
    private int month;
    private long spending;
    private long income;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public long getSpending() {
        return spending;
    }

    public long getIncome() {
        return income;
    }

    public MonthlyReport(final int year, final int month, final long spending, final long income) {
        this.year = year;
        this.month = month;
        this.spending = spending;
        this.income = income;
    }
}
