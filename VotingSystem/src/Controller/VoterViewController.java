package Controller;

import java.math.BigInteger;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import Model.Admin;
import Model.Voter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VoterViewController implements Initializable{
	@FXML
	private Label status;
	@FXML
	private Button Tom,John,Peter,Linda,Andy;
	
	static Random ran = new Random();
	Voter can = new Voter();
	Admin admin = new Admin();
	BigInteger c, gm, rn;
	Random rnd = new Random();
	BigInteger r = new BigInteger(512, rnd).mod(getN());
	
	
	public void Tom(ActionEvent event){
		BigInteger message = new BigInteger(Integer.toString(10000));
		encrypt(message);
		Tom.setDisable(true);
		John.setDisable(true);
		Peter.setDisable(true);
		Linda.setDisable(true);
		Andy.setDisable(true);
	}
	
	public void John(ActionEvent event){
		BigInteger message = new BigInteger(Integer.toString(01000));
		encrypt(message);
		Tom.setDisable(true);
		John.setDisable(true);
		Peter.setDisable(true);
		Linda.setDisable(true);
		Andy.setDisable(true);
	}
	
	public void Peter(ActionEvent event){
		BigInteger message = new BigInteger(Integer.toString(00100));
		encrypt(message);
		Tom.setDisable(true);
		John.setDisable(true);
		Peter.setDisable(true);
		Linda.setDisable(true);
		Andy.setDisable(true);
	}
	
	public void Linda(ActionEvent event){
		BigInteger message = new BigInteger(Integer.toString(00010));
		encrypt(message);
		Tom.setDisable(true);
		John.setDisable(true);
		Peter.setDisable(true);
		Linda.setDisable(true);
		Andy.setDisable(true);
	}
	
	public void Andy(ActionEvent event){
		BigInteger message = new BigInteger(Integer.toString(00001));
		encrypt(message);
		Tom.setDisable(true);
		John.setDisable(true);
		Peter.setDisable(true);
		Linda.setDisable(true);
		Andy.setDisable(true);
	}
	
	public void Back(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void Refresh(ActionEvent event) throws Exception{
		try{
			if(Integer.parseInt(admin.CountVoters()) >= 5){
				Tom.setDisable(true);
				John.setDisable(true);
				Peter.setDisable(true);
				Linda.setDisable(true);
				Andy.setDisable(true);
				status.setText("The Voting system is closed");
			}else{
				Tom.setDisable(false);
				John.setDisable(false);
				Peter.setDisable(false);
				Linda.setDisable(false);
				Andy.setDisable(false);
			}
		}catch(Exception e){}
		
	}
	
	public void encrypt(BigInteger m){
		try {
			BigInteger g = new BigInteger(admin.getg());
			BigInteger N = new BigInteger(admin.getn());
			BigInteger N2 = new BigInteger(admin.getnsquare());
			gm = g.modPow(m, N2);
			rn = r.modPow(N, N2);
			c = gm.multiply(rn).mod(N2);			
			can.SaveMess(c.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BigInteger getN(){
		BigInteger N = null;
		try {
			N = new BigInteger(admin.getn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return N;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try{
			if(Integer.parseInt(admin.CountVoters()) >= 5){
				Tom.setDisable(true);
				John.setDisable(true);
				Peter.setDisable(true);
				Linda.setDisable(true);
				Andy.setDisable(true);
				status.setText("The Voting system is closed");
			}
		}catch(Exception e){}
	
	}
}
