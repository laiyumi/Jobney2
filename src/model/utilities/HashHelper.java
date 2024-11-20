package model.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public interface HashHelper {

	String salt = createSalt();

	// salt is random data that is used as an additional input to hash password
	static String createSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	// hash a password
	public static String hashPassword(String passwordToHash) {

		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static boolean isStrongPassword(String password) {
		// Length rule
		if (password.length() < 8 || password.length() > 20) {
			return false;
		}

		// Check for at least one upper case, one lower case, one digit and one special
		// character
		boolean upperCaseFlag = false;
		boolean lowerCaseFlag = false;
		boolean digitFlag = false;
		boolean specialFlag = false;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c)) {
				upperCaseFlag = true;
			} else if (Character.isLowerCase(c)) {
				lowerCaseFlag = true;
			} else if (Character.isDigit(c)) {
				digitFlag = true;
			} else if (!Character.isLetterOrDigit(c)) {
				specialFlag = true;
			}

			// If all flags are true, no need to check further
			if (upperCaseFlag && lowerCaseFlag && digitFlag && specialFlag) {
				break;
			}
		}

		return upperCaseFlag && lowerCaseFlag && digitFlag && specialFlag;
	}

	public static String checkPasswordStrength(String password) {
		// Length rule
		if (password.length() < 8 || password.length() > 20) {
			return "Password length must be between 8 and 20 characters.";
		}

		// Check for at least one upper case, one lower case, one digit and one special
		// character
		boolean upperCaseFlag = false;
		boolean lowerCaseFlag = false;
		boolean digitFlag = false;
		boolean specialFlag = false;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c)) {
				upperCaseFlag = true;
			} else if (Character.isLowerCase(c)) {
				lowerCaseFlag = true;
			} else if (Character.isDigit(c)) {
				digitFlag = true;
			} else if (!Character.isLetterOrDigit(c)) {
				specialFlag = true;
			}
		}

		if (!upperCaseFlag) {
			return "Password must have at least one uppercase letter.";
		} else if (!lowerCaseFlag) {
			return "Password must have at least one lowercase letter.";
		} else if (!digitFlag) {
			return "Password must have at least one digit.";
		} else if (!specialFlag) {
			return "Password must have at least one special character.";
		} else {
			return "Password is strong.";
		}
	}

}
