package database;

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

}
