import java.lang.Math;

public class Wuerfel {
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]); // Number of sides on the dice
        int K = Integer.parseInt(args[1]); // Number of consecutive rolls of the highest number (N)
        int S = Integer.parseInt(args[2]); // Number of times the dice is thrown

        if (N <= 1 || K <= 0 || S <= 0) {
            System.out.println("Invalid input. N, K, and S must be positive integers.");
            return;
        }

        int throwCount = 0;

        for (int i = 0; i < S; i++) {
            int nHighestRolls = 0;
            int throwsNeeded = 0;

            while (nHighestRolls < K) {
                int currentRoll = rollDice(N);
                throwsNeeded++;

                if (currentRoll == N) {
                    nHighestRolls++;
                } else {
                    nHighestRolls = 0;
                }
            }

            throwCount += throwsNeeded;
        }

        double averageThrows = (double) throwCount / S;
        System.out.println(averageThrows);
    }

    private static int rollDice(int sides) {
        return (int) (Math.random() * sides) + 1;
    }
}
