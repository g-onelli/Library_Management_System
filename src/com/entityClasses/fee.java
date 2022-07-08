package com.entityClasses;

public class fee {

    private int id;
    private double total;
    private String datePaid;
    private String feeType;

    public fee(){
        super();
    }

    public fee(int id, double total, String datePaid, String feeType) {
        super();
        this.id = id;
        this.total = total;
        this.datePaid = datePaid;
        this.feeType = feeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Override
    public String toString() {
        return "fee{" +
                "id=" + id +
                ", total=" + total +
                ", datePaid='" + datePaid + '\'' +
                ", feeType='" + feeType + '\'' +
                '}';
    }
}
