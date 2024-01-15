public class Riddle {
    private static int[] field;
    private static int N;
    private static int solutionCount;

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);
        field = new int[2 * N];
        solutionCount = 0;

        findSolutions(1);
        solutionCount = solutionCount / 2;
        String solutions;

        if (solutionCount == 0) {
            solutions = "keine Loesung";
        } else if (solutionCount == 1) {
            solutions = "eine Loesung";
        } else {
            solutions = solutionCount + " Loesungen";
        }

        System.out.println(solutions);
    }

    private static void findSolutions(int n) {
        if (n > N) {
            if (N < 10 && field[0] < field[2 * N - 1]) {
                print(field);
            } 
            solutionCount++;
            return;
        }

        for (int i = 0; i < 2 * N - n - 1; i++) {
            int j = i + n + 1;
            if (field[i] == 0 && field[j] == 0) {
                field[i] = field[j] = n;

                findSolutions(n + 1);

                field[i] = field[j] = 0;
            }
        }
    }


    private static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}