package Server.Database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import Game.Move;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "moves")
@IdClass(MoveDB.PK.class)
public class MoveDB {

    @Id
    private int gameId;

    @Id
    private int numberOfMove;

    @Column
    private int fromX;

    @Column
    private int fromY;

    @Column
    private int toX;

    @Column
    private int toY;

    public MoveDB() {

    }

    public MoveDB(int gameId, Move move) {
        this.gameId = gameId;
        fromX = move.getFrom()[0];
        fromY = move.getFrom()[1];
        toX = move.getTo()[0];
        toY = move.getTo()[1];
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    public Move getMove() {
        return new Move(new int[]{getFromX(), getFromY()}, new int[]{getToX(), getToY()});
    }


    public static class PK implements Serializable {
        @Column(name="game_id", nullable = false)
        private int gameId;

        @Column(name="move_nr", nullable = false)
        private int numberOfMove;

        public PK() {}

        public PK(int gameId, int numberOfMove) {
            this.gameId = gameId;
            this.numberOfMove = numberOfMove;
        }

        public int getGameId() {
            return gameId;
        }

        public int getNumberOfMove() {
            return numberOfMove;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public void setNumberOfMove(int numberOfMove) {
            this.numberOfMove = numberOfMove;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return gameId == pk.gameId && numberOfMove == pk.numberOfMove;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gameId, numberOfMove);
        }
    }
}
