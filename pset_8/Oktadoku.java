import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Oktadoku {
    private int[][] board;
    private Variante type;

    public enum Variante {
        normal, mitDiagonalen
    }

    public Oktadoku(Variante type) {
        board = new int[8][8];
        this.type = type;
    }

    public void read(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 8; i++) {
                String line = br.readLine();
                for (int j = 0; j < 8; j++) {
                    if (line.charAt(j) == '.') {
                        board[i][j] = 0;
                    } else {
                        board[i][j] = line.charAt(j) - '0';
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean check() {
        for (int i = 0; i < 8; i++) {
            boolean[] numbers = new boolean[9];
            for (int j = 0; j < 8; j++) {
                int num = board[i][j];
                if (num != 0 && numbers[num]) {
                    return false;
                }
                numbers[num] = true;
            }

            numbers = new boolean[9];
            for (int j = 0; j < 8; j++) {
                int num = board[j][i];
                if (num != 0 && numbers[num]) {
                    return false;
                }
                numbers[num] = true;
            }

            if (type == Variante.mitDiagonalen) {
                numbers = new boolean[9];
                for (int j = 0; j < 8; j++) {
                    int num = board[j][j];
                    if (num != 0 && numbers[num]) {
                        return false;
                    }
                    numbers[num] = true;
                }

                numbers = new boolean[9];
                for (int j = 0; j < 8; j++) {
                    int num = board[j][7 - j];
                    if (num != 0 && numbers[num]) {
                        return false;
                    }
                    numbers[num] = true;
                }
            }
        }

        for (int i = 0; i < 8; i += 4) {
            for (int j = 0; j < 8; j += 2) {
                boolean[] numbers = new boolean[9];
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 2; l++) {
                        int num = board[i + k][j + l];
                        if (num != 0 && numbers[num]) {
                            return false;
                        }
                        numbers[num] = true;
                    }
                }
            }
        }

        return true;
    }

    public boolean solve() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0) {
                    for (int k = 1; k <= 8; k++) {
                        board[i][j] = k;
                        if (check() && solve()) {
                            return true;
                        }
                        board[i][j] = 0;
                    }
                    System.out.println("nicht loesbar :-(");
                    return false;
                }
            }
        }
        return true;
    }

    public void write() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
