import java.math.BigInteger;

public class Nolindrom {

    // reverse number method for long 
    public static long reverse_num(long N){
        long R = 0;
        long number = N;
        while(number != 0){
            long remainder = number % 10;
            R = R * 10 + remainder;
            number /= 10;
        }
        return R;
    }

    // reverse number method for BigInteger
    public static BigInteger reverse_big(BigInteger N_big){
        BigInteger R = BigInteger.ZERO;
        BigInteger number = N_big;
        while(number.compareTo(BigInteger.ZERO) != 0){
            BigInteger remainder = number.remainder(BigInteger.TEN);
            R = R.multiply(BigInteger.TEN);
            R = R.add(remainder);
            number = number.divide(BigInteger.TEN);
        }
        return R;
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

        // extra functionality to check 
        boolean check_past_overflow = false;
        if(args.length == 2){
            check_past_overflow = args[1].equals("x");
        } 

        // container for numbers that do eventually producde palindromes without overflow check
        long wrong_number = 0;
        String past_overflow_out = "";

         // begin iteration
         for(long n = 1; n < bound + 1; n++){

            // set value and iterations 
            long N = n;
            int iter = 0;
            // overflow number and iterations if checking beyond overflow point
            long overflow_at = 0;
            int overflow_iter = 0;

            while(true){
                // increment iterations and reverse number 
                iter += 1;
                long R = reverse_num(N);
                // stop if iterations > 100 
                if(iter > 100){
                    System.out.println(n);
                    break;
                // stop if addition causes overflow (record values for checking beyond overflow point)
                } else if(N > Long.MAX_VALUE - R){
                    System.out.println(n);
                    overflow_at = N;
                    overflow_iter = iter;
                    break;
                } else {
                    // check if new number is a palindrome and break if true 
                    N = N + R;
                    R = reverse_num(N);
                    if(N == R){
                        break;
                    } 
                }
            }

            // if checking beyond overflow point
            if(overflow_at != 0 && wrong_number == 0 && check_past_overflow){
                BigInteger N_big = BigInteger.valueOf(overflow_at);
                BigInteger R_big = BigInteger.ZERO;

                while(true){
                    if(overflow_iter > 100){
                        break;
                    } else {
                        overflow_iter += 1;
                        R_big = reverse_big(N_big);
                        N_big = N_big.add(R_big);
                        R_big = reverse_big(N_big);
                        if(N_big == R_big){
                            wrong_number = n;
                            past_overflow_out = n + " braucht " + overflow_iter + " Iterationen bis zum Palindrom " + R_big;
                            break;
                        }
                    }
                }
            }
        }
        if(check_past_overflow && !past_overflow_out.equals("")){
            System.out.println(past_overflow_out);
        }
    }
}