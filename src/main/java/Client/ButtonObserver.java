package Client;

/**
 * Zainteresowany kliknieciem przycisku zakończenia tury.
 */
public interface ButtonObserver {

    /**
     * Informuje o tym, że przycisk został kliknięty.
     */
    void buttonClicked();
}
