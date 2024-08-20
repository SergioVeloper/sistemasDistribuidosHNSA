package laboratorio1.ejercicio2;
public abstract class Empleado {
    private String nombre;
    private double salario;
    private String departamento;

    public Empleado(String nombre, double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public abstract double calcularSalario(); // Método abstracto para calcular el salario
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Salario: " + salario + ", Departamento: " + departamento;
    }
}


