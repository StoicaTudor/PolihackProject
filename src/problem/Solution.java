package problem;

public class Solution {

	public int solutionID;
	public int studentID;
	public int problemID;
	public String studentSolution;
	public int tutorID;
	public int moderatorID;
	public String tutorFeedback;
	public int tutorRating;
	public String moderatorFeedback;
	public int moderatorRating;

	public Solution(int solutionID, int studentID, int problemID, String studentSolution, int tutorID, int moderatorID,
			String tutorFeedback, int tutorRating, String moderatorFeedback, int moderatorRating) {

		this.solutionID = solutionID;
		this.studentID = studentID;
		this.problemID = problemID;
		this.studentSolution = studentSolution;
		this.tutorID = tutorID;
		this.moderatorID = moderatorID;
		this.tutorFeedback = tutorFeedback;
		this.tutorRating=tutorRating;
		this.moderatorFeedback = moderatorFeedback;
		this.moderatorRating=moderatorRating;
	}
}
