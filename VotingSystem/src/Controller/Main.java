package Controller;
	
import java.util.Random;

import Model.Admin;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	static Random ran = new Random();
	static Admin admin = new Admin();

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goAdminPage(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminLoginView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void goCandidatePage(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("VoterView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {		
			launch(args);
			
	}
}
