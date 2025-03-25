
public class TelecommunicationStudent extends Student {

	public TelecommunicationStudent(String name, String surname, String date_of_birth, int id) {
		super(name, surname, date_of_birth, id);
	}

	@Override
	public String do_skill() {
		//TODO: implement this
		return "i don't know anything.";
	}
	
	@Override
	public String toString() {
		return super.toString() + ", specialization: Telecommunication technology.";
	}

}
