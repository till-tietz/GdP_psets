
public class Banner {

    // Definition des Zeichensatzes für 0123456789-
    static String[][] fontChars = {{
            "  ###   ",
            " #   #  ",
            "#     # ",
            "#     # ",
            "#     # ",
            " #   #  ",
            "  ###   "}, {
            "   #    ",
            "  ##    ",
            " # #    ",
            "   #    ",
            "   #    ",
            "   #    ",
            " #####  "}, {
            " #####  ",
            "#     # ",
            "      # ",
            " #####  ",
            "#       ",
            "#       ",
            "####### "}, {
            " #####  ",
            "#     # ",
            "      # ",
            " #####  ",
            "      # ",
            "#     # ",
            " #####  "}, {
            "#       ",
            "#    #  ",
            "#    #  ",
            "#    #  ",
            "####### ",
            "     #  ",
            "     #  "}, {
            "####### ",
            "#       ",
            "#       ",
            "######  ",
            "      # ",
            "#     # ",
            " #####  "}, {
            " #####  ",
            "#     # ",
            "#       ",
            "######  ",
            "#     # ",
            "#     # ",
            " #####  "}, {
            "####### ",
            "#    #  ",
            "    #   ",
            "   #    ",
            "  #     ",
            "  #     ",
            "  #     "}, {
            " #####  ",
            "#     # ",
            "#     # ",
            " #####  ",
            "#     # ",
            "#     # ",
            " #####  "}, {
            " #####  ",
            "#     # ",
            "#     # ",
            " ###### ",
            "      # ",
            "#     # ",
            " #####  "}, {
            "        ",
            "        ",
            "        ",
            " #####  ",
            "        ",
            "        ",
            "        "}};

    public static void main(String[] args) 
    {
        // turn string into array of fontChars indices:
        // turn each char into corresponding int (and turn - into 10)
        String s = args[0];
        int[] idx = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                idx[i] = 10;
            } else {
                idx[i] = c - '0';
            }
        }

        // we have to print line by line >> each symbol in fontChars is 7 lines high
        for (int i = 0; i < 7; i++) {
            String line = "";
            for (int j = 0; j < idx.length; j++) {
                line += fontChars[idx[j]][i];
            }
            System.out.println(line);
        }
    }

}
