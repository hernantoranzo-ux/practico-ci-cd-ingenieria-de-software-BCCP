package observer;
import logger.Logger;
import strategy.TravelPlanner;
import java.util.ArrayList;
import java.util.List;

public class TransportMonitor {
    private Logger logger;
    private List<TransportObserver> observers = new ArrayList<>();
    private TravelPlanner planner;
    private volatile boolean running;

    public TransportMonitor(Logger logger, TravelPlanner planner) {
        this.logger = logger;
        this.planner = planner;
        this.running = false;
    }
    public void suscribe(TransportObserver observer) {
        observers.add(observer);
    }
    public void unsuscribe(TransportObserver observer) {
        observers.remove(observer);
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void start(int intervalMS){
        new Thread(() -> {
            while (running) {
                //Crea snapshot
                TransportSnapshot snapshot = new TransportSnapshot(
                    planner.getTransport().getName(),
                    planner.getTransport().getCost(),
                    planner.getTransport().getDistance(),
                    planner.getTransport().getETA()
                );
                // Notificar a los observadores
                for (TransportObserver observer : observers) {
                    observer.onUpdate(snapshot);
                }
                try { // Espera el intervalo antes de crear un nuevo Snapshot
                    Thread.sleep(intervalMS);
                } catch (InterruptedException e) {
                    logger.logError("Error en el monitor de transporte: " + e.getMessage());
                }
            }
        }).start();
    }
}