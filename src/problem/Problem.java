package problem;

//class problemthumbnail-->>>

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Problem {

	private String problemName;
	private int problemID;
	private String task;
	private String solution;
	private double popularity;
	private difficulty problemDifficulty;
	private subject problemSubject;
	private ArrayList <Solution> solutions;


		//Problem() constructor
		public Problem(String problemName,int problemID,String task,String solution, double popularity, difficulty problemDifficulty,subject problemSubject, ArrayList<Solution> solutions) {
		this.problemName=problemName;
		this.problemID=problemID;
		this.task=task;
		this.solution=solution;
		this.popularity=popularity;
		this.problemDifficulty=problemDifficulty;
		this.problemSubject=problemSubject;
		this.solutions=solutions;
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

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public difficulty getProblemDifficulty() {
		return problemDifficulty;
	}

	public void setProblemDifficulty(difficulty problemDifficulty) {
		this.problemDifficulty = problemDifficulty;
	}

	public subject getProblemSubject() {
		return problemSubject;
	}

	public void setProblemSubject(subject problemSubject) {
		this.problemSubject = problemSubject;
	}

	public ArrayList<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(ArrayList<Solution> solutions) {
		this.solutions = solutions;
	}
}
