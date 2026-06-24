
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

    // Constructor solo con número - nombre es opcional
    public Movil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
        this.nombre      = "Sin nombre";
    }

    // Constructor con número y nombre
    public Movil(String numeroMovil, String nombre) {
        this.numeroMovil = numeroMovil;
        this.nombre      = nombre;
    }

    // Getters necesarios para que Mensaje acceda a los datos
    public String getNumeroMovil() { return numeroMovil; }
    public String getNombre()      { return nombre; }

    @Override
    public String toString() {
        return nombre + " (" + numeroMovil + ")";
    }
}

abstract class Mensaje {
    private Movil remitente;
    private Movil destinatario;

    // Constructor parametrizado
    public Mensaje(Movil remitente,Movil destinatario) {
        this.remitente    = remitente;
        this.destinatario = destinatario;
    }

    // Getters necesarios para que SMS y MMS accedan
    public Movil getRemitente()    { return remitente; }
    public Movil getDestinatario() { return destinatario; }

    // Métodos abstractos que cada subclase implementa
    public abstract void enviarMensaje();
    public abstract void visualizarMensaje();

    @Override
    public String toString() {
        return  "  De   : " + remitente.toString()
            + "\n  Para  : " + destinatario.toString();
    }
}

class Sms extends Mensaje{
    private String texto;

    // Constructor
    public Sms(Movil remitente, Movil destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("[SMS enviado]");
        System.out.println(this.toString());
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
    private String nombreFichero;

    // Constructor
    public Mms(Movil remitente, Movil destinatario, String nombreFichero) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("[MMS enviado]");
        System.out.println(this.toString());
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
            + "\n  Imagen : " + nombreFichero
            + "\n===========";
    }
}

public class Problema_3_EjecutorEnvioMensajes {
    public static void main(String[] args) {
        // Crear móviles - constructor solo con número
        Movil movil1 = new Movil("0991234567");
        Movil movil2 = new Movil("0987654321");

        // Crear móviles - constructor con nombre
        Movil movil3 = new Movil("0912345678", "Ana Torres");
        Movil movil4 = new Movil("0923456789", "Luis Mora");

        // SMS entre móviles sin nombre
        Sms sms1 = new Sms(movil1, movil2,"Hola, ¿cómo estás?");

        // SMS entre móviles con nombre
        Sms sms2 = new Sms(movil3, movil4, "Nos vemos a las 5pm");

        // MMS entre móviles con nombre
        Mms mms1 = new Mms(movil3, movil4, "foto_vacaciones.jpg");

        // MMS entre móviles sin nombre
        Mms mms2 = new Mms(movil1, movil2, "documento_importante.png");

        // Enviar y visualizar mensajes
        System.out.println("Sistema de Mensajeria Movil");
        System.out.println("===========================");

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
 * run: 
 * 
 */