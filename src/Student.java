import java.util.ArrayList;

public abstract class Student {
	
	protected String name;
	protected String surname;
	protected String date_of_birth;
	private int id;
	private ArrayList<Integer> grades;
	public static int addId;
	
	public Student(String name, String surname, String date_of_birth, int id) {
		this.name = name;
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
	
	public abstract String do_skill();
	
	@Override
	public String toString() {
		return "Name: " + this.name + ", Surname: " + this.surname + ", date of birth: " + this.date_of_birth + ", id: " + this.id;
	}
}
