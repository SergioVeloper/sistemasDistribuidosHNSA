import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaDeTareas listaDeTareas = new ListaDeTareas();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Gestión de Tareas:");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Marcar Tarea como Completada");
            System.out.println("3. Mostrar Tareas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarTarea(listaDeTareas, scanner);
                    break;
                case 2:
                    marcarTareaComoCompletada(listaDeTareas, scanner);
                    break;
                case 3:
                    listaDeTareas.mostrarTareas();
                    break;
                case 4:
                    System.out.println("Saliendo del gestor de tareas...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 4);

        scanner.close();
    }

    private static void agregarTarea(ListaDeTareas listaDeTareas, Scanner scanner) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Ingrese la fecha límite (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        try {
            LocalDate fechaLimite = LocalDate.parse(fecha);
            Tarea tarea = new Tarea(descripcion, fechaLimite);
            listaDeTareas.agregarTarea(tarea);
            System.out.println("Tarea agregada exitosamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido.");
        }
    }

    private static void marcarTareaComoCompletada(ListaDeTareas listaDeTareas, Scanner scanner) {
        System.out.print("Ingrese el número de la tarea a marcar como completada: ");
        int indice = scanner.nextInt() - 1;
        listaDeTareas.marcarTareaComoCompletada(indice);
    }
}
