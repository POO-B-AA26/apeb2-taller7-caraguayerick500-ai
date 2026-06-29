/**
 * Problema06: Sistema Un Banco
El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada 
una de las cuentas consisten en el número de cuenta, el nombre del cliente y el balance actual. 
Escriba una clase para implementar dicha cuenta bancaria. El método constructor debe aceptar como 
parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para depositar o retirar una 
cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. 
Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero una cuenta de ahorros no. 
Al final de cada mes, se calcula el interés sobre la cantidad que tenga la cuenta de ahorros. 
Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, 
haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método 
que sea invocado para calcular el interés. Además, el banco está pensando en implementar una cuenta 
PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el 
interés del 10%, sin cargos ni castigos por sobregiro.

 * @author Erick Caraguay
 * @version 1.0
 */

class CuentaBancaria {
    private String numeroCuenta;
    private String nombreCliente;
    private double balance;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta  = numeroCuenta;
        this.nombreCliente = nombreCliente;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            balance += monto;
        }
    }

    public void retirar(double monto) {}

    public double getBalance(){ return balance; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getNombreCliente() { return nombreCliente; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balance=" + balance + '}';
    }
}

class CCheques extends CuentaBancaria {
    public CCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    // Permite sobregiro - balance puede quedar negativo
    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            setBalance(getBalance() - monto);
        }
    }

    @Override
    public String toString() {
        return "CCheques{" + super.toString() +'}';
    }
}

class CAhorros extends CuentaBancaria {
    private double interes;

    public CAhorros(String numeroCuenta, String nombreCliente, double interes) {
        super(numeroCuenta, nombreCliente);
        this.interes = interes;
    }
    // No permite sobregiro
    @Override
    public void retirar(double monto) {
        if (monto > 0 && getBalance() >= monto) {
            setBalance(getBalance() - monto);
        } else {
            System.out.println("Saldo insuficiente. No se permite sobregiro en cuenta de ahorros.");
        }
    }
    
    public void calcularInteres() {
        double interesGanado = getBalance() * (interes / 100);
        setBalance(getBalance() + interesGanado);
    }

    @Override
    public String toString() {
        return "CAhorros{" + "interes=" + interes + super.toString() + '}';
    }
}

class CPlatino extends CuentaBancaria {
    private double interes;

    public CPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.interes = 10.0;
    }
    // Permite sobregiro sin cargos
    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            setBalance(getBalance() - monto);
        }
    }

    // interés del 10%
    public void calcularInteres() {
        double interesGanado = getBalance() * (interes / 100);
        setBalance(getBalance() + interesGanado);
    }

    @Override
    public String toString() {
        return "CPlatino{" + "interes=" + interes + super.toString() + '}';
    }
}

public class Problema_6_EjecutorSistemaBanco {
    public static void main(String[] args) {
        // Crear cuentas
        CCheques  c1 = new CCheques("001CH", "Ferran Torres");
        CAhorros  c2 = new CAhorros("002AH", "Gilberto Mora",   5.0);
        CAhorros  c3 = new CAhorros("003AH", "Luis Suarez", 3.5);
        CPlatino  c4 = new CPlatino("004PL", "Pedro Porro");
        CCheques  c5 = new CCheques("005CH", "Raul Jimenez");

        // cuenta cheques c1
        c1.depositar(1000.00);
        c1.retirar(1500.00);   // sobregiro permitido
        // cuenta ahorros c2
        c2.depositar(2000.00);
        c2.retirar(500.00);
        c2.calcularInteres();
        //cuenta ahorros c3
        c3.depositar(800.00);
        c3.retirar(1000.00);   // NO sobregiro
        c3.calcularInteres();
        // cuenta platino c4
        c4.depositar(5000.00);
        c4.retirar(6000.00);   // sobregiro sin castigo
        c4.calcularInteres();  // interés 10%
        // cuenta cheques c5
        c5.depositar(3000.00);
        c5.retirar(1200.00);

        System.out.println("    SISTEMA BANCO   ");
        System.out.println("====================\n");

        CuentaBancaria[] cuentas = {c1, c2, c3, c4, c5};
        for (CuentaBancaria c : cuentas) {
            System.out.println(c.toString());
            System.out.println();
        }

        System.out.println("    RESUMEN     ");
        System.out.println("================");
        double totalBalance = 0;
        for (CuentaBancaria c : cuentas) {
            totalBalance += c.getBalance();
            System.out.println("  " + c.getNombreCliente() + " : $" + String.format("%.2f", c.getBalance()));
        }

        System.out.println("================\n");
        System.out.println("Total en el banco : $" + String.format("%.2f", totalBalance));
    }
}

/**
 *run:
Saldo insuficiente. No se permite sobregiro en cuenta de ahorros.
    SISTEMA BANCO   
====================

CCheques{CuentaBancaria{numeroCuenta=001CH, nombreCliente=Ferran Torres, balance=-500.0}}

CAhorros{interes=5.0CuentaBancaria{numeroCuenta=002AH, nombreCliente=Gilberto Mora, balance=1575.0}}

CAhorros{interes=3.5CuentaBancaria{numeroCuenta=003AH, nombreCliente=Luis Suarez, balance=828.0}}

CPlatino{interes=10.0CuentaBancaria{numeroCuenta=004PL, nombreCliente=Pedro Porro, balance=-1100.0}}

CCheques{CuentaBancaria{numeroCuenta=005CH, nombreCliente=Raul Jimenez, balance=1800.0}}

    RESUMEN     
================
  Ferran Torres : $-500.00
  Gilberto Mora : $1575.00
  Luis Suarez : $828.00
  Pedro Porro : $-1100.00
  Raul Jimenez : $1800.00
================

Total en el banco : $2603.00
 */