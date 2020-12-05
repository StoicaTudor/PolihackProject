package user;

import problem.subject;

import java.util.ArrayList;

public class Student extends User{


	public Student(int id, String username, String password, String country, String email, ArrayList<Integer> attemptedProblems, Statistics statistics, ArrayList<subject> preferredSubjects) {
		super(id,username,password,country,email,attemptedProblems,statistics,preferredSubjects);

	}
	public void solveProblem(){

	}
	public void submitSolution(){

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
