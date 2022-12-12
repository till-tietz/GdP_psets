public class test {
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

    static int[] times(int[] n, int d) {
        int[] ret = n;
        for(int i = 1; i < d; i++){
            ret = add(ret,n);
        }
        return ret;
    }

    static int[] times10(int[] n) {
        int[] ret = new int[n.length + 1];
        ret[0] = 0;
        for(int i = 1; i < ret.length; i++){
            ret[i] = n[i-1];
        }
        return ret;
    }

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

    static void print (int[] n) {
        String ret = "";
        for(int i = n.length - 1; i >= 0; i--){
            ret = ret + n[i];
            if((n.length - i) % 68 == 0){
                ret = ret + "\n";
            }
        }
        if(n.length > 68){
            ret = ret + "\\";
        }
        System.out.println(ret);
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

    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null() {
        int[] ret = {0};
        return ret;
    }

     // multipliziert zwei Ziffernfelder
     static int[] times(int[] a, int[] b) {
        if (a.length >= b.length){
            return getProd(a,b);
        } else {
            return getProd(b,a);
        }
    }

    static int[] square(int[] a) {
        return times(a,a);
    }

    // Kubikzahl eines Ziffernfeldes
    static int[] cubic(int[] a) {
        int[] ret = square(a);
        return times(ret,a);
    }

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

    public static void main (String[] args) {
        int[] x = {5,7};
        int[] y = {5,7};
        int[] z = {5,1,7,2,2,2,3};

        int[] ret = cubic(x);
        print(ret);

        System.out.println(equal(y,x));
        System.out.println(ok(z));
        maxDigit(z);
    }
}