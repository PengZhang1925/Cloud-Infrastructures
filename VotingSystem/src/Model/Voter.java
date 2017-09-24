package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Voter {
	static Connection connection;
	static Random ran = new Random();
	static Admin admin = new Admin();
	static Voter can = new Voter();
	public Voter(){
		connection = SqliteConnection.Connector();
	}
	
	public boolean isDbConnected(){
		try{
			return !connection.isClosed();
		}catch(SQLException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String user, String pass) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "Select * from Candidates where candname = ? and password = ?";
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,user);
			preparedStatement.setString(2,pass);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}finally{
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public List<String> getKey() throws SQLException{
		List<String> data = new ArrayList<>();
		String SQL = "select * from message";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
						data.add(rs.getString(i));
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			rs.close();
			ps.close();
		}	
		return data;
	}
	
	public boolean saveKey(String X,String P,String G,String Y) throws SQLException{
		String SQL = "Update PrivateKey set X=?,P=?,G=?,Y=?";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, X);
			ps.setString(2, P);
			ps.setString(3, G);
			ps.setString(4, Y);
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	
	public boolean SaveMess(String mess) throws SQLException{
		String SQL = "Insert INTO message(mess) VALUES(?)";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, mess);
			
			ps.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	
}
