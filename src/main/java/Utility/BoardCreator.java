package Utility;


import Game.CounterColor;

public class BoardCreator {
    public static boolean[][] createBoard(int size) {
        int rows = howManyRows(size);
        int cols = howManyCols(size);

        boolean[][] board = new boolean[rows][cols];

        for (int row = 0; row < size; row++) {
            fillPeak(row, cols, row, board);
        }

        for (int row = size, k = 0; row < 2 * size; row++, k++) {
            fillBody(row, cols, k, size, board);
        }

        fillMiddle(size, cols, board);

        for (int row = 2 * size + 1, k = size - 1; row < 3 * size + 1; row++, k--) {
            fillBody(row, cols, k, size, board);
        }

        for (int row = 3 * size + 1, k = size - 1; row < rows; row++, k--) {
            fillPeak(row, cols, k, board);
        }

        return board;
    }

    public static int howManyRows(int size) {
        return 4 * size + 1;
    }

    public static int howManyCols(int size) {
        return 3 * size + 1;
    }

    public static int maxCounters(int size) {
        return ((size + 1) * size) / 2;
    }

    public static int[][] cornerA(boolean[][] board, int size, int howManyCounters) {
        int[][] corner = new int[howManyCounters][2];
        int counters = 0;


        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j < 2 * size + 1; j++) {

                if (board[i][j]) {
                    corner[counters][0] = i;
                    corner[counters][1] = j;
                    counters++;
                }

                if (counters == howManyCounters) {
                    return corner;
                }
            }
        }

        return corner;
    }

    public static int[][] cornerB(boolean[][] board, int size, int howManyCounters) {
        int[][] corner = new int[howManyCounters][2];
        int counters = 0;
        int counter;

        for (int i = size; i < 2 * size; i++) {
            counter = size - i % size;
            for (int j = 0; j < size && counter != 0; j++) {
                if (board[i][j]) {
                    corner[counters][0] = i;
                    corner[counters][1] = j;
                    counters++;
                    counter--;
                }

                if (counters == howManyCounters) {
                    return corner;
                }
            }
        }

        return corner;
    }

    public static int[][] cornerC(boolean[][] board, int size, int howManyCounters) {
        int[][] corner = new int[howManyCounters][2];
        int counters = 0;

        int counter;
        int set = 0;

        for (int i = 2 * size + 1; i < 3 * size + 1; i++) {
            set++;
            counter = set;

            for (int j = 0; j < size && counter != 0; j++) {
                if (board[i][j]) {
                    corner[counters][0] = i;
                    corner[counters][1] = j;
                    counters++;
                    counter--;
                }

                if (counters == howManyCounters) {
                    return corner;
                }
            }
        }

        return corner;
    }

    public static int[][] cornerD(boolean[][] board, int size, int howManyCounters) {
        int[][] corner = new int[howManyCounters][2];
        int counters = 0;


        for (int i = 3 * size + 1; i < 4 * size + 1; i++) {
            for (int j = size; j < 2 * size + 1; j++) {

                if (board[i][j]) {
                    corner[counters][0] = i;
                    corner[counters][1] = j;
                    counters++;
                }

                if (counters == howManyCounters) {
                    return corner;
                }
            }
        }

        return corner;
    }

    public static int[][] cornerE(boolean[][] board, int size, int howManyCounters) {
        int[][] corner = new int[howManyCounters][2];
        int counters = 0;

        int counter;
        int set = 0;

        for (int i = 2 * size + 1; i < 3 * size + 1; i++) {
            set++;
            counter = set;
            for (int j = 3 * size; j > 2 * size && counter != 0; j--) {
                if (board[i][j]) {
                    corner[counters][0] = i;
                    corner[counters][1] = j;
                    counters++;
                    counter--;
                }

                if (counters == howManyCounters) {
                    return corner;
                }
            }
        }

        return corner;
    }

    public static int[][] cornerF(boolean[][] board, int size, int howManyCounters) {
        int[][] corner = new int[howManyCounters][2];
        int counters = 0;
        int counter;

        for (int i = size; i < 2 * size; i++) {
            counter = size - i % size;
            for (int j = 3 * size; j > 2 * size && counter != 0; j--) {
                if (board[i][j]) {
                    corner[counters][0] = i;
                    corner[counters][1] = j;
                    counters++;
                    counter--;
                }

                if (counters == howManyCounters) {
                    return corner;
                }
            }
        }

        return corner;
    }

    private static void fillPeak(int row, int cols, int k, boolean[][] board) {

        int howManyFalse = cols / 2 - (k + 1) / 2;
        int col = 0;

        for (; col < howManyFalse; col++) {
            board[row][col] = false;
        }

        for (int i = 0; i < k + 1; i++, col++) {
            board[row][col] = true;
        }

        for (; col < cols; col++) {
            board[row][col] = false;
        }
    }

    private static void fillBody(int row, int cols, int k, int size, boolean[][] board) {

        int howManyFalse = (k + size % 2) / 2;
        int col = 0;

        for (; col < howManyFalse; col++) {
            board[row][col] = false;
        }

        for (int i = 0; i < cols - k; i++, col++) {
            board[row][col] = true;
        }

        for (; col < cols; col++) {
            board[row][col] = false;
        }
    }

    private static void fillMiddle(int size, int cols, boolean[][] board) {
        int row = 2 * size;
        int col = 0;

        for (; col < (size + 1) / 2; col++) {
            board[row][col] = false;
        }

        for (int i = 0; i < 2 * size + 1; col++, i++) {
            board[row][col] = true;
        }

        for (; col < cols; col++) {
            board[row][col] = false;
        }
    }
}
