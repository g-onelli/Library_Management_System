package com.entityClasses;

public class patron {
    private int id;
    private String name;
    private String cardExpirationDate;
    private double balance;
    private String password;

    public patron() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public patron(int id, String name, String cardExpirationDate, double balance, String password) {
        super();
        this.id = id;
        this.name = name;
        this.cardExpirationDate = cardExpirationDate;
        this.balance = balance;
        this.password = password;
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
		return "patron [id=" + id + ", name=" + name + ", cardExpirationDate=" + cardExpirationDate + ", balance="
				+ balance + ", password=" + password + "]";
	}


}
