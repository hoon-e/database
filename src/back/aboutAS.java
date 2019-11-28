package back;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class aboutAS {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	public aboutAS() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Center list 반환
	public String[] part_list() {
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();

			String search = "SELECT COUNT(*) AS COUNT FROM Part;";
			ResultSet rs = null;
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT Part_id FROM Part;";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("Part_id"));
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
	
	// AS DB 값 가져오기
	public String[][] getValue() {
		try {
			String SQL = "SELECT AS_id, AS_cost, AS_why, DATE_FORMAT(AS_date, '%Y/%d/%m') as d, Customer_id, Employee_id, Part_id FROM AS_ta;";
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			rs.last();
			int count = rs.getRow();
			String[][] result = new String[count][7];
			
			rs.beforeFirst();
			
			int i = 0;
			while(rs.next()){
				result[i][0] = Integer.toString(rs.getInt("AS_id"));
				result[i][1] = Integer.toString(rs.getInt("AS_cost"));
				result[i][2] = rs.getString("AS_why");
				result[i][3] = rs.getString("d");
				result[i][4] = Integer.toString(rs.getInt("Customer_id"));
				result[i][5] = Integer.toString(rs.getInt("Employee_id"));
				result[i][6] = Integer.toString(rs.getInt("Part_id"));
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
	
	public void addAS(int ASID, int ASCOST, String ASWHY, Date dateshow, int setID, int listTarget, int listTarget2) 
	{
		String SQL = "INSERT INTO AS_ta VALUES (?,?,?,?,?,?,?);";
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, ASID);
			pstmt.setInt(2,  ASCOST);
			pstmt.setString(3, ASWHY);
			pstmt.setDate(4, dateshow);
			pstmt.setInt(5, setID);
			pstmt.setInt(6, listTarget);
			pstmt.setInt(7, listTarget2);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
