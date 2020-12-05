package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pandemicspecial";
			String user = "root";
			String password = "";

			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			DataFromDatabase data = new DataFromDatabase(connection, statement, resultSet);

		} catch (Exception ex) {
			String className = this.getClass().getSimpleName();
			System.out.println(className + " DatabaseConnect " + " : " + ex);
			javax.swing.JOptionPane.showMessageDialog(null, "An error occured. App doesn't work");
		}
	}

	public void retrieveDataFromDatabase() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM pandemicspecial.subject");

			while (resultSet.next()) {
				System.out.println(resultSet.getString(2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void retrieveUsers() {
		
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.retrieveDataFromDatabase();
	}
}
