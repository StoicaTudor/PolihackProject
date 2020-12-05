package user;

import java.time.LocalDate;

public class Statistics {

	public int nrOfAttemptedProblems;
	public int nrOfCorrectProblems;
	public int hoursActive;
	public double averageRating;
	public LocalDate dateJoined;

	public Statistics(int nrOfAttemptedProblems, int nrOfCorrectProblems, int hoursActive,
			double averageRating, LocalDate dateJoined) {
		
		this.nrOfAttemptedProblems = nrOfAttemptedProblems;
		this.nrOfCorrectProblems = nrOfCorrectProblems;
		this.hoursActive = hoursActive;
		this.averageRating = averageRating;
		this.dateJoined = dateJoined;
	}
}
