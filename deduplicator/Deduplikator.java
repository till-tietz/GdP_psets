public class Deduplikator {

    // helper: powers of 10 helper for scientific notation
    public static double power10(int exp) {
        double result = 1.0;
        if(exp >= 0) {
            for (int i = 0; i < exp; i++) {
                result *= 10.0;
            }
        } else {
            for (int i = 0; i > exp; i--) {
                result /= 10.0;
            }
        }
        return result;
    }

    // helper: turn String into double 
    public static double stringToDouble(String s) {
        // scientific notation case 
        int e = s.indexOf("E");
        if (e != -1) {
            String exp = s.substring(e + 1);
            s = s.substring(0,e);
            return Double.parseDouble(s) * power10(Integer.parseInt(exp));
        }
        // regular case
        return Double.parseDouble(s);
    } 

    public static void main(String args[]) {
        // turn Strings into double 
        double[] numbers = new double[args.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = stringToDouble(args[i]);
        }

        // print unique numbers >> not very efficient but it works 
        // we should sort before or use a more efficient data structure 
        for (int i = 0; i < numbers.length; i++) {
            boolean duplicated = false;
            for (int j = 0; j < i; j++) {
                if (numbers[i] == numbers[j]) {
                    duplicated = true;
                    break;
                }
            }
            if (!duplicated) {
                System.out.println(numbers[i]);
            }
        }
    }
}