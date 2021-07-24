package daytoday;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SceneController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	String css = this.getClass().getResource("application.css").toExternalForm();
	@FXML
	public ComboBox<String> themeBox;
	@FXML
	public ComboBox<String> importantBox;
	@FXML
	public ComboBox<String> bedtimeBox;
	public ComboBox<String> reoccurBox;
	public ComboBox<String> setTimeBox;
	
	ObservableList<String> themeSelect = FXCollections.observableArrayList("Light Theme (Default)", "Dark Theme");
	ObservableList<String> importantList = FXCollections.observableArrayList("Required (6)", "Very important (5)", "Important (4)", "Somewhat important (3)", "Not very important (2)", "Not important at all (1)");
	ObservableList<String> bedtimeList = FXCollections.observableArrayList("AM", "PM");
	ObservableList<String> reoccurList = FXCollections.observableArrayList("No (has a set end date)", "Yes (this event will repeat)");
	ObservableList<String> setTimeList = FXCollections.observableArrayList("It does not have a set start time or end time", "This event has a set start time",
			"This event has a set end time", "This event has both a set start time and a set end time");

	
	public void switchToIntroScreen(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Intro_Screen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToNewUser(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("New_User.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();		
	}
	
	public void switchToDoList(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("To_Do_List.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();		
	}
	
	public void switchToHomeScreen(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();		
	}
	
	public void switchToEventBuilder(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Event_Builder.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	void Select(ActionEvent event) {
		String s = themeBox.getSelectionModel().getSelectedItem().toString();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(themeBox != null) {
			themeBox.setItems(themeSelect);
			themeBox.setValue("Light Theme (Default)");
		} if(importantBox != null) {
			importantBox.setItems(importantList);
		} if(bedtimeBox != null) {
			bedtimeBox.setItems(bedtimeList);
			bedtimeBox.setValue("PM");
		} if(reoccurBox != null) {
			reoccurBox.setItems(reoccurList);
		} if(setTimeBox != null) {
			setTimeBox.setItems(setTimeList);
		}
	}
}
