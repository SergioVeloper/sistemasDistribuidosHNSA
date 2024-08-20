package ahorcado;

import java.util.HashSet;
import java.util.Set;

public class JuegoAhorcado {
    private String palabra;
    private Set<Character> letrasAdivinadas;
    private int errores;

    public JuegoAhorcado(String palabra) {
        this.palabra = palabra.toLowerCase();
        this.letrasAdivinadas = new HashSet<>();
        this.errores = 0;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Set<Character> getLetrasAdivinadas() {
        return letrasAdivinadas;
    }

    public void setLetrasAdivinadas(Set<Character> letrasAdivinadas) {
        this.letrasAdivinadas = letrasAdivinadas;
    }

    public boolean adivinarLetra(char letra) {
        letra = Character.toLowerCase(letra);
        if (palabra.indexOf(letra) >= 0) {
            letrasAdivinadas.add(letra);
            return true;
        } else {
            errores++;
            return false;
        }
    }

    public String obtenerPalabraOculta() {
        StringBuilder resultado = new StringBuilder();
        for (char letra : palabra.toCharArray()) {
            if (letrasAdivinadas.contains(letra)) {
                resultado.append(letra);
            } else {
                resultado.append('_');
            }
        }
        return resultado.toString();
    }

    public boolean haGanado() {
        for (char letra : palabra.toCharArray()) {
            if (!letrasAdivinadas.contains(letra)) {
                return false;
            }
        }
        return true;
    }

    public boolean haPerdido() {
        return errores >= 7;
    }

    public int getErrores() {
        return errores;
    }

    public String getPalabraCompleta() {
        return palabra;
    }
}
