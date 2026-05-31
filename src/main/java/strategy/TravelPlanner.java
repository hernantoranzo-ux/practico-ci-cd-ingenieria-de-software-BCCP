package strategy;

public class TravelPlanner {
    private TransportStrategy strategy;
    public void setTransport(TransportStrategy strategy) {
        this.strategy = strategy;
    }
    public TransportStrategy getTransport() {
        return strategy;
    }
}