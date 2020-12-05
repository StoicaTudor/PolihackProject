package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import problem.Subject;
import user.Moderator;
import user.Statistics;
import user.Student;
import user.Tutor;
import user.User;
import problem.Difficulty;
import problem.Problem;

public class DataFromDatabase {

	private Connection connection0;
	private Statement statement0;
	private ResultSet resultSet0;
	public Set<User> users = new HashSet<User>();
	public Set<Subject> subjects = new HashSet<Subject>();
	public Queue<String> problems;

	public DataFromDatabase(Connection connectio0n, Statement statement0, ResultSet resultSet0) {

		this.connection0 = connection0;
		this.statement0 = statement0;
		this.resultSet0 = resultSet0;
	}

	public void getData() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	protected User getPersonalizedUser(int userType, int userID, String userName, String password, String nationality,
			String email, ArrayList<Integer> attemptedProblems, Statistics statistics, Set<String> preferredSubjects) {
		System.out.println(preferredSubjects.size());
		switch (userType) {

		case 1: // student
			return new Student(userID, userName, password, nationality, email,
					new ArrayList<Integer>(attemptedProblems), statistics,
					convertStringSetToSubjectSet(preferredSubjects));

		case 2: // tutor
			return new Tutor(userID, userName, password, nationality, email, new ArrayList<Integer>(attemptedProblems),
					statistics, convertStringSetToSubjectSet(preferredSubjects));

		case 3: // moderator
			return new Moderator(userID, userName, password, nationality, email,
					new ArrayList<Integer>(attemptedProblems), statistics,
					convertStringSetToSubjectSet(preferredSubjects));

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

		return preferredSubjects;
	}

	public User validateSignIn(String username, String password) {

		for (User user : this.users) {

			if (user.getUsername() == username && user.getPassword() == password) {
				return user;
			}
		}

		return null;
	}

	public Difficulty getDifficulty(int difficulty) {

		switch (difficulty) {

		case 1:
			return Difficulty.EASY;

		case 2:
			return Difficulty.MEDIUM;

		case 3:
			return Difficulty.HARD;

		case 4:
			return Difficulty.IVAN;

		default:
			return Difficulty.EASY;
		}
	}

	// order all students by their Student -> User -> Statistics -> public double averageRating;
	public Set<Student> generateRanking(){
		// TO DO
	}
	
}
