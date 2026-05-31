package observer;

import logger.Logger;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AlertObserverFakeTest {

    // Fake AlertService que siempre retorna true
    static class AlwaysAlertService implements AlertService {
        @Override
        public boolean shouldAlertCost(double cost) {
            return true;
        }

        @Override
        public boolean shouldAlertETA(int eta) {
            return true;
        }
    }

    // Fake AlertService que siempre retorna false
    static class NeverAlertService implements AlertService {
        @Override
        public boolean shouldAlertCost(double cost) {
            return false;
        }

        @Override
        public boolean shouldAlertETA(int eta) {
            return false;
        }
    }

    @Test
    public void testAlertObserver_withAlwaysAlertService_logsBoth() {
        Logger mockLogger = mock(Logger.class);
        AlertObserver observer = new AlertObserver(new AlwaysAlertService(), mockLogger);

        TransportSnapshot snapshot = new TransportSnapshot("TestTransport", 500.0, 10.0, 30);
        observer.onUpdate(snapshot);

        verify(mockLogger, times(1)).logError(anyString());
        verify(mockLogger, times(1)).logWarning(anyString());
    }

    @Test
    public void testAlertObserver_withNeverAlertService_logsNothing() {
        Logger mockLogger = mock(Logger.class);
        AlertObserver observer = new AlertObserver(new NeverAlertService(), mockLogger);

        TransportSnapshot snapshot = new TransportSnapshot("TestTransport", 500.0, 10.0, 30);
        observer.onUpdate(snapshot);

        verify(mockLogger, never()).logError(anyString());
        verify(mockLogger, never()).logWarning(anyString());
    }
}
