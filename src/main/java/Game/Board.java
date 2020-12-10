package Game;

import java.util.Arrays;

public class Board {

    protected Field[][] board;
    protected final int size;
    protected final int rows;
    protected final int cols;

    public Board(int size) {
        this.size = size;
        this.rows = 4 * size + 1;
        this.cols = 3 * size + 1;
        board = new Field[rows][cols];
        fillBoard();
    }

    public static void main(String[] args) {
        Board board = new Board(2);
        board.print();

    }

    private void fillBoard() {

        int stage = 0;

        fillStage0();
        fillStage1();
        fillStage2();
        fillStage3();
        fillStage4();

    }

    private void fillStage0() {

        for (int row = 0; row < size; row++) {

            int t = cols / 2 - (row + 1) / 2;
            int col = 0;

            for (; col < t; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }

            for (int i = 0; i < row + 1; i++, col++) {
                board[row][col] = new Field(Field.Type.REAL);
            }

            for (; col < cols; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }
        }

    }

    private void fillStage1() {

        for (int row = size; row < 2 * size; row++) {
            int a = ((row - size) + size % 2) / 2;

            int col = 0;

            for (; col < a; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }

            for (int i = 0; i < cols - row + size; i++, col++) {
                board[row][col] = new Field(Field.Type.REAL);
            }

            for (; col < cols; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }
        }

    }

    private void fillStage2() {
        int row = 2 * size;
        int col = 0;

        for (; col < (size + 1) / 2; col++) {
            board[row][col] = new Field(Field.Type.FAKE);
        }

        for (int i = 0; i < 2 * size + 1; col++, i++) {
            board[row][col] = new Field(Field.Type.REAL);
        }

        for (; col < cols; col++) {
            board[row][col] = new Field(Field.Type.FAKE);
        }
    }

    private void fillStage3() {
        for (int row = 2*size + 1, k = size - 1; row < 3 * size + 1; row++, k--) {

            int a = (k + size % 2) / 2;

            int col = 0;

            for (; col < a; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }

            for (int i = 0; i < cols - k; i++, col++) {
                board[row][col] = new Field(Field.Type.REAL);
            }

            for (; col < cols; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }
        }
    }

    private void fillStage4() {

        for (int row = 3 * size + 1, k = size - 1; row < rows; row++, k--) {

            int t = cols / 2 - (k + 1) / 2;

            int col = 0;

            for (; col < t; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }

            for (int i = 0; i < k + 1; i++, col++) {
                board[row][col] = new Field(Field.Type.REAL);
            }

            for (; col < cols; col++) {
                board[row][col] = new Field(Field.Type.FAKE);
            }
        }
    }

    public void print() {

        for(int i = 0; i < rows; i++) {
//            if(i % 2 == 1) {
//                System.out.print(" ");
//            }
            for(int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }

    }
}
