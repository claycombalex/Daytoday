package daytoday;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Accordion;

public class SceneController implements Initializable {
	
	//used for scene management
	private String currentScene;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Manager manager;
	
	//css for background color and various style
	String css = this.getClass().getResource("application.css").toExternalForm();
	
	//control nodes used for creating new user
	public ComboBox<String> themeBox;
	public ComboBox<String> importantBox;
	public ComboBox<String> bedtimeBox;
	public CheckBox defaultUserChoice;
	public TextField usernameField;
	public TextField hoursOfSleep;
	public TextField bedtimeHour;
	public TextField bedtimeMinute;
	
	//Control nodes used for building events
	public ComboBox<String> reoccurBox;
	public ComboBox<String> setTimeBox;
	public ComboBox<String> startBox;
	public ComboBox<String> endBox;
	public ComboBox<String> repeatBox;
	public ChoiceBox<String> importantBox2;
	public TextField eventField;
	public TextField startHourField;
	public TextField startMinuteField;
	public TextField endHourField;
	public TextField endMinuteField;
	public TextField totalHoursField;
	public DatePicker startDateField;
	public DatePicker endDateField;
	public CheckBox showOnCalendar;
	public CheckBox showOnCountdown;
	public CheckBox showOnSchedule;
	public CheckBox showOnToDo;
	public TextField tagField;
	public TextArea keywordsField;
	public AnchorPane commonRepeat;
	public AnchorPane repeatWeekly;
	public AnchorPane repeatMonthly;
	public AnchorPane repeatYearly;
	public AnchorPane startPane;
	public AnchorPane endPane;
	public AnchorPane commonValues;
	
	//Control nodes for todo list
	public AnchorPane toDoListMain;
	public ScrollPane scrollField;
	public Accordion accordionField;
	
	//lists for choice boxes
	ObservableList<String> themeSelect = FXCollections.observableArrayList("Light Theme (Default)", "Dark Theme");
	ObservableList<String> importantList = FXCollections.observableArrayList("Required (6)", "Very important (5)", "Important (4)", "Somewhat important (3)", "Not very important (2)", "Not important at all (1)");
	ObservableList<String> AMPMList = FXCollections.observableArrayList("AM", "PM");
	ObservableList<String> reoccurList = FXCollections.observableArrayList("No (only occurs once)", "Yes (this event will repeat)");
	ObservableList<String> setTimeList = FXCollections.observableArrayList("It does not have a set start time or end time", "This event has a set start time",
			"This event has a set end time", "This event has both a set start time and a set end time");
	ObservableList<String> repeatList = FXCollections.observableArrayList("Day", "Week", "Month", "Year");
	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
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
	
	public void switchToCalendar(ActionEvent event) throws IOException {
		currentScene = "Month_Calendar.fxml";
		loadScene(currentScene, event);
	}
	
	public void switchToWeekly(ActionEvent event) throws IOException {
		currentScene = "Week_Calendar.fxml";
		loadScene(currentScene, event);
	}
	
	public void switchToYearly(ActionEvent event) throws IOException {
		currentScene = "Year_Calendar.fxml";
		loadScene(currentScene, event);
	}
	
	public void switchToDaily(ActionEvent event) throws IOException {
		currentScene = "Day_Calendar.fxml";
		loadScene(currentScene, event);
	}
	
	void Select(ActionEvent event) {
		String s = themeBox.getSelectionModel().getSelectedItem().toString();
	}
	
	void loadScene(String sceneName, ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
    	root = (Parent) loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
        SceneController sc = loader.getController();
        sc.setManager(manager);
        
        if(sceneName.contentEquals("To_Do_List.fxml")) {
        	SceneController todo = (SceneController) loader.getController();
        	todo.createToDo();
        }
        
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
				bedtimeBox.setItems(AMPMList);
				bedtimeBox.setValue("PM");
				break;
			case "Event_Builder.fxml":
				reoccurBox.setItems(reoccurList);
				setTimeBox.setItems(setTimeList);
				importantBox2.setItems(importantList);
				startBox.setItems(AMPMList);
				startBox.setValue("AM");
				endBox.setItems(AMPMList);
				endBox.setValue("AM");
				repeatBox.setItems(repeatList);
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
	
	public void commonRepeatVisible(ActionEvent event) {
		if(reoccurBox.getValue().contentEquals("Yes (this event will repeat)")) {
			commonRepeat.setVisible(true);
		} else if(reoccurBox.getValue().contentEquals("No (only occurs once)")) {
			commonRepeat.setVisible(false);
		}
		commonValues.setVisible(true);
		setPositions();
	}
	
	public void repeatTypeVisible(ActionEvent event) {
		if(repeatBox.getValue().contentEquals("Week")) {
			repeatWeekly.setVisible(true);
			repeatMonthly.setVisible(false);
			repeatYearly.setVisible(false);
		} else if(repeatBox.getValue().contentEquals("Month")) {
			repeatWeekly.setVisible(false);
			repeatMonthly.setVisible(true);
			repeatYearly.setVisible(false);
		} else if(repeatBox.getValue().contentEquals("Year")) {
			repeatWeekly.setVisible(false);
			repeatMonthly.setVisible(false);
			repeatYearly.setVisible(true);
		} else {
			repeatWeekly.setVisible(false);
			repeatMonthly.setVisible(false);
			repeatYearly.setVisible(false);
		}
		setPositions();
	}
	
	public void timeVisible(ActionEvent event) {
		if(setTimeBox.getValue().contentEquals("This event has a set start time")) {
			startPane.setVisible(true);
			endPane.setVisible(false);
		} else if(setTimeBox.getValue().contentEquals("This event has a set end time")) {
			startPane.setVisible(false);
			endPane.setVisible(true);
		} else if(setTimeBox.getValue().contentEquals("This event has both a set start time and a set end time")) {
			startPane.setVisible(true);
			endPane.setVisible(true);
		} else {
			startPane.setVisible(false);
			endPane.setVisible(false);
		}
		commonValues.setVisible(true);
		setPositions();
	}
	
	private void setPositions() {
		commonRepeat.setLayoutY(getLayoutY(commonRepeat));
		repeatWeekly.setLayoutY(getLayoutY(repeatWeekly));
		repeatMonthly.setLayoutY(getLayoutY(repeatMonthly));
		repeatYearly.setLayoutY(getLayoutY(repeatYearly));
		startPane.setLayoutY(getLayoutY(startPane));
		endPane.setLayoutY(getLayoutY(endPane));
		commonValues.setLayoutY(getLayoutY(commonValues));
	}
	
	private int getLayoutY(Object controlNode) {
		switch(controlNode.toString()) {
			case "AnchorPane[id=commonRepeat]": return 174;
			case "AnchorPane[id=repeatWeekly]": return 238;
			case "AnchorPane[id=repeatMonthly]": return 238;
			case "AnchorPane[id=repeatYearly]": return 238;
			case "AnchorPane[id=startPane]":
				if(repeatYearly.isVisible()) return 311;
				else if(repeatMonthly.isVisible()) return 344;
				else if(repeatWeekly.isVisible()) return 351;
				else if(commonRepeat.isVisible()) return 238;
				else return 174;
			case "AnchorPane[id=endPane]":
				if(startPane.isVisible()) return (int) (startPane.getLayoutY() + 90);
				else if(repeatYearly.isVisible()) return 311;
				else if(repeatMonthly.isVisible()) return 344;
				else if(repeatWeekly.isVisible()) return 351;
				else if(commonRepeat.isVisible()) return 238;
				else return 174;
			case "AnchorPane[id=commonValues]":
				if(endPane.isVisible()) return (int) (endPane.getLayoutY() + 90);
				else if(startPane.isVisible()) return (int) (startPane.getLayoutY() + 90);
				else if(repeatYearly.isVisible()) return 311;
				else if(repeatMonthly.isVisible()) return 344;
				else if(repeatWeekly.isVisible()) return 351;
				else if(commonRepeat.isVisible()) return 238;
				else return 174;
		}
		
		return 0;
	}
	
	public void createEvent(ActionEvent event) throws IOException {
		boolean routineVal = true;
		if(reoccurBox.getValue().contentEquals("No (only occurs once)"))
			routineVal = false;
		
		int importantVal = 0;
		String tempStr = "";
		Scanner importantScan = new Scanner(importantBox2.getValue());
		importantScan.useDelimiter("\\(|\\)");
		importantScan.next();
		tempStr = importantScan.next();
		importantVal = Integer.parseInt(tempStr);
		importantScan.close();
		
		boolean[] appearVal = {showOnCalendar.isSelected(), showOnCountdown.isSelected(), 
				showOnSchedule.isSelected(), showOnToDo.isSelected()};
		
		ArrayList<String> keywords = (ArrayList<String>) Arrays.asList(keywordsField.getText().split(","));
		
		for(int i = 0; i < keywords.size(); i++) {
			keywords.set(i, keywords.get(i).trim());
		}
		
		LocalDate sdat = startDateField.getValue();
		LocalDate edat = endDateField.getValue();
		String startTime = "" + sdat.getYear() + String.format("%02d", sdat.getMonth().ordinal() + 1) + String.format("%02d", sdat.getDayOfMonth()) + 
				String.format("%02d", Integer.parseInt(startHourField.getText())) + String.format("%02d", Integer.parseInt(startMinuteField.getText()));
		String endTime = "" + edat.getYear() + String.format("%02d", edat.getMonth().ordinal() + 1) + String.format("%02d", edat.getDayOfMonth()) + 
				String.format("%02d", Integer.parseInt(endHourField.getText())) + String.format("%02d", Integer.parseInt(endMinuteField.getText()));
		
		int hoursToComplete = 0;
		//possibly add minutes to complete
		if(!totalHoursField.getText().equals("")) {
			hoursToComplete = Integer.parseInt(totalHoursField.getText());
		}
		
		Event theEvent = new Event(eventField.getText(), routineVal, importantVal, appearVal, tagField.getText(), keywords, startTime, endTime, hoursToComplete, null, null);
		manager.db.addEvent(theEvent);
		switchToHomeScreen(event);
	}
	
	public void newUserValues() {
		manager.db.userName = usernameField.getText();
		
		if(reoccurBox.getValue().contentEquals("Dark Theme"))
			manager.db.darkTheme = true;
		
		manager.db.defaultUser = defaultUserChoice.isSelected();
		manager.db.hoursOfSleep = Double.parseDouble(hoursOfSleep.getText());
		
		String AMPM = bedtimeBox.getValue();
		int bedHour = Integer.parseInt(bedtimeHour.getText());
		int newHour = 0;
		
		if(bedHour == 12)
			bedHour = 0;
		
		if(AMPM.equals("AM"))
			newHour = bedHour;
		else
			newHour = bedHour + 12;
		
		manager.db.bedtime = String.format("%02d", newHour) + String.format("%02d", bedtimeMinute.getText());
	}
	
	public void createToDo() {
		for(int i = 0; i < manager.db.events.size(); i++) {
			AnchorPane tempPane = new AnchorPane();
			Text theText = new Text("test text!");
			theText.setY(100);
			tempPane.getChildren().add(theText);
			tempPane.setMaxHeight(150);
			tempPane.setMinHeight(150);
			Event curEvent = manager.db.events.get(i);
			TitledPane titled = new TitledPane(curEvent.getName(), tempPane);
			Font theFont = new Font("System", 18);
			titled.setFont(theFont);
			titled.setAnimated(false);
			
			accordionField.getPanes().add(titled);
		}
	}
}