/*
 * Class: CMSC203 CRN 46622
 * Program: Assignment 3
 * Instructor: Grigoriy Grinberg
 * Summary of Description: The objective of the assignment is to update
 * a manager class with methods that check if a string is in bounds, encrypt,
 * and decrypt a string with the Caesar and Bellaso Ciphers. The program will
 * be tested with JavaFX and JUnit classes.
 * Due Date: 07/10/2022 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Camilo Trejos
*/

package Assignment3;

/**
 * The CryptoManager class has the methods to check if a string is out of bounds, encrypt, 
 * and decrypt a string using the Ceasar and Bellaso Cipher methods.
 */
public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;
	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters.
	 * @param var used as a boolean variable, if plainText is within allowable bounds this is set to false.
	 * @param plainText a string to be encrypted, if it is within the allowable bounds.
	 * @return true if all characters are within the allowable bounds, false if any character is outside.
	 */
	public static boolean stringInBounds(String plainText) 
	{
		boolean var = true;
		for (int i = 0; i < plainText.length(); i++)
		{
			if (plainText.charAt(i) > UPPER_BOUND || plainText.charAt(i) < LOWER_BOUND)
			{
				var = false;
				System.out.println("A character is out of range");
				break;
			}
		}
		return var;
	}
	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character.
	 * @param encrypted_text a string that holds the encrypted plainText.
	 * @param encrypt an int that holds the ASCII codes and gets shifted by key.
	 * @param shift an int that holds a shift key if encrypt + key is greater than UPPER_BOUND
	 * @return the encrypted string.
	 */
	public static String encryptCaesar(String plainText, int key) 
	{
		String encrypted_text = "";
		int encrypt, shift;
		for (int i = 0; i < plainText.length(); i++)
		{
			encrypt = (int)plainText.charAt(i);
			if ((encrypt + key) > UPPER_BOUND)
			{
				shift = key % RANGE;
				if ((shift + encrypt) > UPPER_BOUND)
						encrypt = encrypt + shift - UPPER_BOUND + LOWER_BOUND - 1;
				else
					encrypt = encrypt + shift;
			}
			else
				encrypt += key;
			encrypted_text += (char)encrypt;
		}
		return encrypted_text;	
	}
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @param encrypted_text a string that holds the encrypted plainText.
	 * @param key a string that holds modified bellasoStr to use for encryption.
	 * @param encrypt an int that holds the ASCII codes and gets shifted by key.
	 * @return the encrypted string.
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) 
	{
		String key, encrypted_text = "";
		int encrypt;
		key = modiftyBellasoStr(plainText, bellasoStr);
		for (int i = 0; i < plainText.length(); i++)
		{
			encrypt = ((int)plainText.charAt(i) + (int)key.charAt(i));
			if (encrypt > UPPER_BOUND && encrypt <= (UPPER_BOUND+RANGE))
				encrypt -= RANGE;
			else if (encrypt > (UPPER_BOUND+RANGE))
			{
				encrypt -= (UPPER_BOUND+RANGE) + 1;
				encrypt += LOWER_BOUND;
			}
			encrypted_text += (char)encrypt;
		}
		return encrypted_text;
	}
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted
	 * @param key an integer that specifies the offset of each character.
	 * @param decrypted_text a string that holds the decrypted encryptedText.
	 * @param decrypt an int that holds the ASCII codes and gets shifted by key.
	 * @param shift an int that holds a shift key if decrypt + key is greater than UPPER_BOUND.
	 * @return the plain text string.
	 */
	public static String decryptCaesar(String encryptedText, int key)
	{
		String decrypted_text = "";
		int decrypt, shift;
		for (int i = 0; i < encryptedText.length(); i++)
		{
			decrypt = (int)encryptedText.charAt(i);
			if ((decrypt - key) < LOWER_BOUND)
			{
				shift = key % RANGE;
				if ((decrypt - shift) < LOWER_BOUND)
					decrypt = decrypt - shift + UPPER_BOUND - LOWER_BOUND + 1;
				else
					decrypt = decrypt - shift;
			}
			else
				decrypt -= key;
			decrypted_text += (char)decrypt;
		}
		return decrypted_text;	
	}
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @param decrypted_text a string that holds the decrypted encryptedText.
	 * @param key a string that holds modified bellasoStr to use for encryption.
	 * @param decrypt an int that holds the ASCII codes and gets shifted by key.
	 * @return the decrypted string.
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr)
	{
		String key, decrypted_text = "";
		int decrypt;
		key = modiftyBellasoStr(encryptedText, bellasoStr);
		for (int i = 0; i < encryptedText.length(); i++)
		{
			decrypt = ((int)encryptedText.charAt(i) - (int)key.charAt(i));
			if (decrypt < LOWER_BOUND && decrypt >= (LOWER_BOUND-RANGE))
				decrypt += RANGE;
			else if (decrypt < (LOWER_BOUND-RANGE))
				decrypt += (LOWER_BOUND+UPPER_BOUND) + 1;
			decrypted_text += (char)decrypt;
		}
		return decrypted_text;
	}
	/**
	 * Modifies the string bellaoStr to be applicable to the Bellaso Cipher as the key.
	 * @param encryptedOrPlainText is either uppercase string to be encrypted or encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @param string_index counter for while loop that counts until the length of bellasoStr equals
	 * length of encryptedOrPlainText.
	 * @param string a string that is used to hold bellaoStr if length of bellaoStr is greater than
	 * length of encryptedOrPlainText.
	 * @return the modified bellaoStr.
	 */
	public static String modiftyBellasoStr(String encryptedOrPlainText, String bellasoStr)
	{
		int string_index = 0;
		if (bellasoStr.length() > encryptedOrPlainText.length())
		{
			String string = "";
			for (int i = 0; i < encryptedOrPlainText.length(); i++)
				string += bellasoStr.charAt(i);
			return string;
		}
		while (bellasoStr.length() != encryptedOrPlainText.length())
		{
			if (bellasoStr.length() != encryptedOrPlainText.length() && string_index <= (bellasoStr.length()-1))
			{
				bellasoStr += bellasoStr.charAt(string_index);
				string_index++;
			}
			else if (bellasoStr.length() != encryptedOrPlainText.length() && string_index > (bellasoStr.length()-1))
				string_index = 0;		
		}
		return bellasoStr;
	}
}