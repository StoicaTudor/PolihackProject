package user;

import problem.Subject;

import java.util.ArrayList;
import java.util.Set;

public class Student extends User{


	public Student(int id, String username, String password, String country, String email, ArrayList<Integer> attemptedProblems, Statistics statistics, Set<Subject> preferredSubjects) {
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
