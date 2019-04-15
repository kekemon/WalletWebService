package com.kekemon.mywallet.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.json.simple.JSONObject;

@Entity
public class UserProfile {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	@Column
	private String mobile;
	private String password;
	private String accountName;
	private double currentAmmount;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Transaction> transactions;
	
	public UserProfile(){}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getCurrentAmmount() {
		return currentAmmount;
	}

	public void setCurrentAmmount(double currentAmmount) {
		this.currentAmmount = currentAmmount;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void makeTransction(double transctionAmmount, String toACNumber, String toACName) throws Exception{
		if(currentAmmount < transctionAmmount){
			throw new Exception("Insufficient balance.");
		}else{
			Transaction transaction = new Transaction();
			transaction.setAmmount(transctionAmmount);
			transaction.setTimeStamp(System.currentTimeMillis());
			transaction.setToACName(toACName);
			transaction.setToACNumber(toACNumber);
			transactions.add(transaction);
			setCurrentAmmount(currentAmmount-transctionAmmount);
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("mobile", getMobile());
		jsonObject.put("password", getPassword());
		jsonObject.put("accountName", getAccountName());
		jsonObject.put("currentAmmount", getCurrentAmmount());
		return jsonObject;
	}
}
