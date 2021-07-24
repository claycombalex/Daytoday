package daytoday;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Manager {
	
	private RandomAccessFile dataTxt;
	private Database db;
	private boolean newUser = false;
	
	String currentLine = null;
	
	public Manager() throws IOException {
		dataTxt = new RandomAccessFile("userData.txt", "rw");
		db = new Database(dataTxt);
		
		if((currentLine = dataTxt.readLine()) == null)
			newUser = true;

		dataTxt.seek(0);
		db.parseDB();
	}
	
	public boolean isNewUser() {
		return newUser;
	}
}
