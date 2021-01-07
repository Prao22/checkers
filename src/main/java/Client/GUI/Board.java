package Client.GUI;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;

import Client.BoardObserver;
import Game.CounterColor;
import Utility.BoardCreator;

import static Utility.BoardCreator.Corners.*;

/**
 * Klasa planszy do gry, zajmująca się jej tworzeniem
 * i kolorowaniem.
 */
public class Board extends JPanel {

    /**
     * Współrzędne lewego górnego rogu prostokąta
     * w którym znajduje się narysowana plansza.
     */
    private static int MARGINS = 250;

    /**
     * Odległości pól planszy od siebie nawzajem.
     */
    private static int MARGIN_BETWEEN = BoardField.getDiameter() / 8;

    /**
     * Wartość o jaką należy przesunąć co drugą kolumne planszy w prawo.
     */
    private static int OFFSET = BoardField.getDiameter() / 2;

    /**
     * Rozmiar planszy identyfikowany jako
     * ilość pól tworzących bok trójkąta w którym
     * znajdują się pionki gracza.
     */
    private final int size;

    /**
     * Ilość wierszy w których znajdują się pola planszy.
     */
    private final int rows;

    /**
     * Ilość kolumn w których znajdują się pola planszy
     */
    private final int cols;

    /**
     * Ilość graczy.
     */
    private final int numberOfPlayers;

    /**
     * Ilość pionków gracza.
     */
    private final int numberOfCounters;

    /**
     * Obserwator wydarzeń planszy.
     */
    private final BoardObserver observer;

    /**
     * Tablica przetrzymująca współrzędne wszystkich pól planszy.
     */
    private BoardField[][] fields;

    public Board(int size, int numberOfPlayers, int numberOfCounters, BoardObserver observer) {
        this.size = size;
        BoardField.setSizeOfBoard(size);
        OFFSET = BoardField.getDiameter() / 2;
        MARGIN_BETWEEN = BoardField.getDiameter() / 8;
        //magiczne liczby wyprowadzone doświadczalnie (potrzebne aby aplikacja wyglądała jako tako na mniejszych ekranach)
        MARGINS = (int) (15 * Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 384);

        this.rows = BoardCreator.howManyRows(size);
        this.cols = BoardCreator.howManyCols(size);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfCounters = numberOfCounters;
        this.observer = observer;
        observer.attach(this);

        createBoard();
        setPreferredSize(new Dimension(2 * MARGINS + cols * (BoardField.getDiameter() + MARGIN_BETWEEN) + OFFSET - MARGIN_BETWEEN,
                2 * MARGINS + rows * (BoardField.getDiameter() + MARGIN_BETWEEN)));

        addMouseListener(new ClickAction());
    }

    /**
     * Rysuje plansze.
     */
    private void createBoard() {
        fields = new BoardField[rows][cols];
        boolean[][] template = BoardCreator.createBoard(size);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (template[i][j]) {
                    fields[i][j] = new BoardField(i, j, Color.LIGHT_GRAY);

                    if (i % 2 == 0) {
                        fields[i][j].x = MARGINS + j * (BoardField.getDiameter() + MARGIN_BETWEEN);
                    } else {
                        fields[i][j].x = MARGINS + OFFSET + j * (BoardField.getDiameter() + MARGIN_BETWEEN);
                    }

                    fields[i][j].y = MARGINS + (BoardField.getDiameter() + MARGIN_BETWEEN) * i;
                } else {
                    fields[i][j] = null;
                }
            }

        }

        paintCorners(template);
    }

    /**
     * Koloruje plansze.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < 3 * size + 1; i++) {
            for (int j = 0; j < 4 * size + 1; j++) {
                if (fields[j][i] != null) {
                    fields[j][i].draw(g2);
                }
            }
        }
    }

    /**
     * Przesuwa pionki z pola na pola.
     *
     * @param pairA Współrzędne pola z którego pionek ma zostać przesunięty.
     * @param pairB Współrzędne pola na które pionek ma zostać przesunięty.
     */
    public void move(int[] pairA, int[] pairB) {

        //zapisujemy kolor pola A
        Color temporary = fields[pairA[0]][pairA[1]].getColor();

        //zamnieniamy kolor pola A
        fields[pairA[0]][pairA[1]].setColor(fields[pairB[0]][pairB[1]].getColor());
        repaint(fields[pairA[0]][pairA[1]].getBounds());

        //zapisany kolor pola A przypisujemy do pola B
        fields[pairB[0]][pairB[1]].setColor(temporary);
        repaint(fields[pairB[0]][pairB[1]].getBounds());
    }

    /**
     * Podświetla pole na które wskazano
     *
     * @param row Współrzędna x wskazanego pola.
     * @param col Współrzędna y wskazanego pola.
     */
    public void highlightField(int row, int col) {
        fields[row][col].setHighlighted(true);
        repaint(fields[row][col].getBounds());
    }

    /**
     * Znosi efekt podświetlenia pola
     *
     * @param row Współrzędna x podświetlonego pola.
     * @param col Współrzędna y podświetlonego pola.
     */
    public void normalField(int row, int col) {
        fields[row][col].setHighlighted(false);
        repaint(fields[row][col].getBounds());
    }

    /**
     * Zwraca kolor wskazanego pola.
     *
     * @param row Współrzędna x wskazanego pola
     * @param col Współrzędna y wskazanego pola
     * @return kolor wskazanego pola
     */
    public Color getColorOfField(int row, int col) {
        if (fields[row][col] == null) {
            return null;
        }

        return fields[row][col].getColor();
    }

    /**
     * Prywatna klasa obsługująca zdarzenie myszy.
     */
    private class ClickAction extends MouseAdapter {

        /**
         * Obsługuje kliknięcie myszy.
         *
         * @param event Zdarzenie myszy.
         */
        @Override
        public void mousePressed(MouseEvent event) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (fields[i][j] != null && fields[i][j].contains(event.getPoint())) {
                        observer.clickNotify(i, j);
                    }
                }
            }
        }
    }

    /**
     * Koloruje pola graczy.
     *
     * @param board Tablica zawierająca dane o położeniu pól planszy.
     */
    private void paintCorners(boolean[][] board) {
        switch (numberOfPlayers) {
            case 2:
                paintCorner(BoardCreator.getCorner(A, board, size), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.getCorner(D, board, size), CounterColor.getFromNumber(2));
                break;

            case 3:
                paintCorner(BoardCreator.getCorner(A, board, size), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.getCorner(C, board, size), CounterColor.getFromNumber(2));
                paintCorner(BoardCreator.getCorner(E, board, size), CounterColor.getFromNumber(3));
                break;

            case 4:
                paintCorner(BoardCreator.getCorner(B, board, size), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.getCorner(C, board, size), CounterColor.getFromNumber(2));
                paintCorner(BoardCreator.getCorner(E, board, size), CounterColor.getFromNumber(3));
                paintCorner(BoardCreator.getCorner(F, board, size), CounterColor.getFromNumber(4));
                break;

            case 6:
                paintCorner(BoardCreator.getCorner(A, board, size), CounterColor.getFromNumber(1));
                paintCorner(BoardCreator.getCorner(B, board, size), CounterColor.getFromNumber(2));
                paintCorner(BoardCreator.getCorner(C, board, size), CounterColor.getFromNumber(3));
                paintCorner(BoardCreator.getCorner(D, board, size), CounterColor.getFromNumber(4));
                paintCorner(BoardCreator.getCorner(E, board, size), CounterColor.getFromNumber(5));
                paintCorner(BoardCreator.getCorner(F, board, size), CounterColor.getFromNumber(6));

                break;
        }
    }

    /**
     * Maluje pionki gracza w jego strefie.
     *
     * @param corner Strefa gracza.
     * @param color  kolor pionków gracza.
     */
    private void paintCorner(int[][] corner, CounterColor color) {
        for (int i = 0; i < numberOfCounters; i++) {
            fields[corner[i][0]][corner[i][1]].setColor(color.getJavaColor());
        }
    }

}

