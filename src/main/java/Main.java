import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SQLDatabase db = new SQLDatabase();
		db.connect();
		db.createTable();
		db.disconnect();
		sc.close();
		
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
