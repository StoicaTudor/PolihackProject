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

		super(id, username, password, country, email, attemptedProblems, statistics, preferredSubjects);
		this.currentGrade = currentGrade;
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
	public Set<Problem> filterProblems(Set<Subject> filteredSubjects, int filterGrade, Difficulty filteredDifficulty) {
		// TO DO MIRUNA
		return null;
	}

}
