package user;

import problem.Difficulty;
import problem.Problem;
import problem.Subject;

import java.util.ArrayList;
import java.util.Set;

public class Student extends User {

	int currentGrade;

	public Student(int id, String username, String password, String country, String email,
			ArrayList<Integer> attemptedProblems, Statistics statistics, Set<Subject> preferredSubjects, int grade) {

		super(id, username, password, country, email, attemptedProblems, statistics, preferredSubjects, grade);
		this.currentGrade = grade;
	}

	public void solveProblem() {

	}

	public void submitSolution() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// initially, this method will be called by the student's preferred subjects and
	// student's grade
	// additionally, we MUST check for the problems which are NOT attempted
	public ArrayList<Problem> filterProblems(Set<String> filteredSubjects, int filterGrade, String filteredDifficulty) {
		// TO DO MIRUNA
		return null;
	}

}
