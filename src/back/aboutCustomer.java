package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class aboutCustomer {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	// 생성자에서 DB 연결 수행
	public aboutCustomer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Center list 반환
		public String[] list_center() {
			String[] result = null;

			try {
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
				stmt = con.createStatement();
				String search = "SELECT COUNT(*) AS COUNT FROM Center;";
				ResultSet rs = null;
				
				rs = stmt.executeQuery(search);
				int count = 0;
				
				while(rs.next()) {
					count = rs.getInt("COUNT");
				}
				
				result = new String[count];
				
				String search2 = "SELECT DISTINCT Center_id FROM Center;";
				rs = stmt.executeQuery(search2);
				
				int num = 0;
				
				while(rs.next()) {
					result[num] = Integer.toString(rs.getInt("Center_id"));
					num += 1;
				}
				con.close();
				return result;
			}
			catch(Exception e) {
				System.out.println(e);
			}
			return result;
		}
		
		// Customer DB update
		public void updateCustomer(int customerID, String PW, String customerNAME, int age, String customerGENDER, int centerID, int Cid) {
			String SQL = "UPDATE Customer SET Customer_id=?, Customer_name=?, Customer_age=?, Customer_gender=?, Center_id=?, passwd=? WHERE Customer_id=?;";
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
				pstmt = con.prepareStatement(SQL);
				
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, customerID);
				pstmt.setString(2, customerNAME);
				pstmt.setInt(3, age);
				pstmt.setString(4,  customerGENDER);
				pstmt.setInt(5, centerID);
				pstmt.setString(6, PW);
				pstmt.setInt(7, Cid);
				
				int row = pstmt.executeUpdate();
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// Customer id DB 값 가져오기
		public String[] getiDValue(int cust_id) {
			try {
				// 회원 정보 가져오기
				String SQL = "SELECT * FROM Customer WHERE Customer_id=" + cust_id + ";";
				con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
				ResultSet rs = stmt.executeQuery(SQL);
				
				String[] result = new String[6];
				
				// Customer DB 값 내보내기
				while(rs.next()){
					result[0] = Integer.toString(rs.getInt("Customer_id"));
					result[1] = rs.getString("Customer_name");
					result[2] = rs.getString("Customer_age");
					result[3] = rs.getString("Customer_gender");
					result[4] = Integer.toString(rs.getInt("Center_id"));
					result[5] = rs.getString("passwd");
				}
				
				con.close();
				return result;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	// Department DB 값 가져오기
	public String[][] getValue(int cid) {
		try {
			String SQL = "SELECT * FROM Customer WHERE Center_id=" + cid + ";";
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			rs.last();
			int count = rs.getRow();
			String[][] result = new String[count][4];
			
			rs.beforeFirst();
			
			int i = 0;
			
			while(rs.next()){
				result[i][0] = Integer.toString(rs.getInt("Customer_id"));
				result[i][1] = rs.getString("Customer_name");
				result[i][2] = rs.getString("Customer_age");
				result[i][3] = rs.getString("Customer_gender");
				i++;
			}
			
			con.close();
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
