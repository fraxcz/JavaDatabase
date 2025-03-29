import java.util.ArrayList;

public class Database {
	private ArrayList<Student> students;
	
	public Database() {
		this.students = new ArrayList<>();
	}
	
	public void add_student(String name, String surname, String date_of_birth, Specialization s) {
		
		switch(s) {
		case CyberSecurity:
			students.add(new CyberSecurityStudent(name, surname, date_of_birth, this.students.size()));
			break;
			
		case Telecom:
			students.add(new TelecommunicationStudent(name, surname, date_of_birth, this.students.size()));
			break;
		}
	}
	
	public Student get_student(int id){
		Student wanted_student = null;
		
		for(Student student: students) {
			if(id == student.get_id()) {
				wanted_student = student;
				break;	
			}
		}
		return wanted_student;
	}
	
	public void remove_student(int id) {
		Student student = this.get_student(id);
		if(student != null)
			this.students.remove(student);
		//TODO: dodělat toto
	}
	
	@Override
	public String toString() {
		String aux = "";
		
		for(Student student: this.students) {
			aux += student.toString() + "\n";
		}
		return aux;
	}
}
