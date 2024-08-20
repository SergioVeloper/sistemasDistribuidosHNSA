package socketstcpOperaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado");

                // Crear un nuevo hilo para manejar la conexión del cliente
                new Thread(new ClienteHandler(client)).start();
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

class ClienteHandler implements Runnable {
    private Socket clientSocket;
    private Operaciones operaciones = new Operaciones();

    public ClienteHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream toClient = new PrintStream(clientSocket.getOutputStream());
            
            String opcionRecibida;
            while ((opcionRecibida = fromClient.readLine()) != null) {
                
                if (opcionRecibida.equals("5")) {
                    System.out.println("Cliente ha salido.");
                    clientSocket.close();
                    break;
                }
                
                String recibido = fromClient.readLine();
                
                if (recibido == null) {
                    System.out.println("No se recibió número del cliente.");
                    clientSocket.close();
                    break;
                }
                
                int n = Integer.parseInt(recibido);
                System.out.println("Opción recibida: " + opcionRecibida + ", Número recibido: " + n);
                
                int opcion = Integer.parseInt(opcionRecibida);
                String resultado = "";
                
                if (opcion == 2) {
                    int factorial = operaciones.factorial(n);
                    resultado = "El factorial de " + n + " es: " + factorial;
                } else if (opcion == 3) {
                    int fibonacci = operaciones.fibonacci(n);
                    resultado = "El Fibonacci de " + n + " es: " + fibonacci;
                } else if (opcion == 4) {
                    int sumatoria = operaciones.sumatoria(n);
                    resultado = "La sumatoria de " + n + " es: " + sumatoria;
                } else {
                    resultado = "Opción no válida.";
                }

                toClient.println(resultado);
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
