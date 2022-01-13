package daytoday;
import java.util.ArrayList;

public class Event {
	
	private String name;						//name of event
	private boolean routine;					//is this event a routine?
	private int times;							//0 = no set end/start time, 1 = set start time, 2 = set end time, 3 = set start and end time
	
	private int interval;						//-1 = none, 0 = day, 1 = week, 2 = month, 3 = year
	private String week;
	private boolean[] weekArr;
	private ArrayList<Integer> month;			//days of the month in integer form
	private ArrayList<String> year;				//days of the year in string form
	
	private String startTime;					//when does event start
	private String endTime;						//when does event end
	
	private int hoursToComplete;				//how many hours will it take to complete
	private int importance;						//how important is this event (1-5)
	private String tag;							//organizational tag of this event
	private ArrayList<String> keywords;			//keywords to find this event when searching for it
	private boolean calendar, todo, schedule, countdown;	//where will this event appear
	
	//future feature will allow for subevents and parents
	
	public Event(String name, boolean routine, int times, int interval, String week, ArrayList<Integer> month, ArrayList<String> year, String startTime, 
			String endTime, int hoursToComplete, boolean[] appear, int importance, String tag, ArrayList<String> keywords) {
		
		this.name = name;
		this.routine = routine;
		this.times = times;
		
		this.interval = interval;
		this.week = week;
		
		weekArr = new boolean[7];
		if(!week.isEmpty()) {
			for(int i = 0; i < 7; i++) {
				int tempInt = Character.getNumericValue(week.charAt(i));
				if(tempInt == 1)
					weekArr[i] = true;
				else 
					weekArr[i] = false;
			}
		}
		
		this.month = month;
		this.year = year;
		
		this.startTime = startTime;
		this.endTime = endTime;
		
		this.hoursToComplete = hoursToComplete;
		calendar = appear[0];
		todo = appear[1];
		schedule = appear[2];
		countdown = appear[3];
		this.importance = importance;
		this.tag = tag;
		this.keywords = keywords;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String monthToString = "";
		String yearToString = "";
		if(month != null)
			monthToString = month.toString();		
		if(year != null)
			yearToString = year.toString();
		
		String retStr = name + "|" + routine + "|" + times + "|" + interval + "|" + week + "|" + monthToString + "|" + yearToString + "|" + 
						startTime + "|" + endTime + "|" + hoursToComplete + "|" + calendar + "|" + todo + "|" + schedule + "|" + countdown + "|" + 
						importance + "|" + tag + "|" + keywords.toString();
		return retStr + "\n";
	}
}
