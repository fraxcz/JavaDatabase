import java.util.ArrayList;

public class Database {
	private ArrayList<Student> students;
	
	public Database() {
		this.students = new ArrayList<>();
	}
	
	public void add_student(String name, String surname, String date_of_birth, int id, Specialization s) {
		switch(s) {
		case CyberSecurity:
			students.add(new CyberSecurityStudent(name, surname, date_of_birth, id));
			break;
			
		case Telecom:
			students.add(new TelecommunicationStudent(name, surname, date_of_birth, id));
			break;
		}
	}
	
	public void remove_student(int id) {
		Student wanted_student = null;
		for(Student student: students) {
			if(id == student.get_id()) {
				wanted_student = student;
				break;
			}
		}
		if(wanted_student != null) {
			this.students.remove(wanted_student);
		}
	}
}
