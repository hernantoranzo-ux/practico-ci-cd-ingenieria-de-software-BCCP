package strategy;

import java.util.Random;

public class TaxiStrategy implements TransportStrategy{
    private Random random = new Random();
    private int distancia;
    public TaxiStrategy(int distancia) {
        this.distancia = distancia;
    }

    public String getName() {return "Taxi";}

    public double getCost() {return 500 + (random.nextDouble() * 300); }

    public double getDistance() {return distancia;}

    public int getETA() {return distancia + random.nextInt(15);}
}
