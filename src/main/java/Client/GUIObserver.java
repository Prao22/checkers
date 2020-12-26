package Client;

public interface GUIObserver extends BoardObserver, ButtonObserver {
    void close();
}
