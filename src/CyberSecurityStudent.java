
public class CyberSecurityStudent extends Student {

	public CyberSecurityStudent(String name, String surname, String date_of_birth, int id) {
		super(name, surname, date_of_birth, id);
	}

	@Override
	public String do_skill() {
		// TODO implement this
		return "i can't do anything.";
	}

	@Override
	public String toString() {
		return super.toString() + ", specialization: Cybersecurity";
	}
	
}
