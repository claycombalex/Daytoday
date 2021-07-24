package daytoday;

public class ReoccuringEvent extends Event {

	private int repetition; 		//1 for daily, 2 for weekly, 3 for monthly, 4 for yearly, 5 for custom
	private int[][] customInfo;		//2D array to create custom ranges
	private int startTime;			//when does event start
	private int endTime;			//when does event end
	private boolean customTime;		//does the time change every so often (i.e. a job with irregular hours)
	
	
	public ReoccuringEvent(String name, boolean routine, int importance, boolean[] appear,
			int repetition, int[][] customInfo, int startTime, int endTime, boolean customTime) {
		
		super(name, routine, importance, appear);
		this.repetition = repetition;
		this.customInfo = customInfo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.customTime = customTime;
	}
	
}
