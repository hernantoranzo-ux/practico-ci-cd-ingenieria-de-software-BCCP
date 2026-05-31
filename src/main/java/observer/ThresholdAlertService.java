package observer;

public class ThresholdAlertService implements AlertService{
    private final int etaLimit;
    private final double costLimit;

    public ThresholdAlertService(int etaLimit, double costLimit) {
        this.etaLimit = etaLimit;
        this.costLimit = costLimit;
    }

    @Override
    public boolean shouldAlertCost(double cost) {
        return cost > costLimit;
    }

    @Override
    public boolean shouldAlertETA(int eta) {
        return eta > etaLimit;
    }
}