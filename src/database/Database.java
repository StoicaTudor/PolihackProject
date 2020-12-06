package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import problem.Problem;
import problem.Solution;
import problem.Subject;
import user.Statistics;
import user.User;
import problem.Difficulty;

public class Database {

	private Connection connection0;
	private Statement statement0, statement1;
	private ResultSet resultSet0, resultSet1;
	private DataFromDatabase data;

	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pandemicspecial";
			String user = "root";
			String password = "";

			connection0 = DriverManager.getConnection(url, user, password);
			statement0 = connection0.createStatement();
			statement1 = connection0.createStatement();

			data = new DataFromDatabase(connection0, statement0, resultSet0);

		} catch (Exception ex) {
			String className = this.getClass().getSimpleName();
			System.out.println(className + " DatabaseConnect " + " : " + ex);
			javax.swing.JOptionPane.showMessageDialog(null, "An error occured. App doesn't work");
		}
	}

	public void retrieveDataFromDatabase() {

		DatabaseQueryMaker queryMaker = new DatabaseQueryMaker();

		retrieveUsers(queryMaker);
		retrieveSubjects(queryMaker);
		retrieveProblems(queryMaker);
		retrieveStudentsSolutions(queryMaker);

		System.gc();
	}

	private void retrieveUsers(DatabaseQueryMaker queryMaker) {

		try {
			resultSet0 = statement0.executeQuery(queryMaker.getRetrieveUserQuery());

			while (resultSet0.next()) {

				int userID = resultSet0.getInt(1);
				String userName = resultSet0.getString(2);
				int userType = resultSet0.getInt(3);
				String password = resultSet0.getString(4);
				String nationality = resultSet0.getString(5);
				String email = resultSet0.getString(6);
				LocalDate dateJoined = LocalDate.ofEpochDay(resultSet0.getLong(7));
				int grade = resultSet0.getInt(8);

				List<Integer> attemptedProblems = new ArrayList<Integer>();
				Set<String> preferredSubjects = new HashSet<String>();
				Statistics statistics = new Statistics(0, 0, 0, 0, dateJoined);

				try { // retrieve list of attempted problems

					resultSet1 = statement1.executeQuery(queryMaker.getAttemptedProblemsForUserQuery(userID));

					while (resultSet1.next()) {

						int attemptedProblemID = resultSet1.getInt(1);
						attemptedProblems.add(new Integer(attemptedProblemID));
					}

				} catch (SQLException e) {

					e.printStackTrace();
				}

				try { // retrieve list of preferred subjects
					resultSet1 = statement1.executeQuery(queryMaker.getPreferredSubjectsQuery(userID));

					while (resultSet1.next()) {

						String preferredSubject = resultSet1.getString(1);
						preferredSubjects.add(preferredSubject);
					}

				} catch (SQLException e) {

					e.printStackTrace();
				}

				try { // retrieve statistics for user

					resultSet1 = statement1.executeQuery(queryMaker.getStatisticsQuery(userID));
					// System.out.println(userID);
					while (resultSet1.next()) {

						int nrSolvedProblems = resultSet1.getInt(3);
						int nrAttemptedProblems = resultSet1.getInt(4);
						int nrActiveHours = resultSet1.getInt(5);
						double averageRating = resultSet1.getDouble(6);

						statistics = new Statistics(nrAttemptedProblems, nrSolvedProblems, nrActiveHours, averageRating,
								dateJoined);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				User user = data.getPersonalizedUser(userType, userID, userName, password, nationality, email,
						new ArrayList<Integer>(attemptedProblems), statistics, preferredSubjects, grade);

				data.users.add(user); // add user to the great Set<User> users

				// testUser(user);

				System.gc(); // activate the garbage collector
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void retrieveSubjects(DatabaseQueryMaker queryMaker) {

		try {
			resultSet0 = statement0.executeQuery(queryMaker.getRetrieveSubjectsQuery());
			Set<String> preferredSubjects = new HashSet<String>();

			while (resultSet0.next()) {

				preferredSubjects.add(resultSet0.getString(1));
			}

			data.subjects = data.utility.convertStringSetToSubjectSet(preferredSubjects);

			// testSubjects(data.subjects);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.gc();
	}

	private void retrieveProblems(DatabaseQueryMaker queryMaker) {

		try {
			resultSet0 = statement0.executeQuery(queryMaker.getRetrieveProblemsQuery());
			Queue<Problem> problems = new PriorityQueue<Problem>();

			while (resultSet0.next()) {

				int problemID = resultSet0.getInt(1);
				String problemName = resultSet0.getString(2);
				String task = resultSet0.getString(3);
				String solution = resultSet0.getString(4);
				int proposerID = resultSet0.getInt(5);
				int popularity = resultSet0.getInt(6);
				Difficulty difficulty = Problem.getDifficultyInt(resultSet0.getInt(7));
				int grade = resultSet0.getInt(8);
				int subjectID = resultSet0.getInt(9);

				try {
					resultSet1 = statement1.executeQuery(queryMaker.getRetrieveSubjectByIDQuery(subjectID));
					resultSet1.next();
					String stringSubject = resultSet1.getString(1);

					problems.add(new Problem(problemName, problemID, task, solution, popularity, difficulty,
							data.utility.convertStringSubjectToEnumSubject(stringSubject), proposerID, grade));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

			data.problems = problems;

			// testProblems(data.problems);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.gc();
	}

	private void retrieveStudentsSolutions(DatabaseQueryMaker queryMaker) {

		try {
			resultSet0 = statement0.executeQuery(queryMaker.getRetrieveStudentsSolutionsQuery());
			ArrayList<Solution> solutions = new ArrayList<Solution>();

			while (resultSet0.next()) {

				int solutionID = resultSet0.getInt(1);
				int studentID = resultSet0.getInt(2);
				int problemID = resultSet0.getInt(3);
				String studentSolution = resultSet0.getString(4);
				int tutorID = resultSet0.getInt(5);
				int moderatorID = resultSet0.getInt(6);
				String tutorFeedback = resultSet0.getString(7);
				int tutorRating = resultSet0.getInt(8);
				String moderatorFeedback = resultSet0.getString(9);
				int moderatorRating = resultSet0.getInt(10);

				solutions.add(new Solution(solutionID, studentID, problemID, studentSolution, tutorID, moderatorID,
						tutorFeedback, tutorRating, moderatorFeedback, moderatorRating));
			}

			data.solutions = solutions;

			// testSolutions(data.solutions);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.gc();
	}

	public static void main(String[] args) {
		
		Database db = new Database();
		db.retrieveDataFromDatabase();
		
		HashSet<String> preferredSubjects=new HashSet<>();
		preferredSubjects.add("Mathematics");
		
		db.data.addNewUser(db.data.getPersonalizedUser(3, -1, "Ivan", "portocala", "Romania", "ivan@ivan.ivan", null,
				new Statistics(
						0, 0, 0, 0, LocalDate.now()), preferredSubjects, 12));
		
//		ArrayList<Problem> filteredProblems = db.data.getProblemsByFiltersFromStudentID("Mathematics", new Integer(7), "hard");
//		
//		for(Problem currentProblem : filteredProblems) {
//			System.out.println(currentProblem.getProblemID());
//		}
	}

	private void testUser(User user) {

		System.out.println("------------------------------------------------------");
		System.out.println(user.getUsername());
		System.out.println(user.getCountry());

		System.out.print("IDs of attemptedProblems:  ");
		for (int problemID : user.getAttemptedProblemsList()) {
			System.out.print(problemID + " ");
		}

		System.out.println();
		System.out.println("Preferred Subjects:\n");

		for (Subject currentSubject : user.getPreferredSubjects()) {
			System.out.println(currentSubject);
		}

		System.out.println();
		System.out.println("Statistics : ");

		System.out.print("Nr attempted problems: ");
		System.out.println(user.getStatistics().nrOfAttemptedProblems);

		System.out.print("Nr correct problems: ");
		System.out.println(user.getStatistics().nrOfCorrectProblems);

		System.out.print("Nr hours active: ");
		System.out.println(user.getStatistics().hoursActive);

		System.out.print("Average rating: ");
		System.out.println(user.getStatistics().averageRating);

		System.out.print("Date Joined: ");
		System.out.println(user.getStatistics().dateJoined);

		System.out.println();

		System.out.println("------------------------------------------------------");
	}

	private void testSubjects(Set<Subject> subjects) {

		for (Subject currentSubject : subjects) {

			System.out.println(currentSubject);
		}
	}

	private void testProblems(Queue<Problem> problems) {

		System.out.println("-----------------------------------");

		for (Problem currentProblem : problems) {

			System.out.println(currentProblem.getProblemID());
			System.out.println(currentProblem.getProblemName());
			System.out.println(currentProblem.getSolution());
			System.out.println(currentProblem.getPopularity());
			System.out.println(currentProblem.getProblemID());
			System.out.println(currentProblem.grade);
			System.out.println();
		}

		System.out.println("-----------------------------------");
	}

	private void testSolutions(ArrayList<Solution> solutions) {
		
		System.out.println("-----------------------------------");
		
		for (Solution currentSolution : solutions) {

			System.out.println(currentSolution.problemID);
			System.out.println(currentSolution.solutionID);
			System.out.println(currentSolution.studentID);
			System.out.println(currentSolution.studentSolution);
			System.out.println(currentSolution.tutorID);
			System.out.println(currentSolution.tutorFeedback);
			System.out.println(currentSolution.tutorRating);
			System.out.println(currentSolution.moderatorID);
			System.out.println(currentSolution.moderatorFeedback);
			System.out.println(currentSolution.moderatorRating);
			System.out.println();
		}
		
		System.out.println("-----------------------------------");
	}

}
