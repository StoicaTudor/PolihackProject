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
import java.util.Set;

import com.sun.org.glassfish.external.statistics.Statistic;

import problem.Subject;
import sun.misc.GC;
import user.Statistics;
import user.Student;
import user.User;

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

		DatabaseQuerryMaker queryMaker = new DatabaseQuerryMaker();

		retrieveUsers(queryMaker);
	}

	private void retrieveUsers(DatabaseQuerryMaker queryMaker) {

		try {
			resultSet0 = statement0.executeQuery(queryMaker.getRetrieveUserQuery());

			while (resultSet0.next()) {

				int userID = resultSet0.getInt(1);
				String userName = resultSet0.getString(2);
				int userType = resultSet0.getInt(3);
				String password = resultSet0.getString(4);
				String nationality = resultSet0.getString(5);
				String email = resultSet0.getString(6);
				LocalDate dateJoined = LocalDate.ofEpochDay(resultSet0.getLong(7) / (1000 * 60 * 60 * 24));

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
					Set<String> preferredSubjectsString = new HashSet<String>();

					while (resultSet1.next()) {

						String preferredSubject = resultSet1.getString(1);
						preferredSubjectsString.add(preferredSubject);
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
						new ArrayList<Integer>(attemptedProblems), statistics, preferredSubjects);

				data.users.add(user); // add user to the great Set<User> users

				// testUser(user);

				System.gc(); // activate the garbage collector
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void retrieveSubjects() {

		// resultSet0 = statement0.
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.retrieveDataFromDatabase();
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

		System.out.println("------------------------------------------------------\n\n");
	}
}
