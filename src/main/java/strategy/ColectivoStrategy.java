package strategy;
import java.util.Random;

public class ColectivoStrategy implements TransportStrategy {
    private int distancia;
    public ColectivoStrategy(int distancia) {
        this.distancia = distancia;
    }
    
    private Random random = new Random();

    public String getName() { return "Colectivo"; }

    public double getCost() { return 60.0; } // Precio fijo

    public double getDistance() { return distancia; } // Max recorrido entre inicio y punta de  linea

    public int getETA() { return distancia + random.nextInt(20)*2;}
}