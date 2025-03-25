import java.util.HashMap;
public class TelecommunicationStudent extends Student {

	HashMap<Character, String> morse_code = new HashMap<>();
	public TelecommunicationStudent(String name, String surname, String date_of_birth, int id) {
		super(name, surname, date_of_birth, id);
		this.fill_morse_code();
	}
	
	private void fill_morse_code(){
        this.morse_code.put('A', ".-");		this.morse_code.put('B', "-...");
        this.morse_code.put('C', "-.-.");	this.morse_code.put('D', "-..");
        this.morse_code.put('E', ".");		this.morse_code.put('F', "..-.");
        this.morse_code.put('G', "--.");	this.morse_code.put('H', "....");
        this.morse_code.put('I', "..");		this.morse_code.put('J', ".---");
        this.morse_code.put('K', "-.-");	this.morse_code.put('L', ".-..");
        this.morse_code.put('M', "--");		this.morse_code.put('N', "-.");
        this.morse_code.put('O', "---");	this.morse_code.put('P', ".--.");
        this.morse_code.put('Q', "--.-");	this.morse_code.put('R', ".-.");
        this.morse_code.put('S', "...");	this.morse_code.put('T', "-");
        this.morse_code.put('U', "..-");	this.morse_code.put('V', "...-");
        this.morse_code.put('W', ".--");	this.morse_code.put('X', "-..-");
        this.morse_code.put('Y', "-.--");	this.morse_code.put('Z', "--..");
        this.morse_code.put('1', ".----");	this.morse_code.put('2', "..---");
        this.morse_code.put('3', "...--");	this.morse_code.put('4', "....-");
        this.morse_code.put('5', ".....");	this.morse_code.put('6', "-....");
        this.morse_code.put('7', "--...");	this.morse_code.put('8', "---..");
        this.morse_code.put('9', "----.");	this.morse_code.put('0', "-----");
	}
	
	@Override
	public String do_skill() {
		String aux = "";
		for(char ch: this.name.toUpperCase().toCharArray()) {
			aux += this.morse_code.get(ch);
			aux += "|";
		}
		aux += " ";
		
		for(char ch: this.surname.toUpperCase().toCharArray()) {
			aux += this.morse_code.get(ch);
			aux += "|";
		}
		return aux;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", specialization: Telecommunication technology.";
	}

}
