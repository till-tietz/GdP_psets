public class FlugSimulator {

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        int[] n_passengers = new int[n];
        int n_overbooked = 0;

        for(int i = 0; i < n; i++){

            int n_passengers_i = 0;

            for(int j = 0; j < 78; j++){
                double r_arrival = Math.random();
                double p_arrival = 0.92;
                if(r_arrival <= p_arrival){
                    n_passengers_i += 1;
                }
            }  

            n_passengers[i] = n_passengers_i;

            if(n_passengers_i > 75){
                n_overbooked += 1;
            }
        }
        
        double overbooked_percent = 100 * ((float) n_overbooked / n);
        
        int sum_passengers = 0;
        for(int i = 0; i < n; i++){
            sum_passengers += n_passengers[i];
        }
        double mean_passengers = (float) sum_passengers / n;

        System.out.println(
            "Überbuchungen: " + n_overbooked + " (" + overbooked_percent + "%)" + "\n" +
            "\n" + 
            "Mittelwert: " + mean_passengers
        );

    }

}