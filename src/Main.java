public class Main {

	public static void main(String[] args) {
		Database my_database = new Database();
		my_database.add_student("Jan", "Hus", "1.6.1956", Specialization.CyberSecurity);
		my_database.add_student("Ondra", "Novak", "1.6.1956", Specialization.Telecom);
		my_database.add_grade(0, 2);
		my_database.add_grade(0, 5);
		my_database.add_grade(1, 1);
		my_database.add_grade(1, 3);
		System.out.println(my_database);
		System.out.println(my_database.do_student_skill(0));
		System.out.println(my_database.get_group_count(Specialization.CyberSecurity));
		
	}

}
