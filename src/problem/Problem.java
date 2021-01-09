package problem;

import java.util.ArrayList;

public class Problem implements Comparable<Problem> {

	private String problemName;
	private int problemID;
	private String task;
	public String taskThumbnail;
	private String solution;
	private String problemDifficulty;
	private Subject problemSubject;
	private int proposerID;
	public int grade;

	// Problem() constructor
	public Problem(String problemName, int problemID, String task, String solution,
			String problemDifficulty, Subject problemSubject, int proposerID, int grade) {

		this.proposerID = proposerID;
		this.problemName = problemName;
		this.problemID = problemID;
		this.task = task;
		this.solution = solution;
		this.problemDifficulty = problemDifficulty;
		this.problemSubject = problemSubject;
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



	public String getProblemDifficulty() {
		return problemDifficulty;
	}

	public void setProblemDifficulty(String problemDifficulty) {
		this.problemDifficulty = problemDifficulty;
	}

	public Subject getProblemSubject() {
		return problemSubject;
	}

	public void setProblemSubject(Subject problemSubject) {
		this.problemSubject = problemSubject;
	}


	public static Difficulty getDifficultyInt(int difficulty) {

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

	public static Difficulty getDifficultyString(String difficulty) {

		switch (difficulty) {

		case "easy":
			return Difficulty.EASY;

		case "medium":
			return Difficulty.MEDIUM;

		case "hard":
			return Difficulty.HARD;

		case "ivan":
			return Difficulty.IVAN;

		default:
			return Difficulty.EASY;
		}
	}

	@Override

	public int compareTo(Problem problem) {
		if (this.getProblemDifficulty().equals(problemDifficulty)) return 1;
		else return 0;
	}
}
