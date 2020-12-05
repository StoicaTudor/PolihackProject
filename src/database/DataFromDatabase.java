package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import problem.Subject;
import user.Moderator;
import user.Statistics;
import user.Student;
import user.Tutor;
import user.User;

public class DataFromDatabase {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	public Set<User> users = new HashSet<>();

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

	protected User getPersonalizedUser(int userType, int userID, String userName, String password, String nationality,
			String email, ArrayList<Integer> attemptedProblems, Statistics statistics, Set<String> preferredSubjects) {

		switch (userType) {

		case 1: // student
			return new Student(userID, userName, password, nationality, email, new ArrayList<Integer>(attemptedProblems),
					statistics, convertStringSetToSubjectSet(preferredSubjects));

		case 2: // tutor
			return new Tutor(userID, userName, password, nationality, email, new ArrayList<Integer>(attemptedProblems),
					statistics, convertStringSetToSubjectSet(preferredSubjects));

		case 3: // moderator
			return new Moderator(userID, userName, password, nationality, email, new ArrayList<Integer>(attemptedProblems),
					statistics, convertStringSetToSubjectSet(preferredSubjects));

		default:
			return null;
		}
	}

	protected Set<Subject> convertStringSetToSubjectSet(Set<String> subjectsListString) {

		Set<Subject> preferredSubjects = new HashSet<Subject>();

		for (String subject : subjectsListString) {

			switch (subject) {

			case "Mathematics":
				preferredSubjects.add(Subject.MATHEMATICS);
				break;

			case "Physics":
				preferredSubjects.add(Subject.PHYSICS);
				break;

			case "ComputerScience":
				preferredSubjects.add(Subject.COMPUTER_SCIENCE);
				break;

			case "English":
				preferredSubjects.add(Subject.ENGLISH);
				break;

			case "German":
				preferredSubjects.add(Subject.GERMAN);
				break;

			case "Spanish":
				preferredSubjects.add(Subject.SPANISH);
				break;

			case "French":
				preferredSubjects.add(Subject.FRENCH);
				break;

			case "Chemistry":
				preferredSubjects.add(Subject.CHEMISTRY);
				break;

			case "Economy":
				preferredSubjects.add(Subject.ECONOMY);
				break;

			case "Biology":
				preferredSubjects.add(Subject.BIOLOGY);
				break;

			case "Geography":
				preferredSubjects.add(Subject.GEOGRAPHY);
				break;

				
			case "History":
				preferredSubjects.add(Subject.HISTORY);
				break;

				
			default:

				break;
			}
		}

		return null;
	}
	
	public User validateSignIn(String username, String password) {
		
		for(User user : this.users) {
			
			if(user.getUsername() == username && user.getPassword() == password) {
				return user;
			}
		}
		
		return null;
	}

}
