package laboratorio1.ejercicio2;
public class EmpleadoFijo extends Empleado {
    public EmpleadoFijo(String nombre, double salario, String departamento) {
        super(nombre, salario, departamento);
    }

    @Override
    public double calcularSalario() {
        // Supongamos que el salario de un empleado fijo es un salario base
        return getSalario();
    }
}