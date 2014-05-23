package com.example.studentmoneymanagement;

import android.widget.TextView;

public class Wallet {

	double parentWalletMoney = 0.0;
	double expendatures = 0.0;
	boolean isWithinBudget = true;
	double parentBudget = 0.0;
	String budgetPlan = "";
	
	public Wallet(){}
	
	public Wallet(double d, double e){
		this.setParentWalletMoney(d);
		this.setParentBudget(e);
		budgetPlan = "default";
	}
	
	public Wallet(double d, double e, String bp){
		this.setBudgetPlan(bp);
		this.setParentWalletMoney(d);
		this.setParentBudget(e);
		
	}

	public double getExpendatures() {
		return expendatures;
	}



	public void setExpendatures(float expendatures) {
		this.expendatures = expendatures;
	}



	public double getParentWalletMoney() {
		return parentWalletMoney;
	}



	public void setParentWalletMoney(double d) {
		this.parentWalletMoney = d;
	}

	public void setBudgetPlan(String bp){
		budgetPlan = bp;
	}

	public boolean isWithinBudget() {
		
		if(parentWalletMoney >= parentBudget)
			return (isWithinBudget = true);
		else
			return (isWithinBudget = false);
	}



	public void setWithinBudget(boolean isWithinBudget) {
		this.isWithinBudget = isWithinBudget;
	}



	public double getParentBudget() {
		return parentBudget;
	}



	public void setParentBudget(double e) {
		this.parentBudget = e;
	}
}