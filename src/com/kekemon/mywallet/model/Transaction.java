package com.kekemon.mywallet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	@Column
	private double ammount;
	private String toACName;
	private String toACNumber;
	private long timeStamp;
	
	public Transaction(){}
	
	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public String getToACName() {
		return toACName;
	}

	public void setToACName(String toACName) {
		this.toACName = toACName;
	}

	public String getToACNumber() {
		return toACNumber;
	}

	public void setToACNumber(String toACNumber) {
		this.toACNumber = toACNumber;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
