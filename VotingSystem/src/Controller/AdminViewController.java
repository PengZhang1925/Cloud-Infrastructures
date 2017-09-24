package Controller;

import java.math.BigInteger;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import Model.Admin;
import Model.Pailliar;
import Model.Voter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class AdminViewController implements Initializable {
	@FXML
	private ComboBox<String> CandidateName;
	@FXML
	private Label votes;
	@FXML
	private Label raw;
	
	static Random ran = new Random();
	Voter can = new Voter();
	Admin admin = new Admin();
	Pailliar pa = new Pailliar();
	
	
	public void CheckVotes(ActionEvent event) throws SQLException{
		String can = CandidateName.getValue();
		String showVotes =admin.getVotes(can) +" votes";
		votes.setText(showVotes);
	}
	
	public void CollectVotes(ActionEvent event){
			
		try {
			BigInteger N2 = new BigInteger(admin.getnsquare());
			BigInteger mess = new BigInteger(can.getKey().get(0));
			for(int i=1;i<can.getKey().size();i++){
				mess = mess.multiply(new BigInteger(can.getKey().get(i))).mod(N2);
			}
			BigInteger demess = admin.decrypt(mess);
			raw.setText(demess.toString());
			
			List<String> is = new ArrayList<String>();
			for(int i = 0; i < demess.toString().length(); i++) {
			final char c = demess.toString().charAt(i);
			if(Character.isDigit(c)) {
			is.add(String.valueOf(c));
			}
			}
		     for(int j=0;j<=is.size();j++){
		    	 switch(j){
		    	 case 0:admin.SaveVotes("Tom", is.get(j));break;
		    	 case 1:admin.SaveVotes("John", is.get(j));break;
		    	 case 2:admin.SaveVotes("Peter", is.get(j));break;
		    	 case 3:admin.SaveVotes("Linda", is.get(j));break;
		    	 case 4:admin.SaveVotes("Andy", is.get(j));break;
		    	 }
		     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void Back(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AdminLoginView.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,700,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void StartVoting(ActionEvent event) throws Exception{
		admin.CleanVotes();
		pa.kenGen();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			ObservableList<String> canList;
			try {
				canList = FXCollections.observableArrayList(admin.getCust());
				CandidateName.setItems(canList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
