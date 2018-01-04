package eyjavaexam;

/**
 * 
 * This test is the sole property of EY and may not be shared without EY's
 * express consent. All rights reserved.
 *
 */
public class CivilWarCipher {

	public String key;

	public Alphabet alphabet = null;

	public CivilWarCipher(String key, Alphabet alphabet) {
		this.key = key;
		this.alphabet = alphabet;
	}

	public String encrypt(String text) {
		
		//final encrypted msg will be appended in lineUp
		StringBuilder lineUp = new StringBuilder(); 
		
		char[] textArray = text.toCharArray();
		char[] validchars = alphabet.validChars;
		char[] keyArray = key.toCharArray();
		int k = 0;
		
		// loop over all the characters of text
		for (int i = 0; i < textArray.length; i++) {  
			int textValue = -1;
			int keyValue = -1;
			boolean keyflag = true;
			for (int j = 0; j < validchars.length; j++) {
				if (textArray[i] == validchars[j]) {
					textValue = j;
				}
				if (keyArray[k] == validchars[j] && keyflag) {
					keyValue = j;
					keyflag = false;
					k++;
					// return back to start of key
					if (key.length() == k) {  
						k = 0;
					}
				}
			}
			keyflag = true;
			int totalValue = textValue + keyValue;
			
			// when the character in the text is present in the validachars
			if (textValue >= 0) {
				if (totalValue < validchars.length) {
					lineUp.append(validchars[totalValue]);
				} else {
					int index = totalValue - validchars.length;
					lineUp.append(validchars[index]);
				}
			} else {
				if (k == 0) {
					k = key.length() - 1;
				} else {
					k--;
				}
			}
		}
		//System.out.println(lineUp);
		
		return lineUp.toString();
	}

	public String decrypt(String text) {
		
		//final dencrypted msg will be appended in originalData
		StringBuilder originalData = new StringBuilder();
		
		char[] textArray = text.toCharArray();
		char[] validchars = alphabet.validChars;
		char[] keyArray = key.toCharArray();
		int k = 0;
		
		// loop over all the characters of text
		for (int i = 0; i < textArray.length; i++) {
			int decryptValue = -1;
			int keyValue = -1;
			boolean keyflag = true;
			for (int j = 0; j < validchars.length; j++) {
				if (textArray[i] == validchars[j]) {
					decryptValue = j;
				}
				if (keyArray[k] == validchars[j] && keyflag) {
					keyValue = j;
					keyflag = false;
					k++;
					// return back to start of key
					if (key.length() == k) {
						k = 0;
					}
				}
			}
			int finalValue = decryptValue - keyValue;
			
			// if we had to wrap around during encryption
			if (finalValue >= 0) {
				originalData.append(validchars[finalValue]);
			} else {
				int index = finalValue + validchars.length;
				originalData.append(validchars[index]);
			}
		}
		//System.out.println(originalData);
		
		return originalData.toString();
	}
}