import java.util.ArrayList;
import java.time.LocalDate;

public abstract class Student implements Comparable<Student>{
	
	protected String firstName;
	protected String surname;
	protected LocalDate dateOfBirth;
	protected Specialization spec;
	private ArrayList<Integer> grades;
	
	public Student(String firstName, String surname, LocalDate dateOfBirth){
		this.firstName = firstName;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		grades = new ArrayList<>();
	}
	
	public void addGrade(int grade) {
		grades.add(grade);
	}
	

	public String getfirstName() {
		return firstName;
	}


	public String getSurname() {
		return surname;
	}


	public LocalDate getdateOfBirth() {
		return dateOfBirth;
	}
	
	public ArrayList<Integer> getGrades() {
		return this.grades;
	}
	
	public Specialization getSpecialization() {
		return this.spec;
	}

	public double get_grade_point_average() {
		double grade_sum = 0.0d;
		for(int grades: grades) {
			grade_sum += grades;
		}
		return grade_sum / this.grades.size();
		
	}
	public abstract String do_skill();
	
	public String getAttributes() {
		String aux = this.firstName + " " + this.surname + " " + this.dateOfBirth + " " + this.spec + " [";
		
		for(int i = 0; i < this.grades.size(); i++){
			aux += this.grades.get(i);
			
			if(i < this.grades.size() - 1)
				aux += ",";
		}
		aux += "]";
		return aux;
	}
	
	@Override
	public int compareTo(Student o) {
		return this.surname.compareTo(o.surname);
	}

	@Override
	public String toString() {
		return "first name: " + this.firstName + ", surname: " + this.surname + ", date of birth: " + this.dateOfBirth + ", grade point average: " + this.get_grade_point_average();
	}
}
