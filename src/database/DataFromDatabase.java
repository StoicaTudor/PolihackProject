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

	int sessionUserID = -1;
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
			this.resultSet0 = this.statement0.executeQuery(queryMaker.studentInsertQuery(),
					statement0.RETURN_GENERATED_KEYS);

			if (resultSet0.next()) {
				int lastIDInserted = resultSet0.getInt(1);
				user.id = lastIDInserted;
			}

			this.users.add(user);

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
	public ArrayList<Problem> getProblemsByFiltersForStudentID(String filteredSubjects, Integer filterGrade,
			String filteredDifficulty) {

		ArrayList<Problem> filteredProblems = new ArrayList<>();

		for (Problem currentProblem : problems) {

			if (!solutions.contains(currentProblem) && currentProblem.grade == filterGrade
					&& currentProblem.getProblemDifficulty() == Problem.getDifficultyString(filteredDifficulty)
					&& currentProblem.getProblemSubject() == this.utility
							.convertStringSubjectToEnumSubject(filteredSubjects)) {

				filteredProblems.add(currentProblem);
			}
		}
		System.gc();
		return filteredProblems;
	}

	public ArrayList<Problem> getInitialListOfProblems() {

		User currentUser = null;
		Iterator<User> userIterator = users.iterator();

		while (userIterator.hasNext()) {

			User currentUserInIteration = userIterator.next();

			if (currentUserInIteration.id == this.sessionUserID) {

				currentUser = currentUserInIteration;
			}
		}

		ArrayList<Problem> initialProblems = new ArrayList<>();

		for (Problem currentProblem : problems) {

			if (!solutions.contains(currentProblem) && currentProblem.grade == currentUser.getGrade()) {

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
}
