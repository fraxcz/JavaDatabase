import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

public class SQLDatabase {
	
	private Connection conn; 
	public boolean connect() { 
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:studentDB.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void disconnect() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean selectFromTable() {
		if(conn == null)
			return false;
		
		String sql = "SELECT * FROM students";
		try(Statement stm = conn.createStatement()){
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("name") + " " + rs.getString("surname") + " " + rs.getDate("birthdate") + "\n");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean insertIntoTable() {
		if(conn == null)
			return false;
		
		String sql = "INSERT INTO students(name,surname,birthdate) VALUES(?,?,?)";
		try(PreparedStatement stm = conn.prepareStatement(sql)) {
			stm.setString(1, "Jan");
			stm.setString(2, "Dobry");
			stm.setDate(3, Date.valueOf(LocalDate.of(2000, 12, 5)));
			stm.executeUpdate();
			System.out.println("Insert was succesful.");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean createTable(){
		
		if(conn == null)
			return false;
		
		String sql1 = "CREATE TABLE IF NOT EXISTS students ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name VARCHAR(255) NOT NULL,"
				+ "surname VARCHAR(255) NOT NULL,"
				+ "birthdate DATE NOT NULL);";
		
		String sql2 = "CREATE TABLE IF NOT EXISTS grades ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "student_id INTEGER NOT NULL,"
				+ "grade INTEGER NOT NULL,"
				+ "FOREIGN KEY (student_id) REFERENCES students(id));";
		
		try(Statement stm = conn.createStatement()){
			stm.execute(sql1);
			System.out.println("first done.");
			stm.execute(sql2);
			System.out.println("Second done.");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	return true;
	}
}
