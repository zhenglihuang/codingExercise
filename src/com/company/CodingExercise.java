package com.company;

import java.util.HashSet;
import java.util.Set;

public class CodingExercise {

    public static void main(String[] args) throws Exception {
        Set<String> arguments = new HashSet<>();
        for (int i = 0; i<args.length; ++i) {
            arguments.add(args[i]);
        }
        TransactionController transactionController = new TransactionController();
        transactionController.processTransactions(arguments);
    }

}