package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import user.User;

public class DataFromDatabase {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	public List<User> user=new ArrayList<>();

	public DataFromDatabase(Connection connection, Statement statement, ResultSet resultSet) {

		this.connection = connection;
		this.statement = statement;
		this.resultSet = resultSet;
	}
	
	public void getData() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
