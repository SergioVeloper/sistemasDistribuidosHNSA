
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contacto> contactos;

    public Agenda() {
        contactos = new ArrayList<>();
    }

    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public Contacto buscarContactoPorNombre(String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    public boolean eliminarContacto(String nombre) {
        Contacto contacto = buscarContactoPorNombre(nombre);
        if (contacto != null) {
            contactos.remove(contacto);
            return true;
        }
        return false;
    }

    public void mostrarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }
}
