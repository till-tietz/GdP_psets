public class Bigs {

    // addiert die Ziffernfelder a und b
    public static int[] add (int[] a, int[] b) { /* TODO */ }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    // bei sehr langen Zahlen soll das Format verwendet werden, welches auch von
    // bc (s.u.) benutzt wird: Umbruch nach 68 Ziffern mit einem \ am Ende
    static void print (int[] n) {
        String ret = "";
        for(int i = n.length; i == 0; i--){
            ret = ret + n[i];
            if((n.length - i + 1) % 68 == 0){
                ret = ret + "\n";
            }
        }
        ret = ret + "\\";
        System.out.println(ret);
    }

    // konstruiert ein einstelliges Ziffernfeld aus der Ziffer d
    static int[] digit(int d) {
        int[] ret = {d};
        return ret;
    }
    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null() {
        int[] ret = {};
        return ret;
    }

    // konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
    static int[] One() {
        int[] ret = {1};
        return ret;
    }

    // Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
    static int mod10(int[] n) {
        return n[0];
    }

    // ganzzahliger Quotient bei Division durch 10
    static int[] div10(int[] n) {
        int[] ret = new int[n.length - 1];
        for(int i = 1; i < ret.length; i++){
            ret[i] = n[i];
        }
        return ret;
    }

    // Umwandlung einer beliebigen int-Zahl in ein Ziffernfeld
    static int[] fromInt(int n) {
        int digits = String.valueOf(n).length();
        int[] ret = new int[digits];
        int i = 0;
        while (n > 0) {
            ret[i] = n%10;
            n = n/10;
            i += 1;
        }
        return ret;
    }

    // kopiert den Wert von n
    static int[] copy(int[] n) {
        int[] ret = new int[n.length];
        for(int i = 0; i < n.length; i++){
            ret[i] = n[i];
        }
        return ret;
    }

    // multipliziert das Ziffernfeld n mit einer (einstelligen!) int-Zahl
    static int[ ] times(int[ ] n, int d) { /* TODO */ }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n) {
        int[] ret = new int[n.length + 1];
        ret[0] = 0;
        for(int i = 1; i < ret.length; i++){
            ret[i] = n[i-1];
        }
        return ret;
    }

    // multipliziert zwei Ziffernfeld
    static int[ ] times(int[ ] a, int[ ] b) { /* TODO */ }

    // Quadratzahl eines Ziffernfeldes
    static int[ ] square(int[ ] a) { /* TODO */ }

    // Kubikzahl eines Ziffernfeldes
    static int[ ] cubic(int[ ] a) { /* TODO */ }

    // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
    static boolean less (int[ ] a, int[ ] b) { /* TODO */ }

    // Test auf Gleichheit zweier Ziffernfelder
    static boolean equal (int[ ] a, int[ ] b) { /* TODO */ }

    // Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
    // mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
    // keine fuehrenden Nullen (ausser bei Null selbst)
    static boolean ok (int[ ] n) { /* TODO */ }
    
    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
    static void maxDigit(int[] n) { /* TODO */ }
    
    public static void main (String[] s) {
        int[ ] a = One();
        for (int i=0; i<33222; ++i) { a = times(a, 2); }
        System.out.println("2^33222 hat " + a.length + " Stellen");
        print(a);
        System.out.println();
        int[ ] b = fromInt(13);
        int[ ] c = copy(b);
        for (int i=1; i<8978; ++i) { c = times(c, b); }
        System.out.println("13^8978 hat " + c.length + " Stellen");
        print(c);
        System.out.println();
        System.out.println(less(a, c)); // beantwortet die Frage aus der
        // Aufgabenueberschrift
        maxDigit(a);
        maxDigit(c);
    }


}