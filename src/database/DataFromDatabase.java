package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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

	private DatabaseQueryMaker queryMaker;
	private Connection connection0;
	private Statement statement0;
	private ResultSet resultSet0;

	public Set<User> users = new HashSet<User>();
	public Set<Subject> subjects = new HashSet<Subject>();
	public Queue<Problem> problems;
	public ArrayList<Solution> solutions;

	int sessionUserID = 5; // Citadin
	public Utility utility = new Utility();

	public DataFromDatabase(Connection connectio0n, Statement statement0, ResultSet resultSet0) {

		this.connection0 = connection0;
		this.statement0 = statement0;
		this.resultSet0 = resultSet0;
		this.queryMaker = new DatabaseQueryMaker();
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

		try {

			int userType = 1;

			if (user instanceof Student) {
				userType = 1;
			} else if (user instanceof Tutor) {
				userType = 2;
			} else if (user instanceof Moderator) {
				userType = 3;
			}

			this.resultSet0 = this.statement0.executeQuery(queryMaker.studentInsertQuery(user.getUsername(), userType,
					user.getPassword(), user.getCountry(), user.email, user.getStatistics().dateJoined));

			// first introduce new user into the database

			if (resultSet0.next()) {
				int lastIDInserted = resultSet0.getInt(1);
				user.id = lastIDInserted; // get inserted id
			}

			this.users.add(user); // add updated user into the users list

		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.gc();
	}

//	// order all students by their Student -> User -> Statistics -> public double
//	// averageRating;
//	public Set<Student> generateRanking() {
//		// TO DO
//	}
//
	public ArrayList<Problem> getProblemsByFiltersFromStudentID(String filteredSubjects, Integer filterGrade,
			String filteredDifficulty) {

		ArrayList<Problem> filteredProblems = new ArrayList<>();
		User currentUser = this.getUserByID(this.sessionUserID);

		for (Problem currentProblem : problems) { // for every problem

			boolean problemIsAttempted = false; // suppose it is not attempted

			for (Integer attemptedProblemID : currentUser.getAttemptedProblemsList()) { // check if it is attempted

				if (attemptedProblemID == currentProblem.getProblemID()) {
					problemIsAttempted = true;
					break;
				}
			}

			if (problemIsAttempted == false && currentProblem.grade == filterGrade
					&& currentProblem.getProblemDifficulty() == Problem.getDifficultyString(filteredDifficulty)
					&& currentProblem.getProblemSubject() == this.utility
							.convertStringSubjectToEnumSubject(filteredSubjects)) {

				filteredProblems.add(currentProblem);
			}
		}
		System.gc();
		return filteredProblems;
	}

	private User getUserByID(int sessionUserID) {

		User currentUser = null;
		Iterator<User> userIterator = users.iterator();

		while (userIterator.hasNext()) {

			User currentUserInIteration = userIterator.next();

			if (currentUserInIteration.id == this.sessionUserID) {

				return currentUserInIteration;
			}
		}

		return currentUser;
	}

	public ArrayList<Problem> getInitialListOfProblems() {

		User currentUser = this.getUserByID(sessionUserID);

		ArrayList<Problem> initialProblems = new ArrayList<>();

		for (Problem currentProblem : problems) {

			boolean problemIsAttempted = false;
			
			for (Integer attemptedProblemID : currentUser.getAttemptedProblemsList()) { // check if it is attempted

				if (attemptedProblemID == currentProblem.getProblemID()) {
					problemIsAttempted = true;
					break;
				}
			}

			if (problemIsAttempted == false && currentProblem.grade == currentUser.getGrade()) {

				for (Subject preferredSubjects : currentUser.getPreferredSubjects()) {

					if (currentProblem.getProblemSubject() == preferredSubjects) {
						initialProblems.add(currentProblem);
						break;
					}
				}

			}
		}

		System.gc();
		return initialProblems;

	}

	public boolean isModerator() {
		return (this.getUserByID(sessionUserID) instanceof Moderator);
	}
}
