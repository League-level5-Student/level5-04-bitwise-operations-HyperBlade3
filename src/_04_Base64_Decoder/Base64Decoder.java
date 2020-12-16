package _04_Base64_Decoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Base64Decoder {
	/*
	 * Base 64 is a way of encoding binary data using text. Each number 0-63 is
	 * mapped to a character. NOTE: THIS IS NOT THE SAME AS ASCII OR UNICODE
	 * ENCODING! Since the numbers 0 through 63 can be represented using 6 bits,
	 * every four (4) characters will represent twenty four (24) bits of data. 4 * 6
	 * = 24
	 * 
	 * For this exercise, we won't worry about what happens if the total bits being
	 * converted do not divide evenly by 24.
	 * 
	 * If one char is 8 bits, is this an efficient way of storing binary data?
	 * (hint: no)
	 * 
	 * It is, however, useful for things such as storing media data inside an HTML
	 * file (for web development), so that way a web site does not have to look for
	 * an image, sound, library, or whatever in a separate location.
	 * 
	 * View this link for a full description of Base64 encoding
	 * https://en.wikipedia.org/wiki/Base64
	 */

	final static char[] base64Chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/' };

	// 1. Complete this method so that it returns the the element in
	// the base64Chars array that corresponds to the passed in char.
	public static byte convertBase64Char(char c) {
		for (int i = 0; i < base64Chars.length; i++) {
			if (base64Chars[i] == c) {
				return (byte) i;
			}
		}

		return -1;
	}

	// 2. Complete this method so that it will take in a string that is 4
	// characters long and return an array of 3 bytes (24 bits). The byte
	// array should be the binary value of the encoded characters.
	public static byte[] convert4CharsTo24Bits(String s) {

		String sixBits = "";
		ArrayList<String> sixBitsArr = new ArrayList<String>();

		int mask = 1;

		String[] bytes = new String[3];
		byte[] deciConv = new byte[3];

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < 6; j++) {
				sixBitsArr.add(Integer.toString((convertBase64Char(s.charAt(i)) >> j) & mask));
			}
			for (int j = sixBitsArr.size() - 1; j >= 0; j--) {
				sixBits += sixBitsArr.get(j);
			}

			sixBitsArr.clear();

		}

		bytes[0] = sixBits.substring(0, 8);
		bytes[1] = sixBits.substring(8, 16);
		bytes[2] = sixBits.substring(16, 24);

		for (int i = 0; i < bytes.length; i++) {

			byte ans = 0;

			int[] powers = new int[bytes[i].length()];

			powers[powers.length - 1] = 1;

			for (int j = powers.length - 2; j >= 0; j--) {
				powers[j] = powers[j + 1] * 2;

			}

			for (int j = 0; j < bytes[i].length(); j++) {
				if (Integer.parseInt(bytes[i].substring(j, j + 1)) == 1) {
					ans += powers[j];
				}

			}

			deciConv[i] = ans;

		}

		return deciConv;
	}

	// 3. Complete this method so that it takes in a string of any length
	// and returns the full byte array of the decoded base64 characters.
	public static byte[] base64StringToByteArray(String file) {
		String sixBits = "";
		ArrayList<String> sixBitsArr = new ArrayList<String>();
		int mask = 1;
		int curChar = 0;

		ArrayList<String> bytes = new ArrayList<String>();
		ArrayList<Byte> deciConv = new ArrayList<Byte>();

		for (int i = 0; i < file.length(); i++) {
			for (int j = 0; j < 6; j++) {
				sixBitsArr.add(Integer.toString((convertBase64Char(file.charAt(i)) >> j) & mask));
			}
			for (int j = sixBitsArr.size() - 1; j >= 0; j--) {
				sixBits += sixBitsArr.get(j);
			}

			sixBitsArr.clear();

		}

		for (int i = 8; i <= sixBits.length(); i += 8) {
			bytes.add(sixBits.substring(curChar, i));
			curChar += 8;
		}

		for (int i = 0; i < bytes.size(); i++) {

			byte ans = 0;

			int[] powers = new int[8];

			powers[powers.length - 1] = 1;

			for (int j = powers.length - 2; j >= 0; j--) {
				powers[j] = powers[j + 1] * 2;

			}

			for (int j = 0; j < bytes.get(i).length(); j++) {
				if (Integer.parseInt(bytes.get(i).substring(j, j + 1)) == 1) {
					ans += powers[j];
				}

			}

			deciConv.add(ans);

		}

		byte[] answer = new byte[deciConv.size()];

		for (int i = 0; i < deciConv.size(); i++) {
			answer[i] = deciConv.get(i);
		}

		return answer;

	}

}
