package daytoday;
import java.util.ArrayList;

public class Event {
	
	private String name;						//name of event
	private boolean routine;					//is this event a routine?
	private int importance;						//how important is this event (1-5)
	private String tag;							//organizational tag of this event
	private ArrayList<String> keywords;			//keywords to find this event when searching for it
	private int startTime;						//when does event start
	private int endTime;						//when does event end
	private boolean timeSensitive;				//does this event have to occur at a specific time
	private int hoursToComplete;
	
	private ArrayList<Event> subevents;			//child events
	private Event parent;
	
	private boolean calendar, todo, schedule;	//where will this event appear
	
	private int[][] customInfo;		//2D array to create custom ranges
	
	public Event(String name, boolean routine, int importance, boolean[] appear, String tag, ArrayList<String> keywords, 
			int startTime, int endTime, boolean timeSensitive, int hoursToComplete, Event parent, int[][] customInfo) {
		
		this.name = name;
		this.routine = routine;
		this.importance = importance;
		this.tag = tag;
		this.keywords = keywords;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeSensitive = timeSensitive;
		this.hoursToComplete = hoursToComplete;
		
		if(routine) {
			this.customInfo = customInfo;
		} else {
			this.customInfo = null;
		}
		
		calendar = appear[0];
		todo = appear[1];
		schedule = appear[2];
		
		subevents = new ArrayList<Event>();
		this.parent = parent;
	}
	
	public ArrayList<Event> getSubevents() {
		return subevents;
	}
	
	public void addSubevent(Event theEvent) {
		subevents.add(theEvent);
	}
	
	public String toString() {
		return "";
	}
}
