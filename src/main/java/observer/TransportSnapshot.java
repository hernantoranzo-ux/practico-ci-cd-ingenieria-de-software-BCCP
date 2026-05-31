package observer;

public class TransportSnapshot {
    private final String name;
    private final double cost;
    private final double distance;
    private final int eta;

    public TransportSnapshot(String name, double cost, double distance, int eta) {
        this.name = name;
        this.cost = cost;
        this.distance = distance;
        this.eta = eta;
    }

    public String getName() { return name; }
    public double getCost() { return cost; }
    public double getDistance() { return distance; }
    public int getEta() { return eta; }
}
