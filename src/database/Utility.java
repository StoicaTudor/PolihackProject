package database;

import java.util.HashSet;
import java.util.Set;

import problem.Subject;

public class Utility {

	public Utility() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Subject convertStringSubjectToEnumSubject(String stringSubject) {

			switch (stringSubject) {

			case "Mathematics":
				return Subject.MATHEMATICS;

			case "Physics":
				return Subject.PHYSICS;

			case "ComputerScience":
				return Subject.COMPUTER_SCIENCE;

			case "English":
				return Subject.ENGLISH;

			case "German":
				return Subject.GERMAN;

			case "Spanish":
				return Subject.SPANISH;

			case "French":
				return Subject.FRENCH;

			case "Chemistry":
				return Subject.CHEMISTRY;

			case "Economy":
				return Subject.ECONOMY;

			case "Biology":
				return Subject.BIOLOGY;

			case "Geography":
				return Subject.GEOGRAPHY;

			case "History":
				return Subject.HISTORY;

			default:
				return Subject.MATHEMATICS;
			}
	}
	
	protected Set<Subject> convertStringSetToSubjectSet(Set<String> subjectsListString) {

		Set<Subject> preferredSubjects = new HashSet<Subject>();

		for (String subject : subjectsListString) {

			switch (subject) {

			case "Mathematics":
				preferredSubjects.add(Subject.MATHEMATICS);
				break;

			case "Physics":
				preferredSubjects.add(Subject.PHYSICS);
				break;

			case "ComputerScience":
				preferredSubjects.add(Subject.COMPUTER_SCIENCE);
				break;

			case "English":
				preferredSubjects.add(Subject.ENGLISH);
				break;

			case "German":
				preferredSubjects.add(Subject.GERMAN);
				break;

			case "Spanish":
				preferredSubjects.add(Subject.SPANISH);
				break;

			case "French":
				preferredSubjects.add(Subject.FRENCH);
				break;

			case "Chemistry":
				preferredSubjects.add(Subject.CHEMISTRY);
				break;

			case "Economy":
				preferredSubjects.add(Subject.ECONOMY);
				break;

			case "Biology":
				preferredSubjects.add(Subject.BIOLOGY);
				break;

			case "Geography":
				preferredSubjects.add(Subject.GEOGRAPHY);
				break;

			case "History":
				preferredSubjects.add(Subject.HISTORY);
				break;

			default:

				break;
			}
		}

		return preferredSubjects;
	}

}
