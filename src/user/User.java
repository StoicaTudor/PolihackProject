package user;

import problem.Subject;
import java.util.ArrayList;
import java.util.Set;

public abstract class User {

	private int id;
	private String username;
	private String password;
	private String country;
	private String email;
	private ArrayList<Integer> attemptedProblems;
	private Statistics statistics;
	private Set<Subject> preferredSubjects;

	public User(int id, String username, String password, String country, String email,
			ArrayList<Integer> attemptedProblems, Statistics statistics, Set<Subject> preferredSubjects) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.country = country;
		this.email = email;
		this.attemptedProblems = attemptedProblems;
		this.statistics = statistics;
		this.preferredSubjects = preferredSubjects;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public ArrayList<Integer> getAttemptedProblemsList(){
		return this.attemptedProblems;
	}
	
	public Statistics getStatistics() {
		return this.statistics;
	}
	
	public Set<Subject> getPreferredSubjects(){
		return this.preferredSubjects;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public String getPassword() {
		
		return this.password;
	}

}
