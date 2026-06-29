import java.util.ArrayList;
/**
 * Problema01 - Jerarquía de clases para el capítulo de libro
Dibuje un diagrama de clases que muestre la estructura de un capítulo de libro; 
un capítulo está compuesto por varias secciones, cada una de las cuales comprende 
varios párrafos y figuras. Un párrafo incluye varias sentencias, cada una de las cuales contiene varias palabras.

Suponga que en un futuro se prevé que el sistema gestione además de párrafos y figuras otros componentes,
como tablas, listas, viñetas, etc.Suponga además que una palabra puede aparecer en varias sentencias.

* @author Erick Caraguay
 * @version 1.0
 */
class ComponenteSeccion {
    @Override
    public String toString() {
        return "ComponenteSeccion{" + '}';
    }
}

class Parrafo extends ComponenteSeccion {
    private String texto;
    private ArrayList<Sentencia> sentencias;

    public Parrafo(String texto) {
        this.texto      = texto;
        this.sentencias = new ArrayList<>();
    }

    public void agregarSentencia(Sentencia s) {
        sentencias.add(s);
    }

    @Override
    public String toString() {
        return "Parrafo{" + "texto=" + texto + ", sentencias=" + sentencias + super.toString() + '}';
    }
}

class Figura extends ComponenteSeccion {
    @Override
    public String toString() {
        return "Figura{" + super.toString() +'}';
    }
}

class Tabla extends ComponenteSeccion {
    private String datos;

    public Tabla(String datos) {
        this.datos = datos;
    }
    @Override
    public String toString() {
        return "Tabla{" + "datos=" + datos + super.toString() +'}';
    }
}

class Palabra {
    private String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }
    @Override
    public String toString() {
        return "Palabra{" + "texto=" + texto + '}';
    }
}

class Sentencia {
    private ArrayList<Palabra> palabras;

    public Sentencia() {
        this.palabras = new ArrayList<>();
    }
    public void agregarPalabra(Palabra p) {
        palabras.add(p);
    }
    @Override
    public String toString() {
        return "Sentencia{" + "palabras=" + palabras + '}';
    }
}

class Seccion {
    private String titulo;
    private int numero;
    private ArrayList<ComponenteSeccion> componentes;

    public Seccion(String titulo, int numero) {
        this.titulo      = titulo;
        this.numero      = numero;
        this.componentes = new ArrayList<>();
    }
    public void agregarComponente(ComponenteSeccion c) {
        componentes.add(c);
    }
    @Override
    public String toString() {
        return "Seccion{" + "titulo=" + titulo + ", numero=" + numero + ", componentes=" + componentes + '}';
    }
}

class Capitulo {
    private String titulo;
    private int numero;
    private ArrayList<Seccion> secciones;

    public Capitulo(String titulo, int numero) {
        this.titulo = titulo;
        this.numero = numero;
        this.secciones = new ArrayList<>();
    }
    public void agregarSeccion(Seccion s) {
        secciones.add(s);
    }
    @Override
    public String toString() {
        return "Capitulo{" + "titulo=" + titulo + ", numero=" + numero + ", secciones=" + secciones + '}';
    } 
}

public class Problema_1_EjecutorCapituloLibro {
    public static void main(String[] args) {
        Palabra p1 = new Palabra("La");
        Palabra p2 = new Palabra("programacion");
        Palabra p3 = new Palabra("es");
        Palabra p4 = new Palabra("fundamental");
        
        Sentencia s1 = new Sentencia();
        s1.agregarPalabra(p1);
        s1.agregarPalabra(p2);
        s1.agregarPalabra(p3);
        s1.agregarPalabra(p4);

        Sentencia s2 = new Sentencia();
        s2.agregarPalabra(p3);  
        s2.agregarPalabra(p4);  

        Parrafo par1 = new Parrafo("Introduccion a POO");
        par1.agregarSentencia(s1);
        par1.agregarSentencia(s2);

        Figura fig1 = new Figura();
        Tabla tab1 = new Tabla("Comparativa de lenguajes de programacion");

        Seccion sec1 = new Seccion("Fundamentos", 1);
        sec1.agregarComponente(par1);
        sec1.agregarComponente(fig1);
        sec1.agregarComponente(tab1);

        Capitulo cap = new Capitulo("Programacion Orientada a Objetos", 1);
        cap.agregarSeccion(sec1);

        System.out.println(cap.toString());
    }
}


/**
 *run:
Capitulo{titulo=Programacion Orientada a Objetos, numero=1, 
* secciones=[Seccion{titulo=Fundamentos, numero=1, 
* componentes=[Parrafo{texto=Introduccion a POO, 
* sentencias=[Sentencia{
* palabras=[Palabra{texto=La}, Palabra{texto=programacion}, Palabra{texto=es}, Palabra{texto=fundamental}]}, 
* Sentencia{palabras=[Palabra{texto=es}, Palabra{texto=fundamental}]}]
* ComponenteSeccion{}}, 
* Figura{ComponenteSeccion{}}, 
* Tabla{datos=Comparativa de lenguajes de programacionComponenteSeccion{}}]}]}
BUILD SUCCESSFUL (total time: 0 seconds)
 */