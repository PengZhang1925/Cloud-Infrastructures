package Model;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.collections.FXCollections;

public class Admin {
	static Connection connection;
	static Voter can = new Voter();
	public Admin(){
		connection = SqliteConnection.Connector();
		if(connection == null) {
			System.out.println("connection not successful");
			System.exit(1);};
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
		String query = "Select * from admin where username = ? and password = ?";
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
	
	public List<String> getCust() throws SQLException{
		List<String> data = FXCollections.observableArrayList();
		Statement st = null;
		try {
			st = connection.createStatement();
		ResultSet rs = st.executeQuery("select CandName from VotesInfo order by CandName");
		while(rs.next()){
			for(int i=1; i<= rs.getMetaData().getColumnCount();i++){
				data.add(rs.getString(i));
			}
		}
		return data;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		st.close();
	}
		return data;
}
	
	public String getVotes(String candName) throws SQLException{
		String data = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select Votes from VotesInfo where CandName = ?";
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,candName);
		
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				data = resultSet.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			preparedStatement.close();
			resultSet.close();
		}return data;
	}
	
	public String getn() throws SQLException{
		String data = null;
		String SQL = "select n from Keys";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
		}	
		return data;
	}
	
	public String getg() throws SQLException{
		String data = null;
		String SQL = "select g from Keys";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
		}		
		return data;
	}
	
	public String getnsquare() throws SQLException{
		String data = null;
		String SQL = "select nsquare from Keys";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
		}		
		return data;
	}
	
	public String getlambda() throws SQLException{
		String data = null;
		String SQL = "select lambda from Keys";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
		}		
		return data;
	}
	
	public boolean SaveKeys(String n,String g,String nsquare,String lambda) throws SQLException{
		String SQL = "Update Keys set n=?,g=?,nsquare=?,lambda=?";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, n);
			ps.setString(2, g);
			ps.setString(3, nsquare);
			ps.setString(4, lambda);

			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	
	public boolean SaveVotes(String candname,String votes) throws SQLException{
		String SQL = "Update VotesInfo set Votes=? where candname=?";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			ps.setString(1, votes);
			ps.setString(2, candname);
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	
	public String CountVoters() throws SQLException{
		String data = null;
		String SQL = "select count(*) from message";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = connection.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			ps.close();
			rs.close();
		}		
		return data;
	}
	
	public boolean CleanVotes() throws SQLException{
		String SQL = "Delete from message where 1=1";
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(SQL);
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	
	public  BigInteger decrypt(BigInteger c) {
		BigInteger demess = null;
		try{
			BigInteger lambda = new BigInteger(getlambda());
			BigInteger N2 = new BigInteger(getnsquare());
			BigInteger N =  new BigInteger(getn());
			BigInteger g = new BigInteger(getg());
			BigInteger fz =  c.modPow(lambda, N2);
			fz = fz.subtract(BigInteger.ONE);
			fz = fz.divide(N);
			BigInteger fm = g.modPow(lambda, N2);
			fm = fm.subtract(BigInteger.ONE);
			fm = fm.divide(N);
			demess = fz.multiply(fm.modInverse(N)).mod(N);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return demess;
	}
}
