package lab6Data;

import java.sql.*;

public class data {

	public static void main(String[] args) {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab2", "root", "");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from depositor");


			while (rs.next())

				System.out.println(rs.getString(1) + " -- " + rs.getInt(2));

			con.close();

		}
		catch (Exception e) {

			System.out.println(e);

		}
		
		
		
		
		
	}

}
