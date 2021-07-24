package daytoday;
import java.util.ArrayList;

public class Event {
	
	private String name;						//name of event
	private boolean routine;					//is this event a routine?
	private int importance;						//how important is this event (1-5)
	private String tag;							//organizational tag of this event
	private ArrayList<String> keywords;			//keywords to find this event when searching for it
	
	private ArrayList<Event> subevents;			//child events
	private Event parent;
	
	private boolean calendar, todo, schedule;	//where will this event appear
	
	public Event(String name, boolean routine, int importance, boolean[] appear) {
		
		this.name = name;
		this.routine = routine;
		this.importance = importance;
		
		calendar = appear[0];
		todo = appear[1];
		schedule = appear[2];
		
		subevents = new ArrayList<Event>();
		parent = null;
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
