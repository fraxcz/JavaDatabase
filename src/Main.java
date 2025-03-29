
public class Main {

	public static void main(String[] args) {
		Database my_database = new Database();
		my_database.add_student("Jan", "Cerny", "1.6.1980", Specialization.CyberSecurity);
		my_database.add_student("Jiri", "Novak", "4.5.2002", Specialization.Telecom);
		System.out.println(my_database);
	}

}
