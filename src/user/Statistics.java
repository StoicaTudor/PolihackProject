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


	public int getNrOfAttemptedProblems() {
		return nrOfAttemptedProblems;
	}

	public void setNrOfAttemptedProblems(int nrOfAttemptedProblems) {
		this.nrOfAttemptedProblems = nrOfAttemptedProblems;
	}

	public int getNrOfCorrectProblems() {
		return nrOfCorrectProblems;
	}

	public void setNrOfCorrectProblems(int nrOfCorrectProblems) {
		this.nrOfCorrectProblems = nrOfCorrectProblems;
	}

	public int getHoursActive() {
		return hoursActive;
	}

	public void setHoursActive(int hoursActive) {
		this.hoursActive = hoursActive;
	}

	public double getRating() {
		return averageRating;
	}

	public void setRating(double averageRating) {
		this.averageRating = averageRating;
	}
}
