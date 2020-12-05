package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import sun.misc.GC;

public class Database {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private DataFromDatabase data;

	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pandemicspecial";
			String user = "root";
			String password = "";

			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			data = new DataFromDatabase(connection, statement, resultSet);

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
			resultSet = statement.executeQuery(queryMaker.getRetrieveUserQuery());

			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String userName = resultSet.getString(2);
				int userType = resultSet.getInt(3);
				String password = resultSet.getString(4);
				String nationality = resultSet.getString(5);
				String email = resultSet.getString(6);
				LocalDate dateJoined = LocalDate.ofEpochDay(resultSet.getLong(7) / (1000 * 60 * 60 * 24));
				
				data.user.add(new User());
				System.gc();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.retrieveDataFromDatabase();
	}
}
