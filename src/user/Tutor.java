package user;

import problem.Subject;

import java.util.ArrayList;
import java.util.Set;

public class Tutor extends User {
	private int sentTasks;
	private int feedbackGiven;
	public Tutor(int id, String username,String fullname, String password, String country, String email,
			ArrayList<Integer> attemptedProblems, Statistics statistics, Set<Subject> preferredSubjects, int grade) {

		super(id, username,fullname, password, country, email, attemptedProblems, statistics, preferredSubjects,grade);
	}

	public void accomplishModeratorCriteria() {

	}

	public void giveFeedback() {

	}

	public void proposeProblem() {

	}

	public void calculateRevenue() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
