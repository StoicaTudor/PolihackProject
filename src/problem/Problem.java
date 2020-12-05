package problem;

import java.util.ArrayList;

public class Problem implements Comparable<Problem> {

	private String problemName;
	private int problemID;
	private String task;
	public String taskThumbnail;
	private String solution;
	private int popularity;
	private Difficulty problemDifficulty;
	private Subject problemSubject;
	private ArrayList<Solution> solutions;
	private int proposerID;
	private int grade;

	// Problem() constructor
	public Problem(String problemName, int problemID, String task, String solution, int popularity,
			Difficulty problemDifficulty, Subject problemSubject, ArrayList<Solution> solutions, int proposerID,
			int grade) {
		this.proposerID = proposerID;
		this.problemName = problemName;
		this.problemID = problemID;
		this.task = task;
		this.solution = solution;
		this.popularity = popularity;
		this.problemDifficulty = problemDifficulty;
		this.problemSubject = problemSubject;
		this.solutions = solutions;
		this.grade = grade;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public int getProblemID() {
		return problemID;
	}

	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public Difficulty getProblemDifficulty() {
		return problemDifficulty;
	}

	public void setProblemDifficulty(Difficulty problemDifficulty) {
		this.problemDifficulty = problemDifficulty;
	}

	public Subject getProblemSubject() {
		return problemSubject;
	}

	public void setProblemSubject(Subject problemSubject) {
		this.problemSubject = problemSubject;
	}

	public ArrayList<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(ArrayList<Solution> solutions) {
		this.solutions = solutions;
	}

	@Override
	public int compareTo(Problem problem) {
		return (this.getPopularity() < problem.getPopularity()) ? 1 : 0;
	}
	
	public void setTaskThumbnail() {
		// TO DO MIRUNA
		
	}
}
