import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Database {
	private ArrayList<Student> students;
	
	public Database() {
		this.students = new ArrayList<>();
	}
	
	public Database(String path) {
		this.students = new ArrayList<>();
		this.readFromAFile(path);
	}
	
	public void add_student(String name, String surname, String date_of_birth, Specialization s) {
		
		switch(s) {
		case CyberSecurity:
			students.add(new CyberSecurityStudent(name, surname, date_of_birth));
			break;
			
		case Telecom:
			students.add(new TelecommunicationStudent(name, surname, date_of_birth));
			break;
		}
	}
	
	public int get_group_count(Specialization specialization) {
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
	
	public String do_student_skill(int id) {
		Student student = this.get_student(id);
		if(student == null) 
			return "Wrong id.";
		
		return student.do_skill();
	}
	
	public double get_average_of_specialization(Specialization specialization) {
		
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
	
	public boolean add_grade(int id, int grade) {
		if(grade < 1 || grade > 5)
			return false;
		
		Student student = this.get_student(id);
		
		if(student == null)
			return false;
		
		student.addGrade(grade);
		return true;
	}
	
	public Student get_student(int id){
		try {
			Student student = this.students.get(id);
			return student;
		}
		catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public boolean remove_student(int id) {
		return students.remove(this.get_student(id));
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
	public void writeToAFile(String path) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for(Student student: this.students) {
				writer.write(student.getAttributes());
				}
			writer.close();
			System.out.println("Succesfully saved a database to " + path);
		}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
	}

	public void readFromAFile(String path) {
		try {
			String line;
			String lineSplit[];
			String grades[];
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			while((line = reader.readLine()) != null) {
				
				line = line.replaceAll("[\\[\\]]", "");
				lineSplit = line.split(" ");
				grades = lineSplit[4].split(",");
				
				switch(lineSplit[3]) {
				case "CyberSecurity":
					Student studentC = new CyberSecurityStudent(lineSplit[0], lineSplit[1], lineSplit[2]);
					for(String grade: grades)
						studentC.addGrade(Integer.parseInt(grade));
					this.students.add(studentC);
					break;
					
				case "Telecom":
					Student studentT = new TelecommunicationStudent(lineSplit[0], lineSplit[1], lineSplit[2]);
					
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
