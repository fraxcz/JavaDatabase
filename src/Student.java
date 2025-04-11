import java.util.ArrayList;

public abstract class Student implements Comparable<Student>{
	
	protected String first_name;
	protected String surname;
	protected String date_of_birth;
	protected Specialization spec;
	private ArrayList<Integer> grades;
	
	public Student(String first_name, String surname, String date_of_birth){
		this.first_name = first_name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		grades = new ArrayList<>();
	}
	
	public void addGrade(int grade) {
		grades.add(grade);
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
		String aux = this.first_name + " " + this.surname + " " + this.date_of_birth + " " + this.spec + " [";
		
		for(int grade : this.grades) {
			aux += grade;
			if(this.grades.size() - 1 > this.grades.indexOf(grade))
				aux += ",";
		}
		aux += "]\n";
		return aux;
	}
	
	@Override
	public int compareTo(Student o) {
		return this.surname.compareTo(o.surname);
	}

	@Override
	public String toString() {
		return "first name: " + this.first_name + ", surname: " + this.surname + ", date of birth: " + this.date_of_birth + ", grade point average: " + this.get_grade_point_average();
	}
}
