package strategy;

import java.util.Random;

public class BicicletaStrategy implements TransportStrategy {
    private Random random = new Random();
    private int distancia;
    public BicicletaStrategy(int distancia) {
        this.distancia = distancia;
    }
    
    public String getName() { return "Bicicleta"; }

    public double getCost() { return 0.0; }

    public double getDistance() { return distancia; } // Atajos posibles

    public int getETA() { return distancia * random.nextInt(5) + 10; }
}