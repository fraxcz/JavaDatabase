import java.math.BigInteger;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CyberSecurityStudent extends Student {

	public CyberSecurityStudent(String first_name, String surname, LocalDate date_of_birth) {
		super(first_name, surname, date_of_birth);
		this.spec = Specialization.CyberSecurity;
	}

	@Override
	public String do_skill() {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			String fullname = this.firstName + this.surname;
			byte[] hashed_fullname = digest.digest(fullname.getBytes());
			BigInteger bigInt = new BigInteger(1, hashed_fullname);
			return bigInt.toString(16);
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + ", specialization: Cybersecurity.";
	}
	
}
