import java.util.HashMap;
import java.time.LocalDate;
public class TelecommunicationStudent extends Student {

	HashMap<Character, String> morse_alphabet = new HashMap<>();
	public TelecommunicationStudent(String name, String surname, LocalDate date_of_birth) {
		super(name, surname, date_of_birth);
		this.spec = Specialization.Telecom;
		this.fill_morse_alphabet();
	}
	
	private void fill_morse_alphabet(){
        this.morse_alphabet.put('A', ".-");		this.morse_alphabet.put('B', "-...");
        this.morse_alphabet.put('C', "-.-.");	this.morse_alphabet.put('D', "-..");
        this.morse_alphabet.put('E', ".");		this.morse_alphabet.put('F', "..-.");
        this.morse_alphabet.put('G', "--.");	this.morse_alphabet.put('H', "....");
        this.morse_alphabet.put('I', "..");		this.morse_alphabet.put('J', ".---");
        this.morse_alphabet.put('K', "-.-");	this.morse_alphabet.put('L', ".-..");
        this.morse_alphabet.put('M', "--");		this.morse_alphabet.put('N', "-.");
        this.morse_alphabet.put('O', "---");	this.morse_alphabet.put('P', ".--.");
        this.morse_alphabet.put('Q', "--.-");	this.morse_alphabet.put('R', ".-.");
        this.morse_alphabet.put('S', "...");	this.morse_alphabet.put('T', "-");
        this.morse_alphabet.put('U', "..-");	this.morse_alphabet.put('V', "...-");
        this.morse_alphabet.put('W', ".--");	this.morse_alphabet.put('X', "-..-");
        this.morse_alphabet.put('Y', "-.--");	this.morse_alphabet.put('Z', "--..");
        this.morse_alphabet.put('1', ".----");	this.morse_alphabet.put('2', "..---");
        this.morse_alphabet.put('3', "...--");	this.morse_alphabet.put('4', "....-");
        this.morse_alphabet.put('5', ".....");	this.morse_alphabet.put('6', "-....");
        this.morse_alphabet.put('7', "--...");	this.morse_alphabet.put('8', "---..");
        this.morse_alphabet.put('9', "----.");	this.morse_alphabet.put('0', "-----");
	}
	
	@Override
	public String do_skill() {
		String aux = "|";
		for(char ch: this.firstName.toUpperCase().toCharArray()) {
			aux += this.morse_alphabet.get(ch);
			aux += "|";
		}
		aux += " |";
		
		for(char ch: this.surname.toUpperCase().toCharArray()) {
			aux += this.morse_alphabet.get(ch);
			aux += "|";
		}
		return aux;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", specialization: Telecommunication technology.";
	}

}
