package Server.Database;

import javax.persistence.*;
import java.util.List;
import Game.GameParameters;

@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Embedded
    private GameParameters parameters;
    //private List<MoveDB> moves;

    public Game() {

    }

    public Game(GameParameters parameters) {
        this.parameters = parameters;
    }


    public int getId() {
        return id;
    }

    public GameParameters getParameters() {
        return parameters;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setParameters(GameParameters parameters) {
        this.parameters = parameters;
    }

}
