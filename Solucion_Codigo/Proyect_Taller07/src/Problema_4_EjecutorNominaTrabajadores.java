/**
   Problema04: Sistema de nómina para trabajadores
   Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
   Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. 
   Además, existen diferentes tipos de trabajadores:

   Fijos Mensuales: que cobran una cantidad fija al mes.
   Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
   Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes. 
   El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
   Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene obligatoriamente un jefe 
   (exceptuando los jefes que no tienen ninguno). El programa debe permitir dar de alta a trabajadores, 
   así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.
   
   * Nota:
   Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor forma el escenario planteado.
   Para probar el diseño de clases, instancia en el clase de prueba Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
   En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores
 
 * @author Erick Caraguay
 * @version 1.0
 */

class Trabajador {
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private Trabajador jefe;

    public Trabajador(String nombre, String apellido,String direccion, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
    }

    public double calcularNomina() {
        return 0;
    }
    public void setJefe(Trabajador jefe) {
        this.jefe = jefe;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellido; }
    public String getDni() { return dni; }

    @Override
    public String toString() {
        return "Trabajador{" + "nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", dni=" + dni + ", jefe=" + jefe + '}';
    }
}


class TrabajadorFijoMensual extends Trabajador {
    private double sueldoFijo;

    public TrabajadorFijoMensual(String nombre, String apellidos, String direccion, String dni, double sueldoFijo) {
        super(nombre, apellidos, direccion, dni);
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public double calcularNomina() {
        return sueldoFijo;
    }

    @Override
    public String toString() {
        return "TrabajadorFijoMensual{" + "sueldoFijo=" + sueldoFijo + '}' + super.toString();
    }
}


class Comisionista extends Trabajador {
    private double ventas;
    private double porcComision;

    public Comisionista(String nombre, String apellidos, String direccion, String dni, double porcComision) {
        super(nombre, apellidos, direccion, dni);
        this.porcComision = porcComision;
        this.ventas = 0.0;
    }

    // Setter para fijar ventas del mes
    public void setVentas(double ventas) {
        this.ventas = ventas;
    }
    @Override
    public double calcularNomina() {
        return ventas * (porcComision / 100);
    }

    @Override
    public String toString() {
        return "Comisionista{" + "ventas=" + ventas + ", porcComision=" + porcComision + '}' + super.toString();
    }
}


class TrabajadorPorHoras extends Trabajador {
    private double horas;
    private double precioHoras;
    private double precioHorasExtra;

    public TrabajadorPorHoras(String nombre, String apellidos,String direccion, String dni, double precioHoras, double precioHorasExtra) {
        super(nombre, apellidos, direccion, dni);
        this.precioHoras = precioHoras;
        this.precioHorasExtra = precioHorasExtra;
        this.horas = 0.0;
    }
    // Setter para fijar horas del mes
    public void setHoras(double horas) {
        this.horas = horas;
    }

    @Override
    public double calcularNomina() {
        if (horas <= 40) {
            return horas * precioHoras;
        } else {
            return (40 * precioHoras) + ((horas - 40) * precioHorasExtra);
        }
    }

    @Override
    public String toString() {
        return "TrabajadorPorHoras{" + "horas=" + horas + ", precioHoras=" + precioHoras + ", precioHorasExtra=" + precioHorasExtra + '}' + super.toString();
    }
}


class Jefe extends Trabajador {
    private double sueldoFijo;

    public Jefe(String nombre, String apellidos, String direccion, String dni, double sueldoFijo) {
        super(nombre, apellidos, direccion, dni);
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public double calcularNomina() {
        return sueldoFijo;
    }

    @Override
    public String toString() {
        return "Jefe{" + "sueldoFijo=" + sueldoFijo + '}' + super.toString();
    }
}

public class Problema_4_EjecutorNominaTrabajadores {
    public static void main(String[] args) {
        // Crear jefes
        Jefe jefe1 = new Jefe("Carlos", "Cubarsi","Av. Principal 123", "1712345678",3500.00);
        Jefe jefe2 = new Jefe("Aitana", "Lopez","Calle Secundaria 2", "1798765432",4200.00);

        // Crear trabajador fijo mensual
        TrabajadorFijoMensual fijo1 = new TrabajadorFijoMensual("Federico", "Valverde","Bolivar", "1723456789",1200.00);
        fijo1.setJefe(jefe1);

        // Crear comisionista
        Comisionista comis1 = new Comisionista("Luis", "Diaz","Calle Loja", "1934567890",15.0);
        comis1.setVentas(8500.00);
        comis1.setJefe(jefe1);

        // Crear trabajador por horas
        TrabajadorPorHoras porHoras1 = new TrabajadorPorHoras("Pedro", "Vite","Av. Ecuador", "5145678901",5.00, 8.50);
        porHoras1.setHoras(50.0); // 40 normales + 10 extras
        porHoras1.setJefe(jefe2);

        System.out.println("============================");
        System.out.println("    SISTEMA DE NOMINA       ");
        System.out.println("============================");
        System.out.println(jefe1.toString());
        System.out.println();
        System.out.println(jefe2.toString());
        System.out.println();
        System.out.println(fijo1.toString());
        System.out.println();
        System.out.println(comis1.toString());
        System.out.println();
        System.out.println(porHoras1.toString());
        
        System.out.println("\n============================");
        System.out.println("    RESUMEN NOMINA TOTAL    ");
        System.out.println("============================");

        Trabajador[] trabajadores = {jefe1, jefe2, fijo1, comis1, porHoras1};

        double totalNomina = 0;
        for (Trabajador t : trabajadores) {
            totalNomina += t.calcularNomina();
            System.out.println(t.getNombre() + " " + t.getApellidos()+ " : $" + String.format("%.2f", t.calcularNomina()));
        }

        System.out.println("============================");
        System.out.println("Total a pagar : $" + String.format("%.2f", totalNomina));
    }
}


/**
   run:
============================
    SISTEMA DE NOMINA       
============================
Jefe{sueldoFijo=3500.0}Trabajador{nombre=Carlos, apellido=Cubarsi, direccion=Av. Principal 123, dni=1712345678, jefe=null}

Jefe{sueldoFijo=4200.0}Trabajador{nombre=Aitana, apellido=Lopez, direccion=Calle Secundaria 2, dni=1798765432, jefe=null}

TrabajadorFijoMensual{sueldoFijo=1200.0}Trabajador{nombre=Federico, apellido=Valverde, direccion=Bolivar, dni=1723456789, jefe=Jefe{sueldoFijo=3500.0}Trabajador{nombre=Carlos, apellido=Cubarsi, direccion=Av. Principal 123, dni=1712345678, jefe=null}}

Comisionista{ventas=8500.0, porcComision=15.0}Trabajador{nombre=Luis, apellido=Diaz, direccion=Calle Loja, dni=1934567890, jefe=Jefe{sueldoFijo=3500.0}Trabajador{nombre=Carlos, apellido=Cubarsi, direccion=Av. Principal 123, dni=1712345678, jefe=null}}

TrabajadorPorHoras{horas=50.0, precioHoras=5.0, precioHorasExtra=8.5}Trabajador{nombre=Pedro, apellido=Vite, direccion=Av. Ecuador, dni=5145678901, jefe=Jefe{sueldoFijo=4200.0}Trabajador{nombre=Aitana, apellido=Lopez, direccion=Calle Secundaria 2, dni=1798765432, jefe=null}}

============================
    RESUMEN NOMINA TOTAL    
============================
Carlos Cubarsi : $3500.00
Aitana Lopez : $4200.00
Federico Valverde : $1200.00
Luis Diaz : $1275.00
Pedro Vite : $285.00
============================
Total a pagar : $10460.00
BUILD SUCCESSFUL (total time: 0 seconds)

 */