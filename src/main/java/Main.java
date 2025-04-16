import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Database db = new Database();
		/*
		db.add_student("Dan", "Novak", LocalDate.of(2004, 2, 10), Specialization.CyberSecurity);
		db.add_student("Jiri", "Novy", LocalDate.of(1954, 3, 25), Specialization.Telecom);
		db.add_student("Matej", "Priletel", LocalDate.of(1250, 5, 4), Specialization.CyberSecurity);
		db.add_student("Ondra", "Objevitel", LocalDate.of(2009, 7, 15), Specialization.CyberSecurity);
		db.add_student("Anna", "Dvorakova", LocalDate.of(2025, 11, 7), Specialization.Telecom);
		db.add_student("Vladislav", "Novotny", LocalDate.of(2035, 12, 29), Specialization.CyberSecurity);
		db.add_student("Monika", "Absolunova", LocalDate.of(2004, 3, 13), Specialization.Telecom);
		db.add_student("Jesus", "Christ", LocalDate.of(1, 1, 1), Specialization.CyberSecurity);
		db.addGrade(0, 1);
		db.addGrade(0, 2);
		db.addGrade(1, 5);
		db.addGrade(1, 2);
		db.addGrade(2, 4);
		db.addGrade(2, 4);
		db.addGrade(3, 2);
		db.addGrade(3, 1);
		db.addGrade(3, 4);
		db.addGrade(4, 3);
		db.addGrade(5, 2);
		db.addGrade(6, 3);
		db.addGrade(7, 1);
		System.out.println(db + "\n");
		System.out.println(db.toStringSorted());
		System.out.println(db.getGroupCount(Specialization.CyberSecurity));
		System.out.println(db.getGroupCount(Specialization.Telecom));
		*/
		try {
			db.loadFromADatabase();
			System.out.print(db);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void print_menu() {
		System.out.println(
				"1.\tNew Database\n" + 
				"2.\tAdd Student\n" +
				"3.\tAdd student's grade\n" + 
				"4.\tStudent expulsion\n" + 
				"5.\tDo student's skill\n" + 
				"6.\tFind student by id\n" + 
				"7.\tSorted student's list by surname\n" + 
				"8.\tGPA in selected specialization\n" +
				"9.\tNumber of students in selected specialization\n" +
				"10.\tSave student to a file\n"	+
				"11.\tLoad student from a file\n" +
				"0.\tSave to SQL and exit");
	}

}
