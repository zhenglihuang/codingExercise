package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzl on 3/17/2017.
 */
public class Transaction {

    @JsonProperty("transaction-id")
    String transactionId;

    @JsonProperty("account-id")
    String accountId;
    @JsonProperty("raw-merchant")
    String rawMerchant;
    String merchant;

    @JsonProperty("is-pending")
    boolean isPending;
    @JsonProperty("aggregation-time")
    long aggregationTime;
    @JsonProperty("transaction-time")
    String transactionTime;
    long amount;
    @JsonProperty("previous-transaction-id")
    String previousTransactionId;
    String categorization;
    @JsonProperty("memo-only-for-testing")
    String memoOnlyForTesting;
    @JsonProperty("payee-name-only-for-testing")
    String payeeNameOnlyForTesting;
    @JsonProperty("clear-date")
    long clearDate;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRawMerchant() {
        return rawMerchant;
    }

    public void setRawMerchant(String rawMerchant) {
        this.rawMerchant = rawMerchant;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public boolean getIsPending() {
        return isPending;
    }

    public void setIsPending(boolean isPending) {
        this.isPending = isPending;
    }

    public long getAggregationTime() {
        return aggregationTime;
    }

    public void setAggregationTime(long aggregationTime) {
        this.aggregationTime = aggregationTime;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPreviousTransactionId() {
        return previousTransactionId;
    }

    public void setPreviousTransactionId(String previousTransactionId) {
        this.previousTransactionId = previousTransactionId;
    }

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }

    public String getMemoOnlyForTesting() {
        return memoOnlyForTesting;
    }

    public void setMemoOnlyForTesting(String memoOnlyForTesting) {
        this.memoOnlyForTesting = memoOnlyForTesting;
    }

    public String getPayeeNameOnlyForTesting() {
        return payeeNameOnlyForTesting;
    }

    public void setPayeeNameOnlyForTesting(String payeeNameOnlyForTesting) {
        this.payeeNameOnlyForTesting = payeeNameOnlyForTesting;
    }

    public long getClearDate() {
        return clearDate;
    }

    public void setClearDate(long clearDate) {
        this.clearDate = clearDate;
    }

    public Transaction(){
    }
}
