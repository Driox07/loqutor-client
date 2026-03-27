package asanchezg.loqutorcli.ttsservice;

/**
 *
 * @author Adrián Sánchez Galera
 */
public interface ProgressBar {
    void updateProgress(int value, String text);
    boolean isCancelled();
    void makeVisible();
    void finishProgress();
}
