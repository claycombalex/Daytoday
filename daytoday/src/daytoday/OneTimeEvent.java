package daytoday;

public class OneTimeEvent extends Event {

	private boolean timeSensitive;			//does this event have to occur at a specific time
	private int dueDate;
	private int hoursToComplete;
	private int startTime;
	private int endTime;
	
	public OneTimeEvent(String name, boolean routine, int importance, boolean[] appear, 
			int dueDate, int hoursToComplete) {
		super(name, routine, importance, appear);
		
	}

}
