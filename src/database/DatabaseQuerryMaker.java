package database;

import java.time.LocalDate;

public class DatabaseQuerryMaker {

	public DatabaseQuerryMaker() {
		
	}
	
	public String getRetrieveUserQuery() {
		
		StringBuilder userQ=new StringBuilder("SELECT * FROM pandemicspecial.user");
		return userQ.toString();
	}
	
	public String getAttemptedProblemsForUserQuery(int userID) {
		
		StringBuilder attemptedProblemsQ=new StringBuilder("SELECT attemptedproblems.id FROM attemptedProblems JOIN user ON (user.id = attemptedProblems.studentID or user.id = attemptedProblems.tutorID or user.id = attemptedProblems.moderatorID) WHERE user.id = ");
		attemptedProblemsQ.append(userID);
		
		return attemptedProblemsQ.toString();
	}

	public String getPreferredSubjectsQuery(int userID) {
		
		StringBuilder preferredSubjectsQ=new StringBuilder("SELECT subject.name FROM subject JOIN preferredsubjects ON (preferredsubjects.subjectID = subject.id) JOIN user ON (user.id = preferredsubjects.userID) WHERE userID = ");
		preferredSubjectsQ.append(userID);
		
		return preferredSubjectsQ.toString();
	}

	public String getStatisticsQuery(int userID) {
		
		StringBuilder statisticsQ=new StringBuilder("SELECT * FROM statistics WHERE statistics.userID = ");
		statisticsQ.append(userID);
		
		return statisticsQ.toString();
	}

	public String insertUserQuery(String userName, String password, String nationality, String email, String userType, LocalDate dateJoined)
	{
		StringBuilder userIQ=new StringBuilder("INSERT INTO pandemicspecial.user VALUES ('NULL', "+userName+",");
		int userTypeId;

		switch(userType) {
			case "Student":
				userTypeId =1;
				break;
			case "Tutor":
				userTypeId =2;
				break;
			default:
				userTypeId = 3;
		}
		userIQ.append(userTypeId);
		userIQ.append(","+password+","+nationality+","+email+",");
		userIQ.append(dateJoined.toEpochDay());
		userIQ.append(")");
		return userIQ.toString();
	}
   public String insertProposedProblemQuery(String name, String task,String solution,int proposerId,int popularity,int difficulty, int grade, String subjectName)
   {  //!!!Nu uita de proposerId
	   StringBuilder proposedProblemIQ=new StringBuilder("INSERT INTO pandemicspecial.problem VALUES ('NULL', "+name+","+task+","+solution+",");
	   proposedProblemIQ.append(proposerId);
	   proposedProblemIQ.append(",");
	   proposedProblemIQ.append(popularity);
	   proposedProblemIQ.append(",");
	   proposedProblemIQ.append(difficulty);
	   proposedProblemIQ.append(",");
	   proposedProblemIQ.append(grade);
	   proposedProblemIQ.append(",");
	   int subjectId;
	   switch(subjectName) {
		   case "Mathematics":
			   subjectId = 1;
			   break;
		   case "Physics":
			   subjectId = 2;
			   break;
		   case "ComputerScience":
			   subjectId = 3;
			   break;
		   case "English":
			   subjectId = 4;
			   break;
		   case "German":
			   subjectId = 5;
			   break;
		   case "Spanish":
			   subjectId = 6;
			   break;
		   case "French":
			   subjectId = 7;
			   break;
		   case "Chemistry":
			   subjectId = 8;
			   break;
		   case "Economy":
			   subjectId = 9;
			   break;
		   case "Biology":
			   subjectId = 10;
			   break;
		   case "Geography":
			   subjectId = 11;
			   break;
		   default:
			   subjectId = 12;
	   }
	   proposedProblemIQ.append(subjectId);
	   proposedProblemIQ.append(")");

	   return proposedProblemIQ.toString();
   }

   public String insertStudentSolutionQuery (int studentId, int problemId, String studentSolution, int tutorId, int moderatorId, String tutorFeedback,double tutorRating, String moderatorFeedback, double moderatorRating)
   {   //!!!Nu uita de id-uri
	   StringBuilder studentSolutionIQ=new StringBuilder("INSERT INTO pandemicspecial.attemptedproblems VALUES ('NULL',");
	   studentSolutionIQ.append(studentId);
	   studentSolutionIQ.append(",");
	   studentSolutionIQ.append(problemId);
	   studentSolutionIQ.append(","+studentSolution+",");
	   studentSolutionIQ.append(tutorId);
	   studentSolutionIQ.append(",");
	   studentSolutionIQ.append(moderatorId);
	   studentSolutionIQ.append(","+tutorFeedback+",");
	   studentSolutionIQ.append(tutorRating);
	   studentSolutionIQ.append(","+moderatorFeedback);
	   studentSolutionIQ.append(moderatorRating);
	   studentSolutionIQ.append(")");
	   return studentSolutionIQ.toString();
   }

   public String updateTutorQuery( int tutorId, String tutorFeedback, double tutorRating,int problemId)
   {
	   StringBuilder studentSolutionIQ=new StringBuilder("UPDATE pandemicspecial.attemptedproblems SET tutorID = ");
	   studentSolutionIQ.append(tutorId);
	   studentSolutionIQ.append(", tutorFeedback = "+tutorFeedback+", tutorRating = ");
	   studentSolutionIQ.append(tutorRating);
	   studentSolutionIQ.append(" WHERE problemID = ");
	   studentSolutionIQ.append(problemId);
	   studentSolutionIQ.append(";");
	   return studentSolutionIQ.toString();
   }

	public String updateModeratorQuery( int moderatorId, String  moderatorFeedback, double moderatorRating,int problemId)
	{
		StringBuilder studentSolutionIQ=new StringBuilder("UPDATE pandemicspecial.attemptedproblems SET  moderatorID = ");
		studentSolutionIQ.append( moderatorId);
		studentSolutionIQ.append(",  moderatorFeedback = "+ moderatorFeedback+",  moderatorRating = ");
		studentSolutionIQ.append( moderatorRating);
		studentSolutionIQ.append(" WHERE problemID = ");
		studentSolutionIQ.append(problemId);
		studentSolutionIQ.append(";");
		return studentSolutionIQ.toString();
	}

}
