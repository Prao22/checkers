package Client;

/**
 * Zainteresowany kliknieciem przyciskow na footerze.
 */
public interface ButtonObserver {

    /**
     * Informuje o tym, że przycisk został kliknięty.
     */
    void buttonClicked();
}
