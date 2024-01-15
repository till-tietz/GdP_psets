public class Roman {

    static int closestNumIdx(int[] nums, int n) {
        if (n > 1000) {
            return 12;
        }

        int closest = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                closest = i;        
            } else {
                break;
            }
        }

        return closest;
    }

    static String roman(int n, 
                        String roman, 
                        int[] numArabic,  
                        String[] numRoman) {
        if (n == 0) {
            return roman;
        }

        int idx = closestNumIdx(numArabic, n);
        int q = n / numArabic[idx];
        roman = roman + numRoman[idx].repeat(q);

        return roman(n % numArabic[idx], roman, numArabic, numRoman);
    }


    public static void main(String args[]) {

        int numArabic[] = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
        String numRoman[] = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        String roman = "";

        if (args.length == 0) {
            System.out.println("Bitte eine Zahl als Parameter angeben.");
            return;
        }
        
        int N = Integer.parseInt(args[0]);

        if (N <= 0 || N > 5000) {
            System.out.println("Die Zahl muss zwischen 1 und 5000 liegen.");
            return;
        } 

        System.out.println(roman(N, roman, numArabic, numRoman));   
    }
}