import java.util.ArrayList;
import java.util.Scanner;
/**
  Problema05: Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:
    Se desea gestionar la venta de entradas para un espectáculo en un teatro. 
    El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre. 
    Los datos de las zonas son los mostrados en la siguiente tabla:

    NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
    Principal	200	25$	17.5$
    PalcoB	40	70$	40$
    Central	400	20$	14$
    Lateral	100	15.5$	10$
    
    Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor 
    el documento que justifique que tiene algún tipo de descuento (estudiante, abonado o pensionista). 
    El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador (un número entero) que permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

    Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.

    Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:

    Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
    Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
    Entradas abonado: su precio es el precio para abonados de la zona elegida.
    La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.
 * @author Erick Caraguay
 * @version 2.0
 */

class Zona {
    private String nombreZona;
    private int numLocalidades;
    private double precioNormal;
    private double precioAbonado;
    private int localidadesVend;

    public Zona(String nombreZona, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombreZona = nombreZona;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.localidadesVend = 0;
    }

    public boolean estaCompleta() {
        return localidadesVend >= numLocalidades;
    }
    public void registrarVenta() {
        localidadesVend++;
    }

    public String getNombreZona() { return nombreZona; }
    public double getPrecioNormal() { return precioNormal; }
    public double getPrecioAbonado() { return precioAbonado; }

    @Override
    public String toString() {
        return "Zona{" + "nombreZona=" + nombreZona + ", numLocalidades=" + numLocalidades + ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + ", localidadesVend=" + localidadesVend + '}';
    }
}

class Entrada {
    private int id;
    private Zona zona;
    private String nombreComprador;

    public Entrada(int id, Zona zona, String nombreComprador) {
        this.id = id;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public double calcularPrecio() {
        return 0;
    }

    public int getId() { return id; }
    public Zona getZona() { return zona; }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", zona=" + zona + ", nombreComprador=" + nombreComprador + '}';
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return getZona().getPrecioNormal();
    }

    @Override
    public String toString() {
        return "EntradaNormal{" + super.toString() +'}';
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return getZona().getPrecioNormal() * 0.85;
    }

    @Override
    public String toString() {
        return "EntradaReducida{" + super.toString() +'}';
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return getZona().getPrecioAbonado();
    }

    @Override
    public String toString() {
        return "EntradaAbonado{" + super.toString() + '}';
    }
}

class Teatro {
    private ArrayList<Zona> zonas;
    private ArrayList<Entrada> entradas;
    private int contId;

    public Teatro() {
        this.entradas = new ArrayList<>();
        this.contId = 1;
        this.zonas = new ArrayList<>();

        //Zonas tabla
        zonas.add(new Zona("Principal", 200, 25.0,  17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0,  40.0));
        zonas.add(new Zona("Central", 400, 20.0,  14.0));
        zonas.add(new Zona("Lateral", 100, 15.5,  10.0));
    }

    public Zona buscarZona(String nombreZona) {
        for (Zona z : zonas) {
            if (z.getNombreZona().equalsIgnoreCase(nombreZona)) {
                return z;
            }
        }
        return null;
    }

    public String venderEntrada(String nombreZona, String nombreComp, String tipo) {
        Zona zona = buscarZona(nombreZona);

        if (zona == null) {
            return "No existe ninguna zona con el nombre: " + nombreZona;
        }
        if (zona.estaCompleta()) {
            return "La zona " + nombreZona + " esta completa. No hay localidades disponibles.";
        }

        Entrada entrada;
        switch (tipo.toLowerCase()) {
            case "normal":
                entrada = new EntradaNormal(contId, zona, nombreComp);
                break;
            case "reducida":
                entrada = new EntradaReducida(contId, zona, nombreComp);
                break;
            case "abonado":
                entrada = new EntradaAbonado(contId, zona, nombreComp);
                break;
            default:
                return "Tipo de entrada no valido. Use: normal, reducida o abonado.";
        }

        entradas.add(entrada);
        zona.registrarVenta();
        contId++;

        return "Entrada generada correctamente:\n" + entrada.toString() + "\n1";
    }

    public String consultarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.getId() == id) {
                return e.toString();
            }
        }
        return "No existe ninguna entrada con el identificador: " + id;
    }

    @Override
    public String toString() {
        return "Teatro{" + "zonas=" + zonas + ", entradas=" + entradas + ", contId=" + contId + '}';
    }
}

public class Problema_5_EjecutorTeatro_v2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Teatro teatro = new Teatro();
        int opcion;
        do {
            System.out.println("    SISTEMA DE VENTA TEATRO     ");
            System.out.println("================================");
            System.out.println("1. Vender entrada");
            System.out.println("2. Consultar entrada");
            System.out.println("3. Ver estado del teatro");
            System.out.println("0. Salir");
            System.out.println("================================");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nZonas disponibles: Principal, PalcoB, Central, Lateral");
                    System.out.print("Zona deseada: ");
                    String zona = sc.nextLine();

                    System.out.print("Nombre del comprador: ");
                    String nombre = sc.nextLine();

                    System.out.println("Tipo de entrada: normal / reducida / abonado");
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();

                    System.out.println("\n" + teatro.venderEntrada(zona, nombre, tipo));
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la entrada: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("\n" + teatro.consultarEntrada(id));
                    break;

                case 3:
                    System.out.println("\n" + teatro.toString());
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no vAlida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}

/**
  run:
    SISTEMA DE VENTA TEATRO     
================================
1. Vender entrada
2. Consultar entrada
3. Ver estado del teatro
0. Salir
================================
Seleccione una opcion: 1

Zonas disponibles: Principal, PalcoB, Central, Lateral
Zona deseada: principal
Nombre del comprador: yo
Tipo de entrada: normal / reducida / abonado
Tipo: normal

Entrada generada correctamente:
EntradaNormal{Entrada{id=1, zona=Zona{nombreZona=Principal, numLocalidades=200, precioNormal=25.0, precioAbonado=17.5, localidadesVend=1}, nombreComprador=yo}}
1
    SISTEMA DE VENTA TEATRO     
================================
1. Vender entrada
2. Consultar entrada
3. Ver estado del teatro
0. Salir
================================
Seleccione una opcion: 2
Ingrese el ID de la entrada: 1

EntradaNormal{Entrada{id=1, zona=Zona{nombreZona=Principal, numLocalidades=200, precioNormal=25.0, precioAbonado=17.5, localidadesVend=1}, nombreComprador=yo}}
    SISTEMA DE VENTA TEATRO     
================================
1. Vender entrada
2. Consultar entrada
3. Ver estado del teatro
0. Salir
================================
Seleccione una opcion: 3

Teatro{zonas=[Zona{nombreZona=Principal, numLocalidades=200, precioNormal=25.0, precioAbonado=17.5, localidadesVend=1}, Zona{nombreZona=PalcoB, numLocalidades=40, precioNormal=70.0, precioAbonado=40.0, localidadesVend=0}, Zona{nombreZona=Central, numLocalidades=400, precioNormal=20.0, precioAbonado=14.0, localidadesVend=0}, Zona{nombreZona=Lateral, numLocalidades=100, precioNormal=15.5, precioAbonado=10.0, localidadesVend=0}], entradas=[EntradaNormal{Entrada{id=1, zona=Zona{nombreZona=Principal, numLocalidades=200, precioNormal=25.0, precioAbonado=17.5, localidadesVend=1}, nombreComprador=yo}}], contId=2}
    SISTEMA DE VENTA TEATRO     
================================
1. Vender entrada
2. Consultar entrada
3. Ver estado del teatro
0. Salir
================================
Seleccione una opcion: 0
Saliendo del sistema...
BUILD SUCCESSFUL (total time: 41 seconds)
 *
 */