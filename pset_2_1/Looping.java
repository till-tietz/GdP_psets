import java.math.BigInteger;

public class Looping {

    // reverse number method for long 
    // modulo 10 'peels off' the last digit of the number 
    // R * 10 'opens up' a decimal place for the last digit of the number to slot into (i.e. digits are left shifted)
    // number / 10 removes the last digit from the number
    public static long reverse_long(long N) {
        long reversed = 0;
        while (N != 0) {
            reversed = reversed * 10 + N % 10;
            N /= 10;
        }
        return reversed;
    }

    // reverse number method for BigInteger
    // see above comments 
    public static BigInteger reverse_big(BigInteger N){
        BigInteger reversed = BigInteger.ZERO;
        while(N.compareTo(BigInteger.ZERO) != 0){
            BigInteger remainder = N.remainder(BigInteger.TEN);
            reversed = reversed.multiply(BigInteger.TEN);
            reversed = reversed.add(remainder);
            N = N.divide(BigInteger.TEN);
        }
        return reversed;
    }

    public static void main(String[] args){
        // check if upper bound provided as argument 
        if(args.length == 0){
            System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
            System.exit(-1);
        }

        // parse upper bound input
        long bound = Long.parseLong(args[0]);

        // check if upper bound in allowed range
        if(bound < 1 || bound > 100000){
            System.out.println("Obergrenze muss >= 1 und <= 100000 sein.");
            System.exit(-1);
        }

        // extra functionality to check beyond overflow
        boolean check_past_overflow = false;
        if(args.length == 2){
            check_past_overflow = args[1].equals("x");
        } 

        // container for numbers that do eventually producde palindromes without overflow check
        String past_overflow_out = "";

         // begin iteration
         for(long n = 1; n < bound + 1; n++){
            boolean overflow = false;
            // set value and iterations 
            long iter = 0;
            long N = n;

            while(true){
                // increment iterations and reverse number 
                iter += 1;
                long R = reverse_long(N);
                // stop if iterations > 100 
                if(iter > 100){
                    System.out.println(n);
                    break;
                // stop if addition causes overflow (record values for checking beyond overflow point)
                } else if(N > Long.MAX_VALUE - R){
                    System.out.println(n);
                    overflow = true;
                    break;
                } else {
                    // check if new number is a palindrome and break if true 
                    N = N + R;
                    R = reverse_long(N);
                    if(N == R){
                        break;
                    } 
                }
            }

            // if checking beyond overflow point
            if(overflow && check_past_overflow){
                BigInteger N_big = BigInteger.valueOf(N);
                BigInteger R_big;

                while(true){
                    if(iter > 100){
                        break;
                    } else {
                        R_big = reverse_big(N_big);
                        N_big = N_big.add(R_big);
                        R_big = reverse_big(N_big);
                        if(N_big.equals(R_big)){
                            past_overflow_out = n + " braucht " + iter + " Iterationen bis zum Palindrom " + R_big;
                            break;
                        }
                        iter += 1;
                    }
                }
            }
        }
        if(check_past_overflow && !past_overflow_out.equals("")){
            System.out.println(past_overflow_out);
        }
    }
}