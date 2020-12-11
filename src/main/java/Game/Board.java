package Game;

import Utility.BoardCreator;

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
        createRelationBetweenFields();
    }

    public static void main(String[] args) {
        Board board = new Board(2);
        board.print();

    }

    private void fillBoard() {

        boolean[][] b_board = BoardCreator.createBoard(size);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = b_board[row][col] ? new Field() : null;
            }
        }

    }

    private void createRelationBetweenFields() {

    }



    public void print() {

        for(int i = 0; i < rows; i++) {
//            if(i % 2 == 1) {
//                System.out.print(" ");
//            }
            for(int j = 0; j < cols; j++) {
                System.out.print(board[i][j] == null ? "*" : "-");
            }
            System.out.println("");
        }

    }
}
