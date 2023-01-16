public class Gray {

    public static int toGray (int n) {

        String integer = Integer.toBinaryString(n);
        String gray = "";
        gray = gray + integer.charAt(0);

        for (int i = 1; i < integer.length(); i++){
            int v1 = integer.charAt(i - 1);
            int v2 = integer.charAt(i);

            if (v1 == v2) {
                gray = gray + "0";
            } else {
                gray = gray + "1";
            }
        }

        return Integer.parseInt(gray,2);

    }

    public static int fromGray (int g) {
        String gray = Integer.toBinaryString(g);
        String integer = "";
        integer = integer + gray.charAt(0);

        for (int i = 1; i < gray.length(); i++) {
            int v1 = gray.charAt(i);
            int v2 = integer.charAt(i - 1);

            if (v1 == v2) {
                integer = integer + "0";
            } else {
                integer = integer + "1";
            }
        }

        return Integer.parseInt(integer,2);
    }

}