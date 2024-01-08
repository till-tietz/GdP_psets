public class Caesar {
    public static void main(String[] args) {
        // parse shift parameter
        int shift = Integer.parseInt(args[0]);
        // parse messages to be encrypted and apply encryption
        for (int i = 1; i < args.length; i++) {
            String message = args[i];
            String encryptedMessage = encrypt(message, shift);
            System.out.println(encryptedMessage);
        }
    }

    // encryption helper
    private static String encrypt(String message, int shift) {
        // upper and lower case alphabet
        char[] upperCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        // turn message into array of characters
        char[] messageArray = message.toCharArray();
        // empty character array for encrypted message
        char[] result = new char[messageArray.length];
        // loop through message
        for (int j = 0; j < messageArray.length; j++) {
            char c = messageArray[j];
            // only apply encrpytion for letters in alphabet
            if (Character.isLetter(c)) {
                // decide which alphabet to use (upper or lower case)
                char[] alphabet = Character.isUpperCase(c) ? upperCaseAlphabet : lowerCaseAlphabet;
                // find the index of a given letter in the alphabet and apply the shift 
                // applying the modulo operator ensures that when shifting by multiples of 26
                // the letters remain unchanged (this ensures we handle shifts to the right beyond Z correctly)
                int index = (findIndex(alphabet, c) + shift) % 26;
                // if the remained is negative we have shifted to the left beyond A and must add 26 
                if (index < 0) {
                    index += 26;
                }
                result[j] = alphabet[index];
            } else {
                result[j] = c;
            }
        }

        return new String(result);
    }

    // helper to find position of character in alphabet 
    // could make this faster with a some sort of map or dictionary but this gets the job done
    private static int findIndex(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
}

