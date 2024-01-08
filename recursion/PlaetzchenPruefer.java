public class PlaetzchenPruefer
{

    public static void main (String[] args)
    {
        int anzahlPlaetzchen = Integer.parseInt(args[0]);
        System.out.println(pruefePlaetzchen(anzahlPlaetzchen));
    }

    public static int pruefePlaetzchen(int anzahlPlaetzchen)
    {
        if (anzahlPlaetzchen == 0) {
            return 0;
        }
        
        if ((anzahlPlaetzchen % 2) == 0) {
            return pruefePlaetzchen((anzahlPlaetzchen - 2) / 2) + 2;
        } else {
            return pruefePlaetzchen((anzahlPlaetzchen - 1)) + 1;
        }
    }
}