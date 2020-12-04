package problem;

public class Solution {
    private int solutionID;
    private int problemID;
    private int studentID;
    private int tutorID;
    private int moderatorID;
    private double problemRating;
    private String studentSolution;
    private String feedback;

    //CONSTRUCTOR (standard)
    public Solution(int solutionID, int problemID, int studentID, int tutorID, int moderatorID, double problemRating, String studentSolution, String feedback) {
        this.solutionID = solutionID;
        this.problemID = problemID;
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.moderatorID = moderatorID;
        this.problemRating = problemRating;
        this.studentSolution = studentSolution;
        this.feedback = feedback;

    }

    //GETTER & SETTER
    public int getSolutionID() {
        return solutionID;
    }

    public void setSolutionID(int solutionID) {
        this.solutionID = solutionID;
    }

    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public int getModeratorID() {
        return moderatorID;
    }

    public void setModeratorID(int moderatorID) {
        this.moderatorID = moderatorID;
    }

    public double getProblemRating() {
        return problemRating;
    }

    public void setProblemRating(double problemRating) {
        this.problemRating = problemRating;
    }

    public String getStudentSolution() {
        return studentSolution;
    }

    public void setStudentSolution(String studentSolution) {
        this.studentSolution = studentSolution;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
