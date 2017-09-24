package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.SQLException;

import Model.Admin;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class AdminLoginViewController {
	@FXML
	private TextField UserName;
	@FXML
	private Label Status;
	@FXML
	private PasswordField PassWord;
	@FXML
	private Label isConnected;

	// Event Listener on Button.onAction
	
	public Admin admin = new Admin();
	public void Login(ActionEvent event) throws Exception {
		try{
			if(admin.isLogin(UserName.getText(),PassWord.getText())){
				isConnected.setText("Login Success");
				((Node)event.getSource()).getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root,700,700);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}else{
				Status.setText("Login Failed");
			}
		}catch(SQLException e){
			Status.setText("Login Failed");
			e.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void CancelLogin(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
