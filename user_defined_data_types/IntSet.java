import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Mengen nichtnegativer ganzer Zahlen in kompakter
 * Speicherrepraesentation: ob eine Zahl in der Menge enthalten
 * ist, wird durch EIN BIT im Speicher erfasst!
 * 
 *  @author Till Tietz
 *  @version 1.0
 * 
 * 
 * <br>
 * Beispiel:
 * <br>
 * <code>
 * <br>IntSet set = new IntSet(8);
 * <br>int a[] = { 1, 3, 4, 5 };
 * <br>set.include( a );
 * <br>
 * <br> ... +---+---+---+---+---+---+---+---+
 * <br> ... | 0 | 0 | 1 | 1 | 1 | 0 | 1 | 0 |
 * <br> ... +---+---+---+---+---+---+---+---+
 * <br></code>
 */
public class IntSet implements Iterable<Integer> {
    private int[] set;
    private int capacity;

    /**
	 * Konstruiert ein leere Zahlenmenge der Kapazitaet <code>n</code>:
	 * eine Menge, die (nichtnegative ganze) Zahlen im
	 * Bereich 0 bis n-1 als Elemente enthalten kann.
	 * 
	 * @param n die Kapazitaet der Menge
     * @return an IntSet
	 */
    public IntSet(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        }
        capacity = n;
        set = new int[(n + 31) / 32];
    }

    /**
	 * Ermittelt die Kapazitaet der Menge.
	 * 
	 * @return die Kapazitaet der Menge
	 */
    public int capacity() {
        return capacity;
    }

    /**
	 * Erzeugt aus <code>this</code> eine neue (identisch belegte) Zahlenmenge,
	 * die Werte im Bereich 0 bis n-1 als Elemente enthalten kann.
	 * 
	 * Die Originalmenge bleibt unveraendert!
	 * 
	 * @param n die Kapazitaet der Ergebnismenge
	 * @return die Ergebnismenge mit veraenderter Kapazitaet
	 */
    public IntSet resize(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("New capacity must be a positive integer");
        }

        IntSet newSet = new IntSet(n);

        int minCapacity = Math.min(this.capacity, n);
        for (int i = 0; i < minCapacity; i++) {
            if (contains(i)) {
                newSet.insert(i);
            }
        }

        return newSet;
    }

    /**
	 * Ermittelt, ob eine nicht-negative ganze Zahl in der Menge enthalten ist.
	 * 
	 * @param e eine nichtnegative ganze Zahl
	 * @return ist e in dieser Menge enthalten?
	 */
    public boolean contains(int e) {
        if (e < 0 || e >= capacity) {
            return false;
        }

        int index = e / 32;
        int bitPosition = e % 32;

        return (set[index] & (1 << bitPosition)) != 0;
    }

    /**
	 * Nimmt die Zahl <code>e</code> in diese Menge auf.
	 * 
	 * @param e eine nichtnegative ganze Zahl zwischen 0 und capacity
	 */
    public void insert(int e) {
        if (e < 0 || e >= capacity) {
            throw new IllegalArgumentException("Element out of bounds");
        }

        int index = e / 32;
        int bitPosition = e % 32;

        set[index] |= (1 << bitPosition);
    }

    /**
	 * Nimmt alle Elemente aus dem Array <code>es</code> in die Menge auf.
	 * 
	 * @param es ein Array von nichtnegativen ganzen Zahlen
	 */
    public void insert(int[] es) {
        for (int e : es) {
            insert(e);
        }
    }

    /**
	 * Entfernt die Zahl <code>e</code> aus dieser Menge.
	 * 
	 * @param e eine nichtnegative ganze Zahl zwischen 0 und capacity
	 */
    public void remove(int e) {
        if (e < 0 || e >= capacity) {
            throw new IllegalArgumentException("Element out of bounds");
        }

        int index = e / 32;
        int bitPosition = e % 32;

        set[index] &= ~(1 << bitPosition);
    }

    /**
	 * Entfernt alle Elemente aus dem Array <code>es</code> aus der Menge.
	 * 
	 * @param es ein Array von nichtnegativen ganzen Zahlen
	 */
    public void remove(int[] es) {
        for (int e : es) {
            remove(e);
        }
    }

    /**
	 * Berechnet die Komplementaermenge zu dieser Menge: die Menge gleicher
	 * Kapazitaet, die genau alle Elemente enthaelt, die nicht in
	 * <code>this</code> enthalten sind.
	 * 
	 * Originalmenge bleibt unveraendert !
	 * 
	 * @return die Komplementaermenge
	 */
    public IntSet complement() {
        IntSet complementSet = new IntSet(capacity);

        for (int i = 0; i < capacity; i++) {
            if (!contains(i)) {
                complementSet.insert(i);
            }
        }

        return complementSet;
    }

    /**
	 * Erzeuge aus <code>s1</code> und <code>s2</code> die Vereinigungsmenge
	 * <br>
	 * es wird eine Menge der Kapazitaet der groesseren
	 * Kapazitaet der beiden Mengen erzeugt
	 * <br>
	 * <code>s1</code> und <code>s2</code> bleiben unveraendert !
	 * 
	 * @param s1 Mengen, die
	 * @param s2 verknuepft werden sollen
	 * @return die Vereinigungsmenge
	 */
    public static IntSet union(IntSet s1, IntSet s2) {
        int newCapacity = Math.max(s1.capacity, s2.capacity);
        IntSet unionSet = new IntSet(newCapacity);

        for (int i = 0; i < newCapacity; i++) {
            if (s1.contains(i) || s2.contains(i)) {
                unionSet.insert(i);
            }
        }

        return unionSet;
    }

    /**
	 * Erzeuge aus <code>s1</code> und <code>s2</code> die symmetrische
	 * Differenzmenge.
	 * 
	 * Die Eingabemengen bleiben unveraendert!
	 * 
	 * @param s1 erste Menge
	 * @param s2 zweite Menge
	 * @return die symmetrische Differenzmenge
	 */
    public static IntSet intersection(IntSet s1, IntSet s2) {
        int newCapacity = Math.min(s1.capacity, s2.capacity);
        IntSet intersectionSet = new IntSet(newCapacity);

        for (int i = 0; i < newCapacity; i++) {
            if (s1.contains(i) && s2.contains(i)) {
                intersectionSet.insert(i);
            }
        }

        return intersectionSet;
    }

    /**
	 * Erzeugt aus <code>s1</code> und <code>s2</code> die Differenzmenge mit
	 * der Kapazitaet von s1.
	 * 
	 * Beide Eingabemengen bleiben unveraendert!
	 * 
	 * @param s1 erste Menge
	 * @param s2 zweite Menge
	 * @return die Differenzmenge
	 */
    public static IntSet difference(IntSet s1, IntSet s2) {
        int newCapacity = Math.max(s1.capacity, s2.capacity);
        IntSet differenceSet = new IntSet(newCapacity);

        for (int i = 0; i < newCapacity; i++) {
            if (s1.contains(i) && !s2.contains(i)) {
                differenceSet.insert(i);
            }
        }

        return differenceSet;
    }

    /**
	 * Stringrepraesentation der Bits dieser Menge beginnend mit Index 0,
	 * etwa "01011100".
	 * 
	 * @return Stringrepraesentation der Bits der Menge
	 */
    public String bits() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < capacity; i++) {
            int index = i / 32;
            int bitPosition = i % 32;
            result.append((set[index] & (1 << bitPosition)) != 0 ? '1' : '0');
        }

        return result.toString();
    }

    /**
	 * Ermittelt die Stringrepraesentation dieser Menge, etwa "{1, 3, 4, 6}".
	 * 
	 * @return Stringrepraesentation der Menge
	 */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");

        boolean isFirstElement = true;
        for (int i = 0; i < capacity; i++) {
            if (contains(i)) {
                if (!isFirstElement) {
                    result.append(", ");
                }
                result.append(i);
                isFirstElement = false;
            }
        }

        result.append("}");

        return result.toString();
    }

    /**
	 * Erzeugt einen Iterator, mit dem ueber die Menge iteriert werden kann:
	 * <br>
	 * <code>
	 * <br>for (IntSet.Iterator it = menge.iterator(); it.hasNext(); )
	 * <br>         { ... it.next() ... }
	 * </code>
	 * 
	 * @return ein Iterator auf diese Menge
	 */
    @Override
    public Iterator<Integer> iterator() {
        return new IntSetIterator(this);
    }

    /**
	 * IntSet Mengen-Iterator
	 */
    private class IntSetIterator implements Iterator<Integer> {
        private int currentIndex;

        /**
		 * Erzeugt einen Iterator ueber <code>s</code>.
		 * 
		 * @param s die Menge, ueber die iteriert werden soll
		 */
        public IntSetIterator(IntSet intSet) {
            currentIndex = 0;
        }

        /**
		 * Ermittelt, ob noch weitere Elemente in der Menge existieren.
         * 
         * @return true or false 
		 */
        @Override
        public boolean hasNext() {
            while (currentIndex < capacity && !contains(currentIndex)) {
                currentIndex++;
            }
            return currentIndex < capacity;
        }

        /**
		 * Gibt das naechste Element zurueck und setzt den Iterator weiter.
		 * 
		 * @return das naechste Element
		 */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currentIndex++;
        }
    }
}




