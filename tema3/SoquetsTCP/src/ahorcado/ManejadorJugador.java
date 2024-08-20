package ahorcado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ManejadorJugador implements Runnable {
    private Socket clientSocket;
    private JuegoAhorcado juego;

    public ManejadorJugador(Socket socket, String palabra) {
        this.clientSocket = socket;
        this.juego = new JuegoAhorcado(palabra);
    }

    @Override
    public void run() {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream toClient = new PrintStream(clientSocket.getOutputStream());

            toClient.println("Bienvenido al juego del Ahorcado!");
            while (!juego.haGanado() && !juego.haPerdido()) {
                toClient.println("Palabra: " + juego.obtenerPalabraOculta());
                toClient.println("Intentos restantes: " + (7 - juego.getErrores()));
                toClient.println("Adivina una letra: ");
                char letra = fromClient.readLine().charAt(0);

                if (juego.adivinarLetra(letra)) {
                    toClient.println("¡Correcto!");
                } else {
                    toClient.println("Incorrecto.");
                }
            }

            if (juego.haGanado()) {
                toClient.println("¡Felicidades! Has adivinado la palabra: " + juego.obtenerPalabraOculta());
            } else {
                toClient.println("Has perdido. La palabra era: " + juego.getPalabraCompleta());
            }

            clientSocket.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
