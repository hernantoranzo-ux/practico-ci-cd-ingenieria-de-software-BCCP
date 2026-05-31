import java.util.Scanner;
import logger.Logger;
import strategy.*;
import observer.*;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.logInfo("Iniciando la aplicación de transporte...");

        TravelPlanner planner = new TravelPlanner();

        TransportMonitor monitor = new TransportMonitor(logger, planner);

        // Configurar los observers
        ConsolePrinter printer = new ConsolePrinter(logger);
        AlertService thresholdService = new ThresholdAlertService(30, 650.0);
        AlertObserver alerter = new AlertObserver(thresholdService, logger);

        monitor.suscribe(printer);
        monitor.suscribe(alerter);

        Scanner scanner = new Scanner(System.in);
        int opcion;
        int distancia;
        opcion = -123; // Valor inicial para entrar al bucle, no es una opción válida
        do {
            if (opcion == -123) {
                System.out.println("\n¿Cómo quieres viajar hoy?");
                System.out.println("1. Taxi");
                System.out.println("2. Colectivo");
                System.out.println("3. Bicicleta");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                
                if (opcion != 0) {
                    System.out.print("indique distancia: ");
                    distancia = scanner.nextInt();
                } else {
                    distancia = 0;
                }
                switch (opcion) {
                    case 1:
                        planner.setTransport(new TaxiStrategy(distancia)); // crea instansia de taxi strategy y se la
                                                                           // asigna al planner
                        break;
                    case 2:
                        planner.setTransport(new ColectivoStrategy(distancia)); // crea instansia de colectivo strategy
                                                                                // y se la asigna al planner
                        break;
                    case 3:
                        planner.setTransport(new BicicletaStrategy(distancia)); // crea instansia de bicicleta strategy
                                                                                // y se la asigna al planner
                        break;
                    case 0:
                        logger.logInfo("Saliendo de la aplicación. ¡Buen viaje!"); // loggea que se esta saliendo de la
                                                                                   // aplicacion
                        break;
                    default:
                        logger.logError("Opción no válida."); // loggea que se ingreso una opcion no valida
                        break;
                }
                if (opcion > 0 && opcion <= 3) {
                    monitor.setRunning(true); // Inicia el monitor
                    monitor.start(5000); // Inicia el monitor para actualizar cada 5 segundos
                }
            } else {
                System.out.println("\n¿Quieres cambiar tu medio de transporte?");
                System.out.println("1. Si");
                System.out.println("0. Salir de la aplicación");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        opcion = -123; // Reinicia la opción para mostrar el menú de transporte
                        monitor.setRunning(false); // Detiene el monitor
                        break;
                    case 0:
                        logger.logInfo("Saliendo de la aplicación. ¡Buen viaje!"); // loggea que se esta saliendo de la
                                                                                   // aplicacion
                        monitor.setRunning(false); // Detiene el monitor
                        break;
                    default:
                        logger.logError("Opción no válida."); // loggea que se ingreso una opcion no valida
                        break;
                }
            }
        } while (opcion != 0);

        scanner.close();
    }
}