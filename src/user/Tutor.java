package user;

<<<<<<< HEAD
import problem.subject;

import java.util.ArrayList;

public class Tutor extends User{

	private double monthlyIncome;
	public Tutor(int id, String username, String password, String country, String email, ArrayList<Integer> attemptedProblems, Statistics statistics, ArrayList<subject> preferredSubjects, double monthlyIncome) {
		super(id,username,password,country,email,attemptedProblems,statistics,preferredSubjects);
		this.monthlyIncome=monthlyIncome;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public void accomplishModeratorCriteria(){

	}
	public void giveFeedback(){

	}
	public void proposeProblem(){

	}
	public void calculateRevenue(){
=======

public class Tutor {
>>>>>>> main

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
