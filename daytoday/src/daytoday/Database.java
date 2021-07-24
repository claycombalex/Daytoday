package daytoday;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	
	//USER DATA
	private ArrayList<Event> events;
	private String userName;
	private boolean darkTheme;
	private boolean defaultUser;
	private double hoursOfSleep;
	private double bedtime;
	private int bedtimeImportance;
	
	private RandomAccessFile db;
	
	public Database(RandomAccessFile db) {
		this.db = db;
		events = new ArrayList<Event>();
	}
	
	//when application is loaded, load the data from the text file into memory
	public void parseDB() throws IOException {
		setUserData();
		
		String currentLine = null;
		//while((currentLine = db.readLine()) != null && !currentLine.isBlank()) {
			//parseEvent(currentLine);
		//}
		
	}
	
	private void setUserData() throws IOException {
		String userData = db.readLine();
		Scanner userScan = new Scanner(userData);
		userScan.useDelimiter("\\|");
		
		userName = userScan.next();
		darkTheme = Boolean.parseBoolean(userScan.next());
		defaultUser = Boolean.parseBoolean(userScan.next());
		hoursOfSleep = Double.parseDouble(userScan.next());
		bedtime = Double.parseDouble(userScan.next());
		bedtimeImportance = Integer.parseInt(userScan.next());
	}
	
	private void parseEvent(String eventString) {
		Scanner eventScan = new Scanner(eventString);
		eventScan.useDelimiter("\\|");
		
		String theName = eventScan.next();
		boolean theRoutine = Boolean.parseBoolean(eventScan.next()); 
		int theImportance = Integer.parseInt(eventScan.next());
		boolean[] appearArr = {true, false, true};
		
		System.out.println(eventString);
	}
	
	//when new event is created, write it to the text file
	public void writeToDB(String stringToWrite, int offset) throws IOException {
		db.seek(offset);
		db.writeBytes(stringToWrite);
	}
	
	public void addEvent(Event theEvent) {
		events.add(theEvent);
	}
}