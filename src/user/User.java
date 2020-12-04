package user;


import java.util.ArrayList;

public abstract class User {

	private int id;
	private String username;
	private String password;
	private String country;
	private String email;
	private ArrayList<Integer> attemptedProblems;
	private Statistics statistics;


	public User(int id, String username, String password, String country, String email, ArrayList<Integer> attemptedProblems, Statistics statistics) {
		this.id=id;
		this.password=password;
		this.username=username;
		this.country=country;
		this.email=email;
		this.attemptedProblems=attemptedProblems;
		this.statistics=statistics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Integer> getAttempedProblems() {
		return attemptedProblems;
	}

	public void setAttempedProblems(ArrayList<Integer> attempedProblems) {
		this.attemptedProblems = attempedProblems;
	}

	public ArrayList<Integer> getAttemptedProblems() {
		return attemptedProblems;
	}

	public void setAttemptedProblems(ArrayList<Integer> attemptedProblems) {
		this.attemptedProblems = attemptedProblems;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
