package ahorcado;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Servidor {

    private static List<String> palabras = new ArrayList<>();

    public static void main(String[] args) {
        int port = 5003;

        // Agregar palabras a la lista
        palabras.add("multijugador");
        palabras.add("java");
        palabras.add("servidor");
        palabras.add("concurrente");
        palabras.add("programacion");
        palabras.add("ahorcado");
        palabras.add("redes");
        palabras.add("sistemas");
        palabras.add("distribuidos");
        palabras.add("ciencias");
        palabras.add("computacion");
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor de Ahorcado escuchando en el puerto " + port);

            while (true) {
                Socket client = server.accept();
                System.out.println("Jugador conectado");

                // Seleccionar una palabra al azar de la lista
                String palabraSecreta = seleccionarPalabraAlAzar();

                // Usar la clase ManejadorJugador en su propio archivo
                new Thread(new ManejadorJugador(client, palabraSecreta)).start();
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    private static String seleccionarPalabraAlAzar() {
        Random random = new Random();
        int indice = random.nextInt(palabras.size());
        return palabras.get(indice);
    }
}
