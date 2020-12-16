package Utility;


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
