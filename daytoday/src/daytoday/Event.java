package daytoday;
import java.util.ArrayList;

public class Event {
	
	private String name;						//name of event
	private boolean routine;					//is this event a routine?
	private int importance;						//how important is this event (1-5)
	private String tag;							//organizational tag of this event
	private String[] keywords;					//keywords to find this event when searching for it
	private String startTime;					//when does event start
	private String endTime;						//when does event end
	private int hoursToComplete;				//how many hours will it take to complete
	
	private ArrayList<Event> subevents;			//child events
	private Event parent;
	
	private boolean calendar, todo, schedule, countdown;	//where will this event appear
	
	private int[][] customInfo;		//2D array to create custom ranges
	
	public Event(String name, boolean routine, int importance, boolean[] appear, String tag, String[] keywords, 
			String startTime, String endTime, int hoursToComplete, Event parent, int[][] customInfo) {
		
		this.name = name;
		this.routine = routine;
		this.importance = importance;
		this.tag = tag;
		this.keywords = keywords;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hoursToComplete = hoursToComplete;
		
		if(routine) {
			this.customInfo = customInfo;
		} else {
			this.customInfo = null;
		}
		
		calendar = appear[0];
		todo = appear[1];
		schedule = appear[2];
		countdown = appear[3];
		
		subevents = new ArrayList<Event>();
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Event> getSubevents() {
		return subevents;
	}
	
	public void addSubevent(Event theEvent) {
		subevents.add(theEvent);
	}
	
	public String toString() {
		String retStr = "" + name + "|" + routine + "|" + importance + "|" + startTime + "|" + endTime + "|" + hoursToComplete +
				"|" + calendar + "|" + todo + "|" + schedule + "|" + countdown + "|" + tag + "|";
		
		for(int i = 0; i < keywords.length; i++) {
			retStr = retStr + keywords[i] + "|";
		}
		
		return retStr + "\n";
	}
}
