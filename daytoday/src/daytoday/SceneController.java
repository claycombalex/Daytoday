package daytoday;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneController implements Initializable {
	
	//used for scene management
	private String currentScene;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//css for background color and various style
	String css = this.getClass().getResource("application.css").toExternalForm();
	
	//control nodes used for creating new user
	public ComboBox<String> themeBox;
	public ComboBox<String> importantBox;
	public ComboBox<String> bedtimeBox;
	
	//Control nodes used for building events
	public ComboBox<String> reoccurBox;
	public ComboBox<String> setTimeBox;
	public TextField eventField;
	public DatePicker startDateField;
	public TextField startHourField;
	public TextField startMinuteField;
	public DatePicker endDateField;
	public TextField endHourField;
	public TextField endMinuteField;
	public TextField totalHoursField;
	public CheckBox showOnCalendar;
	public CheckBox showOnCountdown;
	public CheckBox showOnSchedule;
	public CheckBox showOnToDo;
	public ChoiceBox<String> importantBox2;
	public TextField tagField;
	public TextArea keywordsField;
	
	//lists for choice boxes
	ObservableList<String> themeSelect = FXCollections.observableArrayList("Light Theme (Default)", "Dark Theme");
	ObservableList<String> importantList = FXCollections.observableArrayList("Required (6)", "Very important (5)", "Important (4)", "Somewhat important (3)", "Not very important (2)", "Not important at all (1)");
	ObservableList<String> bedtimeList = FXCollections.observableArrayList("AM", "PM");
	ObservableList<String> reoccurList = FXCollections.observableArrayList("No (only occurs once)", "Yes (this event will repeat)");
	ObservableList<String> setTimeList = FXCollections.observableArrayList("It does not have a set start time or end time", "This event has a set start time",
			"This event has a set end time", "This event has both a set start time and a set end time");

	
	public void switchToIntroScreen(ActionEvent event) throws IOException {
		currentScene = "Intro_Screen.fxml";
		loadScene(currentScene, event);
	}
	
	public void switchToNewUser(ActionEvent event) throws IOException {
		currentScene = "New_User.fxml";
		loadScene(currentScene, event);
	}
	
	public void switchToDoList(ActionEvent event) throws IOException {
		currentScene = "To_Do_List.fxml";
		loadScene(currentScene, event);
		
	}
	
	public void switchToHomeScreen(ActionEvent event) throws IOException {
		currentScene = "Home_Screen.fxml";
		loadScene(currentScene, event);
	}
	
	public void switchToEventBuilder(ActionEvent event) throws IOException {
		currentScene = "Event_Builder.fxml";
		loadScene(currentScene, event);
	}
	
	void Select(ActionEvent event) {
		String s = themeBox.getSelectionModel().getSelectedItem().toString();
	}
	
	void loadScene(String sceneName, ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource(sceneName));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currentScene = filenameSubstring(arg0.toString());
		switch(currentScene) {
			case "New_User.fxml":
				themeBox.setItems(themeSelect);
				themeBox.setValue("Light Theme (Default)");
				importantBox.setItems(importantList);
				bedtimeBox.setItems(bedtimeList);
				bedtimeBox.setValue("PM");
				break;
			case "Event_Builder.fxml":
				reoccurBox.setItems(reoccurList);
				setTimeBox.setItems(setTimeList);
				break;
			default:
				break;
		}
	}
	
	private String filenameSubstring(String longStr) {
		String retStr = "";
		Scanner userScan = new Scanner(longStr);
		userScan.useDelimiter("/");
		while(userScan.hasNext()) {
			retStr = userScan.next();
		}
		userScan.close();
		return retStr;
	}
	
	public void reoccurOrOnce() {
		String choice = reoccurBox.getValue();
		if(choice.contentEquals("No (only occurs once)")) {
			System.out.println(reoccurBox.getId());
		}
	}
	
	public void createEvent() {
		
		boolean routineVal = true;
		if(reoccurBox.getValue().contentEquals("No (only occurs once)"))
			routineVal = false;
		
		int importantVal = 0;
		String tempStr = "";
		Scanner importantScan = new Scanner(importantBox2.getValue());
		importantScan.useDelimiter("(");
		tempStr = importantScan.next();
		importantVal = Integer.parseInt(tempStr.substring(0, 1));
		
		boolean[] appearVal = {showOnCalendar.isSelected(), showOnCountdown.isSelected(), 
				showOnSchedule.isSelected(), showOnToDo.isSelected()};
		
		
		Event theEvent = new Event(eventField.getText(), routineVal, importantVal, appearVal, tagField.getText(), );
	}
}
