package com.entityClasses;

public class patron {
    private int id;
    private String name;
    private String cardExpirationDate;
    private double balance;

    public patron(){
        super();
    }

    public patron(int id, String name, String cardExpirationDate, double balance) {
        super();
        this.id = id;
        this.name = name;
        this.cardExpirationDate = cardExpirationDate;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "patron{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardExpirationDate='" + cardExpirationDate + '\'' +
                ", balance=" + balance +
                '}';
    }
}
