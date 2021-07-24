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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SceneController implements Initializable {
	
	private String currentScene;
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
}
