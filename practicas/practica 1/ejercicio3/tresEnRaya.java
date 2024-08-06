import java.util.Scanner;

public class tresEnRaya {
    private char[][] tablero;
    private char jugadorActual;

    // Constructor para inicializar el tablero y el primer jugador
    public tresEnRaya() {
        tablero = new char[3][3];
        jugadorActual = 'X';
        inicializarTablero();
    }

    // Inicializar el tablero con espacios en blanco
    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    // Imprimir el tablero en consola
    public void mostrarTablero() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Cambiar el turno del jugador
    public void cambiarJugador() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    // Colocar una marca en el tablero
    public boolean marcarPosicion(int fila, int columna) {
        if (tablero[fila][columna] == ' ') {
            tablero[fila][columna] = jugadorActual;
            return true;
        }
        return false;
    }

    // Verificar si hay un ganador
    public boolean hayGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) ||
                (tablero[0][i] == jugadorActual && tablero[1][i] == jugadorActual && tablero[2][i] == jugadorActual)) {
                return true;
            }
        }
        // Verificar diagonales
        if ((tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) ||
            (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual)) {
            return true;
        }
        return false;
    }

    // Verificar si hay un empate
    public boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Iniciar el juego
    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            mostrarTablero();
            System.out.println("Turno del jugador " + jugadorActual + ". Ingrese fila (0-2) y columna (0-2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3) {
                if (marcarPosicion(fila, columna)) {
                    if (hayGanador()) {
                        mostrarTablero();
                        System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                        juegoTerminado = true;
                    } else if (esEmpate()) {
                        mostrarTablero();
                        System.out.println("¡Es un empate!");
                        juegoTerminado = true;
                    } else {
                        cambiarJugador();
                    }
                } else {
                    System.out.println("Esa posición ya está ocupada. Intente de nuevo.");
                }
            } else {
                System.out.println("Posición inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        tresEnRaya juego = new tresEnRaya();
        juego.jugar();
    }
}
