package daytoday;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	
	//USER DATA
	public String userName;
	public boolean darkTheme = false;
	public boolean defaultUser;
	public double hoursOfSleep;
	public String bedtime;
	public int bedtimeImportance;
	
	private RandomAccessFile db;
	public ArrayList<Event> events;
	
	public Database(RandomAccessFile db) throws IOException {
		this.db = db;
		events = new ArrayList<Event>();
	}
	
	//when application is loaded, load the data from the text file into memory
	public void parseDB() throws IOException {
		//setUserData();
		
		String currentLine = null;
		while((currentLine = db.readLine()) != null && !currentLine.isBlank()) {
			parseEvent(currentLine);
		}
		
	}
	
	private void setUserData() throws IOException {
		String userData = db.readLine();
		Scanner userScan = new Scanner(userData);
		userScan.useDelimiter("\\|");
		
		userName = userScan.next();
		darkTheme = Boolean.parseBoolean(userScan.next());
		defaultUser = Boolean.parseBoolean(userScan.next());
		hoursOfSleep = Double.parseDouble(userScan.next());
		bedtime = userScan.next();
		bedtimeImportance = Integer.parseInt(userScan.next());
	}
	
	private void parseEvent(String eventString) {
		Scanner eventScan = new Scanner(eventString);
		eventScan.useDelimiter("\\|");
		
		String theName = eventScan.next();
		boolean theRoutine = Boolean.parseBoolean(eventScan.next()); 
		int theImportance = Integer.parseInt(eventScan.next());
		String startTime = eventScan.next();
		String endTime = eventScan.next();
		int hours = Integer.parseInt(eventScan.next());
		boolean[] appearArr = {Boolean.parseBoolean(eventScan.next()), Boolean.parseBoolean(eventScan.next()),
							   Boolean.parseBoolean(eventScan.next()), Boolean.parseBoolean(eventScan.next())};
		String theTag = eventScan.next();
		ArrayList<String> keywords = new ArrayList<String>();
		while(eventScan.hasNext()) {
			keywords.add(eventScan.next());
		}
		
		//Event newEvent = new Event(theName, theRoutine, theImportance, appearArr, theTag, keywords, startTime, endTime, hours, null, null);
		//events.add(newEvent);
		//System.out.println(newEvent.toString());
		eventScan.close();
	}
	
	//when new event is created, write it to the text file
	public void writeToDB(String stringToWrite) throws IOException {
		db.seek(db.length());
		db.writeBytes(stringToWrite);
	}
	
	public void addEvent(Event theEvent) throws IOException {
		events.add(theEvent);
		writeToDB(theEvent.toString());
	}
}