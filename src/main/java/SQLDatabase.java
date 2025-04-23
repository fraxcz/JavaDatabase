import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public ResultSet getStudentsAndGrades() throws SQLException{
		if(conn == null)
			return null;

		String sql = "SELECT *, GROUP_CONCAT(grades.grade) AS grades FROM students LEFT JOIN grades ON grades.student_id = students.id GROUP BY students.id;";
		Statement stm = conn.createStatement();
		ResultSet rsStudent = stm.executeQuery(sql);
		return rsStudent;

	}
	
	public void deleteAllStudentsAndGrades() throws SQLException{
		if(conn == null)
			throw new SQLException("Connection was not estabilished.");
		
		String sql1 = "DELETE FROM students";
		String sql2 = "DELETE FROM grades";
		
		Statement stm = conn.createStatement();
		stm.executeUpdate(sql1);
		stm.executeUpdate(sql2);
		
	}
	
	public void insertStudent(String name, String surname, LocalDate date, Specialization specialization, ArrayList<Integer> grades) throws SQLException {
		if(conn == null)
			throw new SQLException("Connection was not estabilished.");
		
		String sql = "INSERT INTO students(name,surname,birthdate,specialization) VALUES(?,?,?,?)";
			PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, name);
			stm.setString(2, surname);
			stm.setLong(3, date.toEpochDay());
			stm.setString(4, specialization.name());
			stm.executeUpdate();
			
			ResultSet generatedID = stm.getGeneratedKeys();
			if(generatedID.next()){
				int studentId = generatedID.getInt(1);
				this.insertGrades(studentId, grades);
			}
		}

	
	private void insertGrades(int student_id, ArrayList<Integer> grades) throws SQLException {
		String sql = "INSERT INTO grades(student_id,grade) VALUES (?,?)";
		PreparedStatement stm = conn.prepareStatement(sql);
		for(int grade: grades) {
			stm.setInt(1, student_id);
			stm.setInt(2, grade);
			stm.executeUpdate();
			}
		}
	public void createTable() throws SQLException{
		
		if(conn == null)
			throw new SQLException("Connection was not estabilished.");
		
		String sql1 = "CREATE TABLE IF NOT EXISTS students ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name VARCHAR(255) NOT NULL,"
				+ "surname VARCHAR(255) NOT NULL,"
				+ "birthdate BIGINT NOT NULL,"
				+ "specialization VARCHAR(255) NOT NULL);";
		
		String sql2 = "CREATE TABLE IF NOT EXISTS grades ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "student_id INTEGER NOT NULL,"
				+ "grade INTEGER NOT NULL,"
				+ "FOREIGN KEY (student_id) REFERENCES students(id));";
		
		Statement stm = conn.createStatement();
		stm.execute(sql1);
		stm.execute(sql2);
	}
}
