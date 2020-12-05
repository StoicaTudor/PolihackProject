package database;

public class DatabaseQuerryMaker {

	public DatabaseQuerryMaker() {
		
	}
	
	public String getRetrieveUserQuery() {
		
		StringBuilder userQ=new StringBuilder("SELECT * FROM pandemicspecial.user");
		return userQ.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
