package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

public class DatabaseUtility {
	Connection conn;
	public void getdbConnection() throws Throwable{
	try {
		Driver driver=new Driver();
		 conn = DriverManager.getConnection("jsbc:mysql://localhost:3306/projects", "tiger", "tiger");
	}catch(Exception e) {
		
	}

	}
	public void closeDBconnection() throws Throwable {
	try {	
	conn.close();
	}catch(Exception e) {
		
	}

}
	public Resultset excuteonselectQuery(String query) throws Throwable
	{
		Resultset result=null;
		try {
		Statement stat = conn.createStatement();
		 result = (Resultset) stat.executeQuery(query);
		}catch(Exception e) {
			
		}
		return result;
	}
	public void excuteNonselectQuery(String query) {
		int result=0;
		try {
			Statement stat=conn.createStatement();
			stat.executeUpdate(query);
		}catch(Exception e) {
	}
}
}
