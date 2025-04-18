import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
public class Database {
	private ArrayList<Student> students;
	
	public Database() {
		this.students = new ArrayList<>();
	}
	
	public void add_student(String name, String surname, LocalDate date_of_birth, Specialization s) {
		
		switch(s) {
		case CyberSecurity:
			students.add(new CyberSecurityStudent(name, surname, date_of_birth));
			break;
			
		case Telecom:
			students.add(new TelecommunicationStudent(name, surname, date_of_birth));
			break;
		}
	}
	
	public int getGroupCount(Specialization specialization) {
		int count = 0;
		for(Student student: this.students) {
			if (specialization == Specialization.CyberSecurity && student instanceof CyberSecurityStudent) {
	            count++;
	            
	        } else if (specialization == Specialization.Telecom && student instanceof TelecommunicationStudent) {
	            count++;
	        }
		}
		return count;
	}
	
	public String doStudentSkill(int id) {
		Student student = this.getStudent(id);
		if(student == null) 
			return "Wrong id.";
		
		return student.do_skill();
	}
	
	public double getAverageOfSpecialization(Specialization specialization) {
		
		double sum = 0;
		int count = 0;
		for(Student student: this.students) {
			if (specialization == Specialization.CyberSecurity && student instanceof CyberSecurityStudent) {
	            sum += student.get_grade_point_average();
	            count++;
	            
	        } else if (specialization == Specialization.Telecom && student instanceof TelecommunicationStudent) {
	        	sum += student.get_grade_point_average();
	        	count++;
	        }
		}
		return sum / count;
	}
	
	public boolean addGrade(int id, int grade) {
		if(grade < 1 || grade > 5)
			return false;
		
		Student student = this.getStudent(id);
		
		if(student == null)
			return false;
		
		student.addGrade(grade);
		return true;
	}
	
	public Student getStudent(int id){
		try {
			Student student = this.students.get(id);
			return student;
		}
		catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public boolean removetudent(int id) {
		return students.remove(this.getStudent(id));
	}
	
	public String toStringSorted() {
		String aux = "";
		ArrayList<Student> studentsSorted = new ArrayList<>(this.students);
		studentsSorted.sort(null);
		
		for(Student student: studentsSorted) {
			aux += "id: " + this.students.indexOf(student) + ", " + student.toString() + "\n";
		}
		return aux;
	}
	public void writeToAFile(int id, String path) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(this.students.get(id).getAttributes() + "\n");
			writer.close();
			System.out.println("Succesfully saved a student to " + path);
		}
			catch(IOException e) {
				e.printStackTrace();
			}
	}

	public void readFromAFile(String path) {
		try {
			String line;
			String lineSplit[];
			String grades[];
			String birthDate[];
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			while((line = reader.readLine()) != null) {
				
				line = line.replaceAll("[\\[\\]]", "");
				lineSplit = line.split(" ");
				grades = lineSplit[4].split(",");
				birthDate = lineSplit[3].split("-");
				LocalDate ld = LocalDate.of(Integer.valueOf(birthDate[0]), Integer.valueOf(birthDate[1]), Integer.valueOf(birthDate[2]));
				switch(lineSplit[3]) {
				
				case "CyberSecurity":
					Student studentC = new CyberSecurityStudent(lineSplit[0], lineSplit[1], ld);
					for(String grade: grades)
						studentC.addGrade(Integer.parseInt(grade));
					this.students.add(studentC);
					break;
					
				case "Telecom":
					Student studentT = new TelecommunicationStudent(lineSplit[0], lineSplit[1], ld);
					
					for(String grade: grades)
						studentT.addGrade(Integer.parseInt(grade));
					this.students.add(studentT);
					break;
				}
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Could't load every student in a file, missing arguments !");
		}
	}
	
	public void createDatabase() throws SQLException {
		SQLDatabase db = new SQLDatabase();
		db.connect();
		db.createTable();
		db.disconnect();
	}
	
	public void saveToADatabase() throws SQLException {
		SQLDatabase db = new SQLDatabase();
		if(!db.connect())
			return;
		for(Student student: students) {
				db.insertStudent(student.getfirstName(), student.getSurname(), student.getdateOfBirth(), student.getSpecialization(), student.getGrades());
			}
		db.disconnect();
		}
	
	public void loadFromADatabase() throws SQLException {
		SQLDatabase db = new SQLDatabase();
		if(!db.connect()) {
			System.out.println("chyba.");
			return;
		}
		
		ResultSet rs = db.getStudentsAndGrades();
		String grades[];
		Student student;
		
		while(rs.next()) {
			switch(rs.getString("specialization")) {
			case ("CyberSecurity"):
				student = new CyberSecurityStudent(rs.getString("name"), rs.getString("surname"), LocalDate.ofEpochDay(rs.getInt("birthdate")));
				grades = rs.getString("grades").split(",");
				
				for (String grade: grades) {
					student.addGrade(Integer.valueOf(grade));
				}
				this.students.add(student);
				break;
			
			case ("Telecom"):
				student = new TelecommunicationStudent(rs.getString("name"), rs.getString("surname"), LocalDate.ofEpochDay(rs.getInt("birthdate")));
				grades = rs.getString("grades").split(",");
				
				for (String grade: grades) {
					student.addGrade(Integer.valueOf(grade));
				}
				this.students.add(student);
				break;

			default:
				System.out.println("Student specialization has not been found. Student wasn't loaded.");
				break;
			}
		}
		db.disconnect();
	}
		
	
	@Override
	public String toString() {
		String aux = "";
		
		for(Student student: this.students) {
			aux += "id: " + this.students.indexOf(student) + ", " + student.toString() + "\n";
		}
		return aux;
	}
}
