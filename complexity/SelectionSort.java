public class SelectionSort {
    // Array mit SelectionSort sortieren
    public static void sort(String[] a, char c) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (compare(a[j], a[minIndex], c) < 0) {
                    minIndex = j;
                }
            }
            // vertauschen 
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

    // zwei strings nach haeufigkeit eines buchstabens vergleichen
    private static int compare(String s1, String s2, char searchLetter) {
        int freqS1 = getFrequency(s1, searchLetter);
        int freqS2 = getFrequency(s2, searchLetter);

        return Integer.compare(freqS2, freqS1);
    }

    // haeufigkeit eines buchstabens in string ermitteln
    private static int getFrequency(String s, char searchLetter) {
        char[] sArray = s.toCharArray();
        int frequency = 0;
        for (int i = 0; i < sArray.length; i++) {
            char c = sArray[i];
            if (c == searchLetter) {
                frequency++;
            }
        }
        return frequency;
    }
}
