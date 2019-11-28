package back;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/***
 * 
 * @author jaehoon
 *  납품 관련 클래스
 *
 */
public class aboutSupply {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	//  생성자에서 DB 연결 수행
	public aboutSupply() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 직원 목록 리턴 함수
	public String[] emp_list() {		
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = null;
			
			String search = "SELECT COUNT(*) AS COUNT FROM Employee;";
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT Emp_id FROM Employee;";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("Emp_id"));
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
	
	public String[] fac_list() {		
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = null;
			
			String search = "SELECT COUNT(*) AS COUNT FROM Factory;";
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT Factory_id FROM Factory;";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("Factory_id"));
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
	
	public String[] list() {		
		String[] result = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = null;
			
			String search = "SELECT COUNT(*) AS COUNT FROM List;";
			
			rs = stmt.executeQuery(search);
			int count = 0;
			
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}
			
			result = new String[count];
			
			String search2 = "SELECT DISTINCT List_id FROM List;";
			rs = stmt.executeQuery(search2);
			
			int num = 0;
			
			while(rs.next()) {
				result[num] = Integer.toString(rs.getInt("List_id"));
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
	
	// Supply DB 값 가져오기
	public String[][] getValue() {
		try {
			String SQL = "SELECT MS_id, Supply_price, DATE_FORMAT(Supply_date, '%Y/%d/%m') as d, List_id, Factory_id, Employee_id FROM Manage_Supply;";
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			rs.last();
			int count = rs.getRow();
			String[][] result = new String[count][6];
			
			rs.beforeFirst();
			
			int i = 0;
			while(rs.next()){
				result[i][0] = Integer.toString(rs.getInt("MS_id"));
				result[i][1] = Integer.toString(rs.getInt("Supply_price"));
				result[i][2] = rs.getString("d");
				result[i][3] = Integer.toString(rs.getInt("List_id"));
				result[i][4] = Integer.toString(rs.getInt("Factory_id"));
				result[i][5] = Integer.toString(rs.getInt("Employee_id"));
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
	
	public void addSupply(int msid, int suppPrice, java.sql.Date suppDate, int Listid, int Factid, int Empid) {
		String SQL = "INSERT INTO Manage_Supply VALUES (?,?,?,?,?,?);";
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://mysql:4567/ascenter","coldbrew","jaehoon");
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, msid);
			pstmt.setInt(2,  suppPrice);
			pstmt.setDate(3, suppDate);
			pstmt.setInt(4, Listid);
			pstmt.setInt(5, Factid);
			pstmt.setInt(6, Empid);
			
			// SQL문 쿼리 실행
			int row = pstmt.executeUpdate();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
