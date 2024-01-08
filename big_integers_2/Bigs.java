public class Bigs {

    //helper function for add 
    //let x be the greater of the two numbers 
    static int[] getSum (int[] x, int[] y){
        
        //initialize sum arry to length x at first 
        //we'll deal with carry on the last digit later
        int[] sum = new int[x.length];
        int carry = 0;
        int sum_i = 0;

        for(int i = 0; i < y.length; i++){
            sum_i = x[i] + y[i] + carry;
            sum[i] = sum_i % 10;
            carry = sum_i / 10;
        }

        if(x.length > y.length){
            for(int i = y.length; i < x.length; i++){
                sum_i = x[i] + carry;
                sum[i] = sum_i % 10;
                carry = sum_i / 10;
            }
        }

        //if we need to carry on last digit we'll 
        //create a new arry, copy the values of sum and add 1 
        //in last index of new array 
        if(carry == 1){
            int[] sum_new = new int[sum.length + 1];
            for(int i = 0; i < sum.length; i++){
                sum_new[i] = sum[i];
            }
            sum_new[sum_new.length - 1] = 1;
            return sum_new;
        } else {
            return sum;
        }   
    }

    // addiert die Ziffernfelder a und b
    static int[] add (int[] a, int[] b) {
        if (a.length >= b.length){
            return getSum(a,b);
        } else {
            return getSum(b,a);
        }
    }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    // bei sehr langen Zahlen soll das Format verwendet werden, welches auch von
    // benutzt wird: Umbruch nach 68 Ziffern mit einem \ am Ende
    static void print (int[] n) {
        String ret = "";
        for(int i = n.length - 1; i >= 0; i--){
            ret = ret + n[i];
            if((n.length - i) % 68 == 0 && i != 0){
                ret = ret + "\\\n";
            }
        }
        System.out.println(ret);
    }

    // konstruiert ein einstelliges Ziffernfeld aus der Ziffer d
    static int[] digit(int d) {
        int[] ret = {d};
        return ret;
    }

    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null() {
        int[] ret = {0};
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
        if(n.length == 1) {
            return Null();
        }

        int[] ret = new int[n.length - 1];
        for(int i = 0; i < ret.length; i++){
            ret[i] = n[i+1];
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
    static int[] times(int[] n, int d) {
        if (d == 0) {
            return Null();
        }
        int[] ret = n;
        for(int i = 1; i < d; i++){
            ret = add(ret,n);
        }
        return ret;
    }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n) {
        int[] ret = new int[n.length + 1];
        ret[0] = 0;
        for(int i = 1; i < ret.length; i++){
            ret[i] = n[i-1];
        }
        return ret;
    }

    //helper function for times
    //let x be the larger of the numbers 
    static int[] getProd(int[] x, int[] y){
        int[] prod = Null();
        for(int i = 0; i < y.length; i++){
            int[] prod_i = times(x,y[i]); 
            if(i > 0){
                for(int j = 1; j <= i; j++){
                    prod_i = times10(prod_i);
                }
            }
            prod = add(prod,prod_i);
        }
        return prod;
    } 

    // multipliziert zwei Ziffernfelder
    static int[] times(int[] a, int[] b) {
        if (a.length >= b.length){
            return getProd(a,b);
        } else {
            return getProd(b,a);
        }
    }

    // Quadratzahl eines Ziffernfeldes
    static int[] square(int[] a) {
        return times(a,a);
    }

    // Kubikzahl eines Ziffernfeldes
    static int[] cubic(int[] a) {
        int[] ret = square(a);
        return times(ret,a);
    }

    // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
    static boolean less (int[] a, int[] b) {
        int aLength = a.length;
        int bLength = b.length;
        boolean isSmaller = false;
        if(aLength < bLength){
            isSmaller = true;
        } else if(aLength > bLength){
            isSmaller = false;
        } else {
            for(int i = aLength - 1; i >= 0; i--){
                if(a[i] < b[i]){
                    isSmaller = true;
                    break;
                }
            }
        }
        return isSmaller;
    }

    // Test auf Gleichheit zweier Ziffernfelder
    static boolean equal (int[] a, int[] b) {
        int aLength = a.length;
        int bLength = b.length;
        boolean isEqual = true;
        if(aLength < bLength){
            isEqual = false;
        } else if(aLength > bLength){
            isEqual = false;
        } else {
            for(int i = aLength - 1; i >= 0; i--){
                if(a[i] != b[i]){
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;  
    }

    // Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
    // mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
    // keine fuehrenden Nullen (ausser bei Null selbst)
    static boolean ok (int[] n) {
        int nLength = n.length;
        if(nLength == 0){
            return false;
        }

        if(nLength > 1 && n[nLength - 1] == 0){
            return false;
        }

        for(int i = 0; i < nLength; i++){
            if(!(n[i] >= 0 && n[i] <= 9)){
                return false;
            }
        }
        return true;
    }

    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
    static void maxDigit(int[] n) {
        int[] count = {0,0,0,0,0,0,0,0,0,0};
        for(int i = 0; i < n.length; i++){
            count[n[i]] += 1;
        }
        int max = count[0];
        for(int i = 1; i < 10; i++){
            if(count[i] > max){
                max = count[i];
            }
        }
        
        for(int i = 0; i < 10; i++){
            if(count[i] == max){
                System.out.println(i + ": " + max);
                break;
            }
        }
        return;
    }
    
    public static void main (String[ ] s) {
        int[] a = One();

        for (int i=0; i<33222; ++i) {
            a = times(a, 2);
        }

        System.out.println("2^33222 hat " + a.length + " Stellen");
        print(a); 
        System.out.println(); 

        int[] b = fromInt(13);
        int[] c = copy(b);
        
        for (int i=1; i<8978; ++i) {
            c=times(c, b);
        }
        
        System.out.println("13^8978 hat " + c.length + " Stellen");
        print(c); 
        System.out.println(); 

        System.out.println(less(a, c)); // beantwortet die Frage aus der Aufgabenueberschrift
                maxDigit(a);
                maxDigit(c);
  }


}