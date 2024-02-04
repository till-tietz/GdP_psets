public class SelectionSort {
    // sortiere das Array a mit SelectionSort
    public static void sort(String[] a, char c) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (compare(a[j], a[minIndex], c) < 0) {
                    minIndex = j;
                }
            }
            // Swap
            String temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }

    public static void main(String[] argv) {
        // ueberpruefe Eingabeparameter
        if (argv.length == 0 || argv[0].length() != 1) {
            System.out.println("Fehler: es wird ein Buchstabe als Parameter erwartet");
            System.exit(-1);
        }

        // den buchstaben aus dem Parameter extrahieren
        char c = argv[0].charAt(0);

        // alle Zeilen ueber die Konsole einlesen
        String[] a = StdIn.readAllLines();

        // Array sortieren
        sort(a, c);

        // sortierte Liste ausgeben
        for (int i = 0; i < a.length; i++) {
            int frequency = getFrequency(a[i], c);
            System.out.println(frequency + ": " + a[i]);
        }
    }

    // Compare two strings based on the frequency of the given letter
    private static int compare(String s1, String s2, char searchLetter) {
        int freqS1 = getFrequency(s1, searchLetter);
        int freqS2 = getFrequency(s2, searchLetter);

        return Integer.compare(freqS2, freqS1);
    }

    // Get the frequency of a given letter in a string
    private static int getFrequency(String s, char searchLetter) {
        int frequency = 0;
        for (char c : s.toCharArray()) {
            if (c == searchLetter) {
                frequency++;
            }
        }
        return frequency;
    }
}
