
public class Main {

	public static void main(String[] args) {
		Database my_database = new Database();
		my_database.add_student("Jan", "Cerny", "1.6.5245", 1, Specialization.Telecom);
		System.out.println(my_database.get_student(1).do_skill());
	}

}
