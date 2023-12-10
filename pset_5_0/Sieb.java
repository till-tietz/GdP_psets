public class Sieb {
    
    //method: sieve  
    private static boolean[] sieve(int N){
        boolean[] p = new boolean[N];
        
        // initialization: no factors found yet
        for (int i= 2; i<N; i++){
            p[i]= true; 
        }
        
        for (int i= 2; i*i < N; i++) {
            if (p[i]) {
                // i is prime number -> leave p[i] == true
                // mark multiples of i as nonprime
                for (int n = 2; n*i<N; n++){
                    p[n*i]= false;
                }
            }
        }
        return p;
    }

    //method: check if desired number of primes found  
    private static boolean check_primes(int bound, boolean[] p){
        int prime_count = 0;

        for(int i = 2; i < p.length; i++){
            if(p[i]){
                prime_count += 1;
            }
            if(prime_count == bound){
                break;
            }
        }

        boolean desired_prime_count = prime_count == bound;
        return desired_prime_count;
    } 

    //method: print desired number of primes
    private static void print_primes(int bound, boolean[] p){
        int n_printed = 0; 
        int i = 2;
        do{
            if(p[i]){
                n_printed += 1;
                System.out.println(i);
            }
            i += 1;
        }while(n_printed != bound);
    }

    // main functionality 
    public static void main(String[] args){
        //check if upper bound parameter supplied
        if(args.length == 0){
            System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
            System.exit(-1);
        }

        //check if upper bound is in range
        int bound = Integer.parseInt(args[0]);
        if(bound < 1){
            System.out.println("Obergrenze muss >= 1 sein.");
            System.exit(-1);
        }

        //run sieve until desired number of primes are found
        int N = 9;
        boolean[] sieve_result;
        do{
            sieve_result = sieve(N);
            N = N*10;
        } while(!check_primes(bound,sieve_result));

        //print primes
        print_primes(bound, sieve_result);
    }
}