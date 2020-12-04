package user;

public class Statistics {

    private int nrOfAttemptedProblems;
    private int nrOfCorrectProblems;
    private int hoursActive;
    private double successRatio;
    private double averageRating;

    public Statistics(int nrOfAttemptedProblems, int nrOfCorrectProblems, int hoursActive, int successRatio, int averageRating){
        this.nrOfAttemptedProblems=nrOfAttemptedProblems;
        this.nrOfCorrectProblems=nrOfCorrectProblems;
        this.hoursActive=hoursActive;
        this.successRatio=successRatio;
        this.averageRating=averageRating;
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

    public double getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(double successRatio) {
        this.successRatio = successRatio;
    }
    public double getRating() {
        return averageRating;
    }

    public void setRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
