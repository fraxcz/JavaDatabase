public class Main {

	public static void main(String[] args) {
		Database my_database = new Database();
		my_database.add_student("Jan", "Hus", "1.6.1956", Specialization.CyberSecurity);
		my_database.add_student("Jana", "Hladka", "1.1.2004", Specialization.CyberSecurity);
		my_database.add_student("Ondra", "Novak", "4.5.1999", Specialization.Telecom);
		my_database.add_grade(0, 2);
		my_database.add_grade(0, 5);
		my_database.add_grade(1, 1);
		my_database.add_grade(1, 3);
		my_database.add_grade(2, 4);
		my_database.add_grade(2, 5);
		my_database.add_grade(2, 2);
		my_database.add_grade(2, 5);
		my_database.add_grade(2, 1);
		System.out.println(my_database);
		System.out.println(my_database.do_student_skill(0));
		System.out.println(my_database.get_group_count(Specialization.CyberSecurity));
		System.out.println(my_database.toStringSorted());
		System.out.println(my_database.get_average_of_specialization(Specialization.CyberSecurity));
		System.out.println(my_database);
		System.out.println(my_database.get_student(0));
		my_database.writeToAFile("output.txt");
		System.out.println("\n 2nd database:");
		Database database2 = new Database();
		database2.readFromAFile("output.txt");
		System.out.println(database2);
		
	}

}
