package daytoday;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Daytoday extends Application {
	
	private static Manager manager;
	private static String resource = "Home_Screen.fxml";
	
    public static void main(String[] args) throws IOException {
    	manager = new Manager();
    	if(manager.isNewUser())
    		resource = "Intro_Screen.fxml";
        launch();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource(resource));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setTitle("Daytoday Productivity Software");
        stage.getIcons().add(new Image("day_logo_blank.png")); 

        stage.setScene(scene);
        stage.show();
    }
    
}
