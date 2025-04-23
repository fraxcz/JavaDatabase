import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Database db = new Database();
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		String select = "";
		try {
			db.loadFromADatabase();
			System.out.println("Database loaded succesfully.");
		}
		catch(SQLException e) {
			System.out.println("Couldn't load database.");
			run = false;
		}
		
		if(run == false){
			System.out.println("Trying to create a new one.");
			try {
				db.createDatabase();
				System.out.println("New database created succesfully.");
				run = true;
			}
			catch(SQLException e) {
					e.printStackTrace();
			}
		}
		
		while(run) {
			print_menu();
			System.out.print("Enter number: ");
			select = sc.next();
			cls();
			switch(select) {
			case "1":
				String name;
				String surname;
				String dateOfBirth[];
				LocalDate date;
				String specialization;
				
				System.out.print("Enter name, surname, date of birth(YYYY-MM-DD), and specialization name: ");
				name = sc.next();
				surname = sc.next();
				dateOfBirth = sc.next().split("-");
				try {
					date = LocalDate.of(Integer.valueOf(dateOfBirth[0]),Integer.valueOf(dateOfBirth[1]),Integer.valueOf(dateOfBirth[2]));
				}
				catch(NumberFormatException e) {
					System.out.println("Date of birth require numbers!");
					break;
				}
				specialization = sc.next().toLowerCase();
				
				switch(specialization) {
				case "telecommunication":
					db.add_student(name, surname, date, Specialization.Telecommunication);
					break;
				case "cybersecurity":
					db.add_student(name, surname, date, Specialization.CyberSecurity);
					break;
				default:
					System.out.println("Invalid specialization.");
					break;
	
				}
				System.out.println("Student added successfully.");
				break;
				
			case "2":
				System.out.println(db);
				System.out.print("Enter id and grade (1-5):");
				
				try {
					if(db.addGrade(sc.nextInt(), sc.nextInt()))
						System.out.println("Grade added succesfully.");
					else
						System.out.println("Invalid grade or student id.");
				}
				catch(InputMismatchException e){
					System.out.println("Enter numbers only !");
				}
				break;
				
			case "3":
				System.out.print("Enter id: ");
				try {
					if(db.removetudent(sc.nextInt()))
						System.out.println("Student was removed from the database.");
					else
						System.out.println("ID is not valid.");
				}
				catch(InputMismatchException e) {
					System.out.println("Enter numbers only !");
				}
				break;
			
			case "4":
				System.out.print("Enter id: ");
				try {
					System.out.println(db.getStudent(sc.nextInt()));
				}
				catch(InputMismatchException e) {
					System.out.println("Enter numbers only !");
				}
				break;
				
			case "5":
				System.out.print("Enter id: ");
				try {
					System.out.println(db.doStudentSkill(sc.nextInt()));
				}
				catch(InputMismatchException e) {
					System.out.println("Enter numbers only !");
				}
				break;
				
			case "6":
				System.out.println(db.toStringSorted());
				break;
				
			case "7":
				System.out.print("Enter specialization: ");
				specialization = sc.next().toLowerCase();
				System.out.print("GPA in " + specialization + ": ");
				switch(specialization) {
				case "telecommunication":
					System.out.println(db.getAverageOfSpecialization(Specialization.Telecommunication));
					break;
					
				case "cybersecurity":
					System.out.println(db.getAverageOfSpecialization(Specialization.CyberSecurity));
					break;
				default:
					System.out.println("Invalid specialization.");
					break;
				}
				break;
				
			case "8":
				System.out.print("Enter specialization: ");
				specialization = sc.next().toLowerCase();
				System.out.print("Number of students in " + specialization + ": ");
				
				switch(specialization) {
				case "telecommunication":
					System.out.println(db.getGroupCount(Specialization.Telecommunication));
					break;
					
				case "cybersecurity":
					System.out.println(db.getGroupCount(Specialization.CyberSecurity));
					break;
				default:
					System.out.println("Invalid specialization.");
					break;
				}
				break;
				
			case "9":
				System.out.print("Enter id and output path: ");
				
				try {
					db.writeToAFile(sc.nextInt(), sc.next());
					System.out.print("Student saved succesfully.");
				}
				
				catch (InputMismatchException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				break;
				
			case "10":
				try {
					System.out.print("Enter path to a file: ");
					db.readFromAFile(sc.next());
					System.out.print("Student loaded succesfully.");
				}
				
				catch (InputMismatchException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch(IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				break;
				
			case "0":
				try {
					db.saveToADatabase();
					System.out.println("Database was saved successfully.");
					run = false;
					break;
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}		
		}
		System.out.println("press enter to end.");
		sc.nextLine();
		sc.close();
	}
				
	
	
	public static void print_menu() {
		System.out.println(
				"\n1.\tAdd Student\n" +
				"2.\tAdd student's grade\n" + 
				"3.\tStudent expulsion\n" + 
				"4.\tFind student by id\n" + 
				"5.\tDo student's skill\n" + 
				"6.\tSorted student's list by surname\n" + 
				"7.\tGPA in selected specialization\n" +
				"8.\tNumber of students in selected specialization\n" +
				"9.\tSave student to a file\n"	+
				"10.\tLoad student from a file\n" +
				"0.\tSave to SQL and exit\n");
	}
	
	public static void cls() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
		} 
		catch (Exception e) {
			System.out.println("Error while cleaning a console.");
			}
	}
}
