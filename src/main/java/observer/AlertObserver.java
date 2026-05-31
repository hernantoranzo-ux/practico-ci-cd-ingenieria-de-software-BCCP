package observer;

import logger.Logger;

public class AlertObserver implements TransportObserver {
    private final Logger logger;
    private final AlertService alertService;

    public AlertObserver(AlertService alertService, Logger logger) {
        this.alertService = alertService;
        this.logger = logger;
    }

    @Override
    public void onUpdate(TransportSnapshot snapshot) {
        if (alertService.shouldAlertETA(snapshot.getEta())) {
            logger.logError("Tiempo estimado de llegada excede el límite: " + snapshot.getEta());
        }
        if (alertService.shouldAlertCost(snapshot.getCost())) {
            logger.logWarning("Costo estimado excede el límite: " + snapshot.getCost());
        }
    }
}
