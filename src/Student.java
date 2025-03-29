import java.util.ArrayList;

public abstract class Student {
	
	protected String first_name;
	protected String surname;
	protected String date_of_birth;
	private int id;
	private ArrayList<Integer> grades;
	public static int addId;
	
	public Student(String first_name, String surname, String date_of_birth, int id) {
		this.first_name = first_name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.id = id;
		grades = new ArrayList<>();
	}
	
	public void addGrade(int grade) {
		grades.add(grade);
	}
	
	public int get_id() {
		return id;
	}
	
	private double get_grade_point_average() {
		double grade_sum = 0.0d;
		for(int grades: grades) {
			grade_sum += grades;
		}
		return grade_sum / this.grades.size();
		
	}
	public abstract String do_skill();
	
	@Override
	public String toString() {
		return "first name: " + this.first_name + ", surname: " + this.surname + ", date of birth: " + this.date_of_birth + ", id: " + this.id + ", grade point average: " + this.get_grade_point_average();
	}
}
