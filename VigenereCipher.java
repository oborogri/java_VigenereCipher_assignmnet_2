import java.util.Arrays;
/**
 * Class to generate and test Vigenere Cipher 
 * 
 * @author Grigore Oboroceanu
 * @version 23.3.2016
 * 
 */
public class VigenereCipher
{
    private static final int NUMBER_CHARS = 26;
    //number of English alphabet chars used to generate vigenere Table 
    private static char [][] vigenereTable;
    //2d char array used to generate vigenere Table 
    private static char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    //char array of alphabet chars
    /**
     * Constructs a new VigenereCipher object.
     */
    public VigenereCipher()
    {
        generateVigenereTable();
    }

    /**
     * Generates the Vigenere Table.
     */
    public static void generateVigenereTable()
    {
        //instantiates 2d char array vigenereTable of size 26 (number of alphabet leters)
        vigenereTable = new char [NUMBER_CHARS][NUMBER_CHARS];
        //loop through the 2d array and populate with []chars elements
        for (int i = 0; i < NUMBER_CHARS; i++) {
            for (int j = 0; j < NUMBER_CHARS; j++) {
                vigenereTable[i][j] = chars[(i + j)%(NUMBER_CHARS)];
            }
        }    
    }

    /**
     * Use the keyword to generate a key.
     * Key length is the same as the length of the plain text.
     * The key comprises repeating keyword. 
     * The last keyword in the key is truncated if necessary.
     *
     * @param keyLength The number of characters in the key.
     * @param plainText The plain text for which a key will be generated.
     * @return The key for use in encrypting a message whose length matches the key length.
     */
    public String generateKey(String keyword, int keyLength)
    {
        //checks that keyLength has positive value
        if (keyLength < 0) {
            System.out.println("Please enter a positive number");
        }

        StringBuilder key = new StringBuilder();
        //StringBulider used to build the key string
        while(key.length() < keyLength) {
            int j = 0;
            while (j < keyword.length() && key.length() < keyLength) {
                key.append(keyword.charAt(j++));
                //loop untill untill keyLength is reached  
            }
        }
        return key.toString();
    }

    /**
     * Encrypts plain text (message text) under the key using the Vigenere Table.
     * 
     * @param plainText The plain text (message text) to be encrypted
     * @param key The key under which the plain text is encrypted
     * @return The cipher text created by encrypting the plaintext under the generated key.
     */
    public String encrypt(String key, String plainText)
    {
        //using an empty string to build encryprted message 
        String res = "";
        //convert text to uppercase
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();
        //loop from 0 to length plainText 
        for (int i = 0, j = 0; i < plainText.length(); i++) {
            //encrypted message string gets the chars at intersection of (plainText + key) modulus number_chars  
            res += (char)((plainText.charAt(i) + key.charAt(j))%NUMBER_CHARS + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    /**
     * Decrypts cipher text under the key using the Vigenere Table.
     * 
     * @param key The key under which the cipher text is deccrypted
     * @param cipherText The cipher text to be decrypted.
     * @return The plain text obtained by decrypting the cipher text under the generated key.
     */

    public String decrypt(String key, String cipherText)
    {
        String res = "";
        //empty string to build decrypted meesage
        cipherText = cipherText.toUpperCase();
        //convert input text to uppercases
        key = key.toUpperCase();
        //loop from 0 to length cipher text        
        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            res += (char)((cipherText.charAt(i) - key.charAt(j) + NUMBER_CHARS)% NUMBER_CHARS + 'A');
            //encrypted message chars are reversed by key chars to find position of initial text   
            j = ++j % key.length();
        }
        return res;
    }

    /**
     * Print the vigenere table.
     */
    public void printVigenereTable()
    {
        //loop through the vigenereTable array and print each element
        for(int i = 0; i < NUMBER_CHARS; i++){
            for(int j = 0; j < NUMBER_CHARS; j++){
                System.out.print(vigenereTable[i][j] + " ");
            }
            System.out.print("\n"); // a carriage return after each line of letters
        }
    }

    /**
     * Print the key.
     * 
     * @param key The key used for encryption and decryption.
     */
    public void printKey(String key)
    {
        System.out.println(key);
    }

    /**
     * Print the plain text (message text).
     * 
     * @param plainText The plain text (message text).
     */
    public void printMessage(String plainText)
    {
        System.out.println(plainText);
    }

    /**
     * Print the cipher text.
     * 
     * @param cipherText The encrypted message.
     */
    public void printCipher(String cipherText)
    {
        System.out.println(cipherText);
    }

    /**
     * Test method by: 
     *  Generating and printing Vigenere Table,
     *  Generating the key using the keyword,
     *  Encrypting a message (plain text),
     *  Decrypting the cipher text.
     *  Printing the table, key, message, ciphertext & decrypted ciphertext.
     */
    public void testVigenere()
    {
        generateVigenereTable();

        String messageText = "MICHIGANTECHNOLOGICALUNIVERSITY";
        String key = generateKey("HOUGHTON", messageText.length());
        String cipherText = encrypt(key, messageText);
        String decrypted = decrypt(key, cipherText);

        printVigenereTable();
        printMessage(messageText);
        printKey(key);
        printCipher(cipherText);
        printMessage(decrypted);
    }
}