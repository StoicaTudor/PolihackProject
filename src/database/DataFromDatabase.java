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

	int sessionUserID; // Citadin=5 daca trb
	public Utility utility = new Utility();

	public DataFromDatabase(Connection connection0, Statement statement0, ResultSet resultSet0) {

		this.connection0 = connection0;
		this.statement0 = statement0;
		this.resultSet0 = resultSet0;
		this.queryMaker = new DatabaseQueryMaker();
	}


	public int getSessionUserID(String username) throws SQLException {
		this.statement0.execute(queryMaker.getUserIDByUsername(username));
		resultSet0= statement0.getResultSet();
		if(resultSet0.next()){
			sessionUserID=resultSet0.getInt(1);
		}

		return sessionUserID;
	}

	public static void main(String[] args) {

	}

	public User getPersonalizedUser(int userType, int userID, String userName,String fullName, String password, String nationality,
			String email, ArrayList<Integer> attemptedProblems, Statistics statistics, Set<String> preferredSubjects,
			int grade) {

		if (attemptedProblems == null) {
			attemptedProblems = new ArrayList<Integer>();
		}

		switch (userType) {

		case 1: // student
			return new Student(userID, userName,fullName, password, nationality, email,
					new ArrayList<Integer>(attemptedProblems), statistics,
					utility.convertStringSetToSubjectSet(preferredSubjects), grade);

		case 2: // tutor
			return new Tutor(userID, userName,fullName, password, nationality, email, new ArrayList<Integer>(attemptedProblems),
					statistics, utility.convertStringSetToSubjectSet(preferredSubjects), grade);

		case 3: // moderator

			return new Moderator(userID, userName,fullName, password, nationality, email,
					new ArrayList<Integer>(attemptedProblems), statistics,
					utility.convertStringSetToSubjectSet(preferredSubjects), grade);

		default:
			return null;
		}
	}

	public UserType validateSignIn(String username, String password) {

		for (User user : this.users) {

			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

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

//			System.out.println(queryMaker.studentInsertQuery(user.getUsername(), userType, user.getPassword(),
//					user.getCountry(), user.email, user.getStatistics().dateJoined, user.getGrade()));

			this.statement0.executeUpdate(
					queryMaker.studentInsertQuery(user.getUsername(), userType, user.getPassword(), user.getCountry(),
							user.email, user.getStatistics().dateJoined, user.getGrade()),
					statement0.RETURN_GENERATED_KEYS);

			resultSet0 = statement0.getGeneratedKeys();

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
//	currentProblem.getProblemDifficulty() == Problem.getDifficultyString(filteredDifficulty)
			if (!problemIsAttempted && currentProblem.grade == filterGrade
					&& currentProblem.getProblemDifficulty().equals(filteredDifficulty)
					&& currentProblem.getProblemSubject() == this.utility
							.convertStringSubjectToEnumSubject(filteredSubjects)) {

				filteredProblems.add(currentProblem);
			}
		}
		System.gc();
		return filteredProblems;
	}

	public User getUserByID(int sessionUserID) {

		User currentUser = null;
		Iterator<User> userIterator = users.iterator();

		while (userIterator.hasNext()) {

			User currentUserInIteration = userIterator.next();

			if (currentUserInIteration.id == sessionUserID) {

				return currentUserInIteration;
			}
		}

		return currentUser;
	}

	public ArrayList<Problem> getInitialListOfProblems() {
		
		if (true) {
			return new ArrayList(this.problems);
		}
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
				System.out.println(currentProblem.grade);
				for (Subject preferredSubjects : currentUser.getPreferredSubjects()) {

					if (currentProblem.getProblemSubject() == preferredSubjects) {
						initialProblems.add(currentProblem);
						break;
					}
				}

			}
		}

		for (Problem problem : initialProblems) {
			System.out.println(problem.getTask());
		}

		System.gc();
		return initialProblems;

	}

	public boolean isModerator() {
		System.out.println(sessionUserID);
		return (this.getUserByID(sessionUserID) instanceof Moderator);
	}

	public int getNrProposedProblemsByTutor(int sessionUserID) throws Exception{
		this.statement0.execute(queryMaker.getSentProblemsByTutor(sessionUserID));
		resultSet0= statement0.getResultSet();
		int nrProp=0;
		while(resultSet0.next())
			nrProp++;
		return nrProp;
	}
	public int getNrReviewedSolutionsByTutor(int sessionUserID)throws Exception{
		this.statement0.execute(queryMaker.getGivenFeedbackByTutor(sessionUserID));
		resultSet0= statement0.getResultSet();
		int nrRev=0;
		while(resultSet0.next())
			nrRev++;
		return nrRev;
	}
	public void insertProposedProblem(int sessionUserID,String problemName,String task,String solution,String difficulty,String subject,int grade)throws Exception{
		User currUser=getUserByID(sessionUserID);
		this.statement0.execute(queryMaker.proposedProblemInsertQuery(problemName,task,solution,sessionUserID,difficulty,grade,subject));
		resultSet0=statement0.getResultSet();
	}

	public void submitSolution(int problemID, String solution) {

		Solution studentSolution = new Solution(0, this.sessionUserID, problemID, solution, 0, 0, "", -1, "", -1);

		try {
			// insert solution into database
			this.statement0.executeUpdate(
					this.queryMaker.studentSolutionInsertQuery(studentSolution.studentID, studentSolution.problemID,
							studentSolution.studentSolution, studentSolution.tutorID, studentSolution.moderatorID,
							studentSolution.tutorFeedback, studentSolution.tutorRating,
							studentSolution.moderatorFeedback, studentSolution.moderatorRating),
					statement0.RETURN_GENERATED_KEYS);

			this.resultSet0 = statement0.getGeneratedKeys();

			if (resultSet0.next()) {
				studentSolution.solutionID = this.resultSet0.getInt(1);
			}
			// add solution to all time solutions list
			this.solutions.add(studentSolution);

			// now, search for the user who submitted the problem and add the solvedProblem
			// id to his/her list
			Iterator<User> userIterator = users.iterator();

			while (userIterator.hasNext()) { // iterate through users

				User currentUserInIteration = userIterator.next();

				if (currentUserInIteration.id == this.sessionUserID) { // if we identified the user

					this.users.remove(currentUserInIteration); // remove the user

					// update the user, by inserting the new attempted problem in the attempted
					// problems list
					currentUserInIteration.getAttemptedProblemsList().add(studentSolution.solutionID); // solutionID
																										// !!!!, not
																										// problemID

					// finally, add the user back in the data
					this.users.add(currentUserInIteration);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Problem> getAttemptedProblemsByUserID() {

		User currentUser = this.getUserByID(sessionUserID);
		// TODO
		return null;
	}
	public ArrayList<String>getAProblemToReviewForTutor()throws Exception{
		this.statement0.execute(queryMaker.unReviewedProblemForId(getNextUnrReviewedProblemId()));
		resultSet0=statement0.getResultSet();
		ArrayList<String>problem=new ArrayList<>();
		int probID=0;
		if(resultSet0.next()){
			problem.add(resultSet0.getString("studentSolution"));
			problem.add(resultSet0.getString("tutorFeedback"));
			problem.add(resultSet0.getString("tutorRating"));
			probID=Integer.parseInt(resultSet0.getString("problemID"));
		}
		this.statement0.execute(queryMaker.problemInfoForID(probID));
		resultSet0=statement0.getResultSet();
		if(resultSet0.next()){
			problem.add(resultSet0.getString("task"));
			problem.add(resultSet0.getString("solution"));
		}
		return problem;
	}
	public int getNextUnrReviewedProblemId()throws Exception{
		this.statement0.execute(queryMaker.nextAttemptedProblemQuery());
		resultSet0=statement0.getResultSet();
		int attemptedProbId;
		if (resultSet0.next()){
			attemptedProbId=Integer.parseInt(resultSet0.getString("id"));
			return attemptedProbId;
	}
	else return 0;
	}


	public void submitTutorFeedback(int tutorGrade, String tutorFeedback, int problemID, int userID) {

	}

//	public ArrayList<Solution> getSolvedProblemsForStudent(){
//		
//		for(Solution currentSolution : this.solutions) {
//			
////			if() {
////				
////			}
//		}
//	}
}
