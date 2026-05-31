package observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ThresholdAlertServiceTest {

    @Test
    public void testShouldAlertCost_belowThreshold_returnsFalse() {
        ThresholdAlertService service = new ThresholdAlertService(10, 100.0);
        assertTrue(service.shouldAlertCost(50.0)); // TEST ROTO A PROPOSITO
    }

    @Test
    public void testShouldAlertCost_atThreshold_returnsFalse() {
        // En la implementación actual, cost > costLimit retorna false para un costo igual al límite.
        ThresholdAlertService service = new ThresholdAlertService(10, 100.0);
        assertFalse(service.shouldAlertCost(100.0));
    }

    @Test
    public void testShouldAlertCost_aboveThreshold_returnsTrue() {
        ThresholdAlertService service = new ThresholdAlertService(10, 100.0);
        assertTrue(service.shouldAlertCost(150.0));
    }

    @Test
    public void testShouldAlertETA_belowThreshold_returnsFalse() {
        ThresholdAlertService service = new ThresholdAlertService(10, 100.0);
        assertFalse(service.shouldAlertETA(5));
    }

    @Test
    public void testShouldAlertETA_aboveThreshold_returnsTrue() {
        ThresholdAlertService service = new ThresholdAlertService(10, 100.0);
        assertTrue(service.shouldAlertETA(15));
    }
}
