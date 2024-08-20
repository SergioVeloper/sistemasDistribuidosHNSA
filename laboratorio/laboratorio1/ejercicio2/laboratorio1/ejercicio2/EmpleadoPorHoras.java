package laboratorio1.ejercicio2;
public class EmpleadoPorHoras extends Empleado {
    private int horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHoras(String nombre, String departamento, int horasTrabajadas, double tarifaPorHora) {
        super(nombre, horasTrabajadas * tarifaPorHora, departamento);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSalario() {
        // El salario de un empleado por horas es el número de horas trabajadas multiplicado por la tarifa por hora
        return horasTrabajadas * tarifaPorHora;
    }
}