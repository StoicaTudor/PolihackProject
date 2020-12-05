package user;

import java.util.ArrayList;
import java.util.Set;

import problem.Subject;

public class Moderator extends Tutor{

	public Moderator(int id, String username, String password, String country, String email,
			ArrayList<Integer> attemptedProblems, Statistics statistics, Set<Subject> preferredSubjects) {

		super(id, username, password, country, email, attemptedProblems, statistics, preferredSubjects);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
