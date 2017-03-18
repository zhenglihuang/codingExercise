package com.company;

/**
 * Created by hzl on 3/17/2017.
 */
public class GetAllTransactionResponse {
    String error;
    Transaction[] transactions;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public GetAllTransactionResponse(){
    }
}
