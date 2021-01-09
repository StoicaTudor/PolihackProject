package user;

import problem.Subject;
import java.util.ArrayList;
import java.util.Set;

public abstract class User {

	public int id;
	private String username;
	private String password;
	private String country;
	private String fullname;
	public String email;
	private ArrayList<Integer> attemptedProblems; // solution ID
	private Statistics statistics;
	private Set<Subject> preferredSubjects;
	private int currentGrade;

	public User(int id, String username,String fullname, String password, String country, String email,
			ArrayList<Integer> attemptedProblems, Statistics statistics, Set<Subject> preferredSubjects, int grade) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.fullname=fullname;
		this.country = country;
		this.email = email;
		this.attemptedProblems = attemptedProblems;
		this.statistics = statistics;
		this.preferredSubjects = preferredSubjects;
		this.currentGrade = grade;
	}
	public String getEmail(){
		return this.email;
	}
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getGrade() {
		return this.currentGrade;
	}

	public String getUsername() {
		return this.username;
	}

	public String getCountry() {
		return this.country;
	}

	public ArrayList<Integer> getAttemptedProblemsList() {
		return this.attemptedProblems;
	}

	public Statistics getStatistics() {
		return this.statistics;
	}

	public Set<Subject> getPreferredSubjects() {
		return this.preferredSubjects;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public String getPassword() {

		return this.password;
	}

}
