package user;

import java.util.ArrayList;

public class Student extends User{


	public Student(int id, String username, String password, String country, String email, ArrayList<Integer> attempedProblems, Statistics statistics) {
		super(id,username,password,country,email,attempedProblems,statistics);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
