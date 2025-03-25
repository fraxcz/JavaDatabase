
public class Main {

	public static void main(String[] args) {
		Database my_database = new Database();
		my_database.add_student("Jan", "Cerny", "1.6.5245", 1, Specialization.CyberSecurity);
		my_database.remove_student(1);
		System.out.println("Done.");

	}

}
