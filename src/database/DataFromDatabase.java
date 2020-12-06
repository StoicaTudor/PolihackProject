package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import problem.Subject;
import user.Moderator;
import user.Statistics;
import user.Student;
import user.Tutor;
import user.User;
import user.UserType;
import problem.Problem;
import problem.Solution;

public class DataFromDatabase {

	private Connection connection0;
	private Statement statement0;
	private ResultSet resultSet0;

	public Set<User> users = new HashSet<User>();
	public Set<Subject> subjects = new HashSet<Subject>();
	public Queue<Problem> problems;
	public ArrayList<Solution> solutions;

	int sessionUserID = -1;
	public Utility utility = new Utility();

	public DataFromDatabase(Connection connectio0n, Statement statement0, ResultSet resultSet0) {

		this.connection0 = connection0;
		this.statement0 = statement0;
		this.resultSet0 = resultSet0;
	}

	public void getData() {

	}

	public static void main(String[] args) {

	}

	public User getPersonalizedUser(int userType, int userID, String userName, String password, String nationality,
			String email, ArrayList<Integer> attemptedProblems, Statistics statistics, Set<String> preferredSubjects,
			int grade) {

		switch (userType) {

		case 1: // student
			return new Student(userID, userName, password, nationality, email,
					new ArrayList<Integer>(attemptedProblems), statistics,
					utility.convertStringSetToSubjectSet(preferredSubjects), grade);

		case 2: // tutor
			return new Tutor(userID, userName, password, nationality, email, new ArrayList<Integer>(attemptedProblems),
					statistics, utility.convertStringSetToSubjectSet(preferredSubjects), grade);

		case 3: // moderator
			return new Moderator(userID, userName, password, nationality, email,
					new ArrayList<Integer>(attemptedProblems), statistics,
					utility.convertStringSetToSubjectSet(preferredSubjects), grade);

		default:
			return null;
		}
	}

	public UserType validateSignIn(String username, String password) {

		for (User user : this.users) {

			if (user.getUsername() == username && user.getPassword() == password) {

				this.sessionUserID = user.id;

				if (user instanceof Student) {
					return UserType.STUDENT;
				} else if (user instanceof Tutor) {
					return UserType.TUTOR;
				} else if (user instanceof Moderator) {
					return UserType.MODERATOR;
				}
			}
		}

		return UserType.NA;
	}

	public void addNewUser(User user) {

		/*
		 * !!!!!!!!!!!!!!!!!!!!!!!! TUDOR TODO BAGA IN DB IA ID USER SI BAGA IN
		 * VARIABILA DE CLASA USER (UPDATE LA ID USER PT CA E TRIMIS CA -1)
		 */
		try {
			this.resultSet0 = this.statement0.executeQuery("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.users.add(user);
	}

//	// order all students by their Student -> User -> Statistics -> public double
//	// averageRating;
//	public Set<Student> generateRanking() {
//		// TO DO
//	}
//
//	public ArrayList<Problem> getProblemsByFiltersForStudentID(Set<String> filteredSubjects, int filterGrade,
//			String filteredDifficulty) {
//
//	}

}
