import java.util.ArrayList;

public class Database {
	private ArrayList<Student> students;
	
	public Database() {
		this.students = new ArrayList<>();
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
		//TODO: dodelat
		
		return "";
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
