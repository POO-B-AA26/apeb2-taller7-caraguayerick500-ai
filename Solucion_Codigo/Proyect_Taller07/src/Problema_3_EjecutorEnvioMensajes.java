/**
 * Problema03: Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes 
 * que se pueden enviar entre móviles, mensajes de texto (SMS) y mensajes que contienen imágenes (MMS).
 * Por un lado, los mensajes de texto contienen un mensaje en caracteres que se desea enviar de un móvil
 * a otro. Por otro lado, los mensajes que contienen imágenes almacenan información sobre la imagen a 
 * enviar, la cual se representará por el nombre del fichero que la contiene. Independientemente del 
 * tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. 
 * Ambos estarán definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar 
 * información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.
 * 
 * @author Erick Caraguay
 * @version 1.0
 */

class Movil {
    private String numeroMovil;
    private String nombre;

    // Constructor solo con número
    public Movil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
        this.nombre      = "Sin nombre";
    }
    // Constructor con número y nombre
    public Movil(String numeroMovil, String nombre) {
        this.numeroMovil = numeroMovil;
        this.nombre      = nombre;
    }
    
    public String getNumeroMovil() { return numeroMovil; }
    public String getNombre()      { return nombre; }

    @Override
    public String toString() {
        return nombre + " (" + numeroMovil + ")";
    }
}

class Mensaje {
    private Movil remitente;
    private Movil destinatario;

    public Mensaje(Movil remitente,Movil destinatario) {
        this.remitente    = remitente;
        this.destinatario = destinatario;
    }

    public Movil getRemitente()    { return remitente; }
    public Movil getDestinatario() { return destinatario; }

    // Métodos abstractos que cada subclase implementa
    public void enviarMensaje() { }
    public void visualizarMensaje(){ }

    @Override
    public String toString() {
        return  "  De   : " + remitente.toString()
            + "\n  Para  : " + destinatario.toString();
    }
}

class Sms extends Mensaje{
    private String texto;

    public Sms(Movil remitente, Movil destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("[SMS enviado]");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("[Visualizando SMS]");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return  "=== SMS ==="
            + "\n" + super.toString()
            + "\n  Texto : " + texto
            + "\n===========";
    }
}

class Mms extends Mensaje{
    private String nombreFicha;

    public Mms(Movil remitente, Movil destinatario, String nombreFicha) {
        super(remitente, destinatario);
        this.nombreFicha = nombreFicha;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("[MMS enviado]");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("[Visualizando MMS]");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return  "=== MMS ==="
            + "\n" + super.toString()
            + "\n  Imagen : " + nombreFicha
            + "\n===========";
    }
}

public class Problema_3_EjecutorEnvioMensajes {
    public static void main(String[] args) {
        // Crear móviles con constructor solo con número
        Movil movil1 = new Movil("0991234567");
        Movil movil2 = new Movil("0987654321");
        // Crear móviles con constructor con número ynombre
        Movil movil3 = new Movil("0912345678", "Ferran Torres");
        Movil movil4 = new Movil("0923456789", "Gilberto Mora");

        // SMS sin nombre
        Sms sms1 = new Sms(movil1, movil2,"Hola, que tal?");
        // SMS con nombre
        Sms sms2 = new Sms(movil3, movil4, "Nos vemos a las 5pm");

        // MMS con nombre
        Mms mms1 = new Mms(movil3, movil4, "foto_vacaciones.jpg");
        // MMS sin nombre
        Mms mms2 = new Mms(movil1, movil2, "doc_importante.png");

        System.out.println("Sistema de Mensajeria Movil");
        System.out.println("===========================\n");

        sms1.enviarMensaje();
        System.out.println();
        sms1.visualizarMensaje();
        System.out.println();

        sms2.enviarMensaje();
        System.out.println();
        sms2.visualizarMensaje();
        System.out.println();

        mms1.enviarMensaje();
        System.out.println();
        mms1.visualizarMensaje();
        System.out.println();

        mms2.enviarMensaje();
        System.out.println();
        mms2.visualizarMensaje();
    }
}

/**
 *  run:
    Sistema de Mensajeria Movil
    ===========================

    [SMS enviado]

    [Visualizando SMS]
    === SMS ===
      De   : Sin nombre (0991234567)
      Para  : Sin nombre (0987654321)
      Texto : Hola, que tal?
    ===========

    [SMS enviado]

    [Visualizando SMS]
    === SMS ===
      De   : Ferran Torres (0912345678)
      Para  : Gilberto Mora (0923456789)
      Texto : Nos vemos a las 5pm
    ===========

    [MMS enviado]

    [Visualizando MMS]
    === MMS ===
      De   : Ferran Torres (0912345678)
      Para  : Gilberto Mora (0923456789)
      Imagen : foto_vacaciones.jpg
    ===========

    [MMS enviado]

    [Visualizando MMS]
    === MMS ===
      De   : Sin nombre (0991234567)
      Para  : Sin nombre (0987654321)
      Imagen : doc_importante.png
    ===========
    BUILD SUCCESSFUL (total time: 0 seconds)
 * 
 */