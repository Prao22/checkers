package Utility;
@SuppressWarnings("DuplicatedCode")

public class BoardCreator {

    public enum Corners {
        A, B, C, D, E, F
    }

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

    public static int[][] getCorner(Corners corner, boolean[][] board, int size) {
        switch (corner) {
            default:
            case A:
                return getCornerA(board, size);
            case B:
                return getCornerB(board, size);
            case C:
                return getCornerC(board, size);
            case D:
                return getCornerD(board, size);
            case E:
                return getCornerE(board, size);
            case F:
                return getCornerF(board, size);
        }
    }

    private static int[][] getCornerA(boolean[][] board, int size) {
        int[][] A = new int[size * (size + 1) / 2][2];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = size; j <= 2 * size; j++) {
                if (board[i][j]) {
                    A[index][0] = i;
                    A[index][1] = j;
                    index++;
                }

            }
        }
        return A;
    }

    private static int[][] getCornerB(boolean[][] board, int size) {
        int[][] B = new int[size * (size + 1) / 2][2];
        int index = 0;
        for (int i = 0; i < size; i++) {
            int k, j;
            k = i;
            j = size;
            if (index < size * (size + 1) / 2 && size % 2 == 0) {
                B[index][0] = j;
                B[index][1] = k;
                k--;
                j++;
                index++;
            }
            while (k >= 0 && board[j][k] && index < size * (size + 1) / 2) {
                B[index][0] = j;
                B[index][1] = k;
                index++;
                if (board[j + 1][k]) {
                    B[index][0] = j + 1;
                    B[index][1] = k;
                    index++;
                }
                k--;
                j = j + 2;
            }
        }
        return B;
    }

    private static int[][] getCornerC(boolean[][] board, int size) {
        int[][] C = new int[size * (size + 1) / 2][2];
        int index = 0;
        for (int i = 0; i < size; i++) {
            int k, j;
            j = 3 * size;
            k = i;
            if (index < size * (size + 1) / 2 && size % 2 == 0) {
                C[index][0] = j;
                C[index][1] = k;
                k--;
                j--;
                index++;
            }
            while (k >= 0 && board[j][k] && index < size * (size + 1) / 2) {
                C[index][0] = j;
                C[index][1] = k;
                index++;
                if (board[j - 1][k]) {
                    C[index][0] = j - 1;
                    C[index][1] = k;
                    index++;
                }
                k--;
                j = j - 2;
            }
        }

        return C;
    }

    private static int[][] getCornerD(boolean[][] board, int size) {
        int[][] D = new int[size * (size + 1) / 2][2];
        int index = 0;
        for (int i = 4 * size; i > 3 * size; i--) {
            for (int j = 2 * size; j >= size - 2; j--) {
                if (j >= 0 && board[i][j]) {
                    D[index][0] = i;
                    D[index][1] = j;
                    index++;
                }
            }
        }
        return D;
    }

    private static int[][] getCornerE(boolean[][] board, int size) {
        int[][] E = new int[size * (size + 1) / 2][2];
        int index = 0;
        for (int i = 3 * size; i > 2 * size; i--) {
            int k, j;
            k = i;
            j = 3 * size;
            if (size % 2 == 1) {
                E[index][0] = j;
                E[index][1] = k;
                k++;
                j--;
                index++;
            }
            while (k <= 3 * size && board[j][k] && index < size * (size + 1) / 2) {
                E[index][0] = j;
                E[index][1] = k;
                index++;
                if (board[j - 1][k]) {
                    E[index][0] = j - 1;
                    E[index][1] = k;
                    index++;
                }
                k++;
                j = j - 2;
            }
        }

        return E;
    }

    private static int[][] getCornerF(boolean[][] board, int size) {
        int[][] F = new int[size * (size + 1) / 2][2];
        int index = 0;
        for (int i = 3 * size; i > 2 * size; i--) {
            int k, j;
            k = i;
            j = size;
            if (size % 2 == 1) {
                F[index][0] = j;
                F[index][1] = k;
                k++;
                j++;
                index++;
            }
            while (k <= 3 * size && board[j][k] && index < size * (size + 1) / 2) {
                F[index][0] = j;
                F[index][1] = k;
                index++;
                if (board[j + 1][k]) {
                    F[index][0] = j + 1;
                    F[index][1] = k;
                    index++;
                }
                k++;
                j = j + 2;
            }
        }

        return F;
    }


//    public static int[][] cornerA(boolean[][] board, int size, int howManyCounters) {
//        int[][] corner = new int[howManyCounters][2];
//        int counters = 0;
//
//
//        for (int i = 0; i < size; i++) {
//            for (int j = size - 1; j < 2 * size + 1; j++) {
//
//                if (board[i][j]) {
//                    corner[counters][0] = i;
//                    corner[counters][1] = j;
//                    counters++;
//                }
//
//                if (counters == howManyCounters) {
//                    return corner;
//                }
//            }
//        }
//
//        return corner;
//    }
//
//    public static int[][] cornerB(boolean[][] board, int size, int howManyCounters) {
//        int[][] corner = new int[howManyCounters][2];
//        int counters = 0;
//        int counter;
//
//        for (int i = size; i < 2 * size; i++) {
//            counter = size - i % size;
//            for (int j = 0; j < size && counter != 0; j++) {
//                if (board[i][j]) {
//                    corner[counters][0] = i;
//                    corner[counters][1] = j;
//                    counters++;
//                    counter--;
//                }
//
//                if (counters == howManyCounters) {
//                    return corner;
//                }
//            }
//        }
//
//        return corner;
//    }
//
//    public static int[][] cornerC(boolean[][] board, int size, int howManyCounters) {
//        int[][] corner = new int[howManyCounters][2];
//        int counters = 0;
//
//        int counter;
//        int set = 0;
//
//        for (int i = 2 * size + 1; i < 3 * size + 1; i++) {
//            set++;
//            counter = set;
//
//            for (int j = 0; j < size && counter != 0; j++) {
//                if (board[i][j]) {
//                    corner[counters][0] = i;
//                    corner[counters][1] = j;
//                    counters++;
//                    counter--;
//                }
//
//                if (counters == howManyCounters) {
//                    return corner;
//                }
//            }
//        }
//
//        return corner;
//    }
//
//    public static int[][] cornerD(boolean[][] board, int size, int howManyCounters) {
//        int[][] corner = new int[howManyCounters][2];
//        int counters = 0;
//
//
//        for (int i = 3 * size + 1; i < 4 * size + 1; i++) {
//            for (int j = size; j < 2 * size + 1; j++) {
//
//                if (board[i][j]) {
//                    corner[counters][0] = i;
//                    corner[counters][1] = j;
//                    counters++;
//                }
//
//                if (counters == howManyCounters) {
//                    return corner;
//                }
//            }
//        }
//
//        return corner;
//    }
//
//    public static int[][] cornerE(boolean[][] board, int size, int howManyCounters) {
//        int[][] corner = new int[howManyCounters][2];
//        int counters = 0;
//
//        int counter;
//        int set = 0;
//
//        for (int i = 2 * size + 1; i < 3 * size + 1; i++) {
//            set++;
//            counter = set;
//            for (int j = 3 * size; j > 2 * size && counter != 0; j--) {
//                if (board[i][j]) {
//                    corner[counters][0] = i;
//                    corner[counters][1] = j;
//                    counters++;
//                    counter--;
//                }
//
//                if (counters == howManyCounters) {
//                    return corner;
//                }
//            }
//        }
//
//        return corner;
//    }
//
//    public static int[][] cornerF(boolean[][] board, int size, int howManyCounters) {
//        int[][] corner = new int[howManyCounters][2];
//        int counters = 0;
//        int counter;
//
//        for (int i = size; i < 2 * size; i++) {
//            counter = size - i % size;
//            for (int j = 3 * size; j > 2 * size && counter != 0; j--) {
//                if (board[i][j]) {
//                    corner[counters][0] = i;
//                    corner[counters][1] = j;
//                    counters++;
//                    counter--;
//                }
//
//                if (counters == howManyCounters) {
//                    return corner;
//                }
//            }
//        }
//
//        return corner;
//    }

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
