package database;

import java.time.LocalDate;

public class DatabaseQueryMaker {

	public DatabaseQueryMaker() {

	}

	public String getRetrieveUserQuery() {

		StringBuilder userQ = new StringBuilder("SELECT * FROM pandemicspecial.user");
		return userQ.toString();
	}

	public String getAttemptedProblemsForUserQuery(int userID) {

		StringBuilder attemptedProblemsQ = new StringBuilder(
				"SELECT attemptedproblems.id FROM attemptedProblems JOIN user ON (user.id = attemptedProblems.studentID or user.id = attemptedProblems.tutorID or user.id = attemptedProblems.moderatorID) WHERE user.id = ");
		attemptedProblemsQ.append(userID);

		return attemptedProblemsQ.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getPreferredSubjectsQuery(int userID) {

		StringBuilder preferredSubjectsQ = new StringBuilder(
				"SELECT subject.name FROM subject JOIN preferredsubjects ON (preferredsubjects.subjectID = subject.id) JOIN user ON (user.id = preferredsubjects.userID) WHERE userID = ");
		preferredSubjectsQ.append(userID);

		return preferredSubjectsQ.toString();
	}

	public String getStatisticsQuery(int userID) {

		StringBuilder statisticsQ = new StringBuilder("SELECT * FROM statistics WHERE statistics.userID = ");
		statisticsQ.append(userID);

		return statisticsQ.toString();
	}

	public String getRetrieveSubjectsQuery() {

		return new String("SELECT subject.name FROM subject");
	}

	public String getRetrieveProblemsQuery() {

		return new String("SELECT * FROM problem");
	}

	public String getRetrieveSubjectByIDQuery(int subjectID) {

		return new StringBuilder("SELECT subject.name FROM subject WHERE subject.id = ").append(subjectID).toString();
	}

	public String getRetrieveStudentsSolutionsQuery() {

		return new String("SELECT * FROM attemptedproblems");
	}

	public String studentInsertQuery(String userName, int typeId, String password, String nationality, String email,
			LocalDate dateJoined) {
		StringBuilder studentIQ = new StringBuilder("INSERT INTO pandemicspecial.user VALUES (NULL, " + userName + ",");
		studentIQ.append(typeId);
		studentIQ.append("," + password + "," + nationality + "," + email + ",");
		long days = dateJoined.toEpochDay();
		studentIQ.append(days);
		studentIQ.append(")");
		return studentIQ.toString();
	}

	public String proposedProblemInsertQuery(String name, String task, String solution, int proposerId, int popularity,
			int difficulty, int grade, String subject) {
		StringBuilder problemIQ = new StringBuilder(
				"INSERT INTO pandemicspecial.problem VALUES (NULL, " + name + "," + task + "," + solution + ",");
		problemIQ.append(proposerId);// AICI AI DE REZOLVAT CU ID;
		problemIQ.append(",");
		problemIQ.append(popularity);
		problemIQ.append(",");
		problemIQ.append(difficulty);
		problemIQ.append(",");
		problemIQ.append(grade);
		problemIQ.append(",");
		int subjectID;
		switch (subject) {
		case "Mathematics":
			subjectID = 1;
			break;
		case "Physics":
			subjectID = 2;
			break;
		case "ComputerScience":
			subjectID = 3;
			break;
		case "English":
			subjectID = 4;
			break;
		case "German":
			subjectID = 5;
			break;
		case "Spanish":
			subjectID = 6;
			break;
		case "French":
			subjectID = 7;
			break;
		case "Chemistry":
			subjectID = 8;
			break;
		case "Economy":
			subjectID = 9;
			break;
		case "Biology":
			subjectID = 10;
			break;
		case "Geography":
			subjectID = 11;
			break;
		default:
			subjectID = 12;
		}
		problemIQ.append(subjectID);
		problemIQ.append(")");
		return problemIQ.toString();
	}

	public String studentSolutionInsertQuery(int studentId, int problemId, String studentSolution, int tutorId,
			int moderatorId, String tutorFeedback, double tutorRating, String moderatorFeedback,
			double moderatorRating) {
		tutorId = 0;
		moderatorId = 0;
		tutorFeedback = " ";
		tutorRating = 0.0;
		moderatorFeedback = " ";
		moderatorRating = 0.0;

		StringBuilder solutionIQ = new StringBuilder("INSERT INTO pandemicspecial.attemptedproblems VALUES (NULL, ");
		solutionIQ.append(studentId);
		solutionIQ.append(",");
		solutionIQ.append(problemId);
		solutionIQ.append("," + studentSolution + ",");
		solutionIQ.append(tutorId);
		solutionIQ.append(",");
		solutionIQ.append(moderatorId);
		solutionIQ.append("," + tutorFeedback + ",");
		solutionIQ.append(tutorRating);
		solutionIQ.append("," + moderatorFeedback + ",");
		solutionIQ.append(moderatorRating);
		solutionIQ.append(")");
		return solutionIQ.toString();
	}

	public String tutorFeedbackUpdateQuery(int problemID, int tutorID, String tutorFeedback, double tutorRating) {
		StringBuilder tutorFeedbackQ = new StringBuilder("UPDATE pandemicspecial.attemptedproblems SET tutorID = ");
		tutorFeedbackQ.append(tutorID);
		tutorFeedbackQ.append(", tutorFeedback = " + tutorFeedback + ", tutorRating = ");
		tutorFeedbackQ.append(tutorRating);
		tutorFeedbackQ.append("WHERE problemID = ");
		tutorFeedbackQ.append(problemID);
		tutorFeedbackQ.append(";");
		return tutorFeedbackQ.toString();
	}

	public String moderatorFeedbackUpdateQuery(int problemID, int moderatorID, String moderatorFeedback,
			double moderatorRating) {
		StringBuilder moderatorFeedbackQ = new StringBuilder(
				"UPDATE pandemicspecial.attemptedproblems SET moderatorID = ");
		moderatorFeedbackQ.append(moderatorID);
		moderatorFeedbackQ.append(", moderatorFeedback = " + moderatorFeedback + ", moderatorRating = ");
		moderatorFeedbackQ.append(moderatorRating);
		moderatorFeedbackQ.append("WHERE problemID = ");
		moderatorFeedbackQ.append(problemID);
		moderatorFeedbackQ.append(";");
		return moderatorFeedbackQ.toString();
	}

}
