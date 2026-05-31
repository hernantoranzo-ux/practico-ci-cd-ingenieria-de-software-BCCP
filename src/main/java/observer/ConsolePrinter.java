package observer;

import logger.Logger;

public class ConsolePrinter implements TransportObserver {
    private final Logger logger;

    public ConsolePrinter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onUpdate(TransportSnapshot snapshot) {
        logger.logInfo("Transporte: " + snapshot.getName());
        logger.logDebug("Distancia: " + snapshot.getDistance() + " | Costo: $" + snapshot.getCost());
    }
}

