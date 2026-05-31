package observer;

import logger.Logger;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AlertObserverMockTest {

    @Test
    public void testAlertObserver_whenCostAlertIsTrue_callsLogWarning() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(true);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(false);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);
        TransportSnapshot snapshot = new TransportSnapshot("Test", 500.0, 10.0, 30);

        // Act
        observer.onUpdate(snapshot);

        // Assert
        verify(mockLogger, times(1)).logWarning(anyString());
        verify(mockLogger, never()).logError(anyString());
    }

    @Test
    public void testAlertObserver_whenETAAlertIsTrue_callsLogError() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(false);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(true);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);
        TransportSnapshot snapshot = new TransportSnapshot("Test", 500.0, 10.0, 30);

        // Act
        observer.onUpdate(snapshot);

        // Assert
        verify(mockLogger, times(1)).logError(anyString());
        verify(mockLogger, never()).logWarning(anyString());
    }

    @Test
    public void testAlertObserver_whenBothAreFalse_callsNoLogs() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(false);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(false);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);
        TransportSnapshot snapshot = new TransportSnapshot("Test", 500.0, 10.0, 30);

        // Act
        observer.onUpdate(snapshot);

        // Assert
        verify(mockLogger, never()).logError(anyString());
        verify(mockLogger, never()).logWarning(anyString());
    }

    @Test
    public void testAlertObserver_whenBothAreTrue_callsErrorAndWarning() {
        // Arrange
        AlertService mockService = mock(AlertService.class);
        Logger mockLogger = mock(Logger.class);

        when(mockService.shouldAlertCost(anyDouble())).thenReturn(true);
        when(mockService.shouldAlertETA(anyInt())).thenReturn(true);

        AlertObserver observer = new AlertObserver(mockService, mockLogger);
        TransportSnapshot snapshot = new TransportSnapshot("Test", 500.0, 10.0, 30);

        // Act
        observer.onUpdate(snapshot);

        // Assert
        verify(mockLogger, times(1)).logError(anyString());
        verify(mockLogger, times(1)).logWarning(anyString());
    }
}
