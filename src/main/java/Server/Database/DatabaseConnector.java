package Server.Database;

import Game.GameParameters;
import Game.Move;
import Utility.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnector implements DatabaseService {

    private static DatabaseConnector instance = null;

    private SessionFactory sessionFactory;

    private DatabaseConnector() {
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnector.class) {
                if (instance == null) {
                    instance = new DatabaseConnector();
                }
            }
        }

        return instance;
    }


    @Override
    public int newGame(GameParameters parameters) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int gameId = (Integer) session.save(new Game(parameters));
        session.getTransaction().commit();
        session.close();

        return gameId;
    }

    @Override
    public void saveNextMove(Move move, int gameId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new MoveDB(gameId, move));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public GameParameters getGameParameters(int gameId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Game game = session.get(Game.class, gameId);
        session.getTransaction().commit();
        session.close();

        if (game == null) {
            return null;
        }

        return game.getParameters();
    }

    @Override
    public Move getMove(int gameId, int whichMove) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MoveDB moveDB = session.get(MoveDB.class, new MoveDB.PK(gameId, whichMove));
        session.getTransaction().commit();
        session.close();

        if (moveDB == null) {
            return null;
        }

        return moveDB.getMove();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, GameParameters> listAllGames() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Game> games = (List<Game>) session.createQuery("from Game").list();
        session.getTransaction().commit();
        session.close();

        Map<Integer, GameParameters> gamesMap = new HashMap<>();

        for(Game g : games) {
            gamesMap.put(g.getId(), g.getParameters());
        }

        return gamesMap;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_server.xml");
        DatabaseConnector a = (DatabaseConnector) context.getBean("dbService");
        //Log.log("" + a.newGame(new GameParameters()));
        a.saveNextMove(new Move(new int[]{1, 1}, new int[]{1, 1}), 4);
        a.saveNextMove(new Move(new int[]{1, 1}, new int[]{1, 1}), 4);
        a.saveNextMove(new Move(new int[]{1, 1}, new int[]{1, 1}), 4);
        a.saveNextMove(new Move(new int[]{1, 1}, new int[]{1, 1}), 4);
        a.saveNextMove(new Move(new int[]{1, 1}, new int[]{1, 1}), 4);
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
