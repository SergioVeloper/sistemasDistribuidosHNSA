import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Empleado> empleados;

    public Empresa() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public double calcularSalarioTotal() {
        double salarioTotal = 0;
        for (Empleado empleado : empleados) {
            salarioTotal += empleado.calcularSalario();
        }
        return salarioTotal;
    }

    public void mostrarEmpleados() {
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }
}
