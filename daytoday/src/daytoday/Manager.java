package daytoday;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Manager {

	public Database db;
	
	private RandomAccessFile dataTxt;
	private boolean newUser = false;
	String currentLine = null;
	
	public Manager() throws IOException {
		dataTxt = new RandomAccessFile("userData.txt", "rw");
		db = new Database(dataTxt);
		
		
		if((currentLine = dataTxt.readLine()) == null)
			newUser = true;
		
		dataTxt.seek(0);
		
		if(!newUser)
			db.parseDB();
	}
	
	public boolean isNewUser() {
		return newUser;
	}
}
