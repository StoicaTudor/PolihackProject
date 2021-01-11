package database;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.time.LocalDate;

public class DatabaseQueryMaker {

	public DatabaseQueryMaker() {

	}

	public String getRetrieveUserQuery() {

		StringBuilder userQ = new StringBuilder("SELECT * FROM pandemicspecial.userr");
		return userQ.toString();
	}

	public String getAttemptedProblemsForUserQuery(int userID) {

		StringBuilder attemptedProblemsQ = new StringBuilder(
				"SELECT attemptedproblemss.id FROM attemptedProblemss JOIN userr ON (userr.id = attemptedProblemss.studentID or userr.id = attemptedProblemss.tutorID or userr.id = attemptedProblemss.moderatorID) WHERE userr.id = ");
		attemptedProblemsQ.append(userID);

		return attemptedProblemsQ.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public String getSentProblemsByTutor(int userID){
		StringBuilder string=new StringBuilder("SELECT * FROM problemm WHERE problemm.proposerID = ");
		string.append(userID);
		return string.toString();
	}
	public String getGivenFeedbackByTutor(int userID){
		StringBuilder string=new StringBuilder("SELECT * FROM attemptedproblemss WHERE attemptedproblemss.tutorID = ");
		string.append(userID);
		return string.toString();
	}
	public String getUserIDByUsername(String username){
		StringBuilder userIdQ=new StringBuilder(
				"SELECT userr.id FROM userr WHERE userName =  ");
		userIdQ.append("\"");
		userIdQ.append(username);
		userIdQ.append("\"");
		System.out.println(userIdQ.toString());
		return userIdQ.toString();
	}
	public String getPreferredSubjectsQuery(int userID) {

		StringBuilder preferredSubjectsQ = new StringBuilder(
				"SELECT subjectt.name FROM subjectt JOIN preferredsubjectss ON (preferredsubjectss.subjectID = subjectt.id) JOIN userr ON (userr.id = preferredsubjectss.userID) WHERE userID = ");
		preferredSubjectsQ.append(userID);

		return preferredSubjectsQ.toString();
	}

	public String getStatisticsQuery(int userID) {

		StringBuilder statisticsQ = new StringBuilder("SELECT * FROM statisticss WHERE statisticss.userID = ");
		statisticsQ.append(userID);

		return statisticsQ.toString();
	}

	public String getRetrieveSubjectsQuery() {

		return new String("SELECT subjectt.name FROM subjectt");
	}

	public String getRetrieveProblemsQuery() {

		return new String("SELECT * FROM problemm");
	}

	public String getRetrieveSubjectByIDQuery(int subjectID) {

		return new StringBuilder("SELECT subjectt.name FROM subjectt WHERE subjectt.id = ").append(subjectID).toString();
	}

	public String getRetrieveStudentsSolutionsQuery() {

		return new String("SELECT * FROM attemptedproblemss");
	}

	public String studentInsertQuery(String userName, int typeId, String password, String nationality, String email,
			LocalDate dateJoined, int grade) {

		StringBuilder studentIQ = new StringBuilder("INSERT INTO pandemicspecial.userr VALUES (NULL, " +'"' +userName + '"'+",");
		studentIQ.append(typeId);
		studentIQ.append("," + '"' + password + '"' + "," + '"' + nationality + '"' + "," + '"' + email + '"' + ",");
		long days = dateJoined.toEpochDay();
		studentIQ.append(days);
		studentIQ.append(",");
		studentIQ.append(grade);
		studentIQ.append(")");
		return studentIQ.toString();
	}

	public String proposedProblemInsertQuery(String name, String task, String solution, int proposerId,
			String difficulty, int grade, String subject) {
		StringBuilder problemIQ = new StringBuilder(
				"INSERT INTO pandemicspecial.problemm VALUES (NULL, " +"'"+ task +"'"+ "," +"'"+ name +"'"+ "," + "'"+solution +"'"+ ",");
		problemIQ.append(proposerId);
		problemIQ.append(",");
		problemIQ.append("'");
		problemIQ.append(difficulty);
		problemIQ.append("'");
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
		System.out.println(problemIQ.toString());
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

		StringBuilder solutionIQ = new StringBuilder("INSERT INTO pandemicspecial.attemptedproblemss VALUES (NULL, ");
		solutionIQ.append(studentId);
		solutionIQ.append(",");
		solutionIQ.append(problemId);
		solutionIQ.append("," +"'"+ studentSolution +"'"+ ",");
		solutionIQ.append(tutorId);
		solutionIQ.append(",");
		solutionIQ.append(moderatorId);
		solutionIQ.append("," +"'"+ tutorFeedback +"'"+ ",");
		solutionIQ.append(tutorRating);
		solutionIQ.append("," +"'"+ moderatorFeedback +"'"+ ",");
		solutionIQ.append(moderatorRating);
		solutionIQ.append(")");
		return solutionIQ.toString();
	}
	public String nextAttemptedProblemQuery(){
		return new String("SELECT * FROM attemptedproblemss WHERE tutorID=-1");
	}
	public String problemInfoForID(int problemID){
		StringBuilder string=new StringBuilder(
				"SELECT problemm.task,problemm.solution FROM problemm WHERE problemm.id="
		);
		string.append(problemID);
		return string.toString();
	}
	public String unReviewedProblemForId(int problemID){
		StringBuilder string = new StringBuilder(
				"SELECT attemptedproblemss.problemID,attemptedproblemss.studentSolution,attemptedproblemss.tutorFeedback,attemptedproblemss.tutorRating FROM attemptedproblemss WHERE id= "
		);
		string.append(problemID);
		return string.toString();
	}
	public String tutorFeedbackUpdateQuery(int problemID, int tutorID, String tutorFeedback, double tutorRating) {
		StringBuilder tutorFeedbackQ = new StringBuilder("UPDATE pandemicspecial.attemptedproblemss SET tutorID = ");
		tutorFeedbackQ.append(tutorID);
		tutorFeedbackQ.append(", tutorFeedback = " + "'"+tutorFeedback + "'"+", tutorRating = ");
		tutorFeedbackQ.append(tutorRating);
		tutorFeedbackQ.append("WHERE problemID = ");
		tutorFeedbackQ.append(problemID);
		tutorFeedbackQ.append(";");
		return tutorFeedbackQ.toString();
	}

	public String moderatorFeedbackUpdateQuery(int problemID, int moderatorID, String moderatorFeedback,
			double moderatorRating) {
		StringBuilder moderatorFeedbackQ = new StringBuilder(
				"UPDATE pandemicspecial.attemptedproblemss SET moderatorID = ");
		moderatorFeedbackQ.append(moderatorID);
		moderatorFeedbackQ.append(", moderatorFeedback = " + "'"+moderatorFeedback + "'"+", moderatorRating = ");
		moderatorFeedbackQ.append(moderatorRating);
		moderatorFeedbackQ.append("WHERE problemID = ");
		moderatorFeedbackQ.append(problemID);
		moderatorFeedbackQ.append(";");
		return moderatorFeedbackQ.toString();
	}

}
