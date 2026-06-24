
import java.util.Arrays;

/**
 * Problema02: Un videoclub dispone de una serie de películas que pueden estar en DVD (con capacidad en Gb.) 
 * o en VHS (una sola cinta por película y con cierto tipo de cinta magnetica). De las películas interesa guardar 
 * el título, el autor, el año de edición y el idioma (o los idiomas, en caso de DVD). El precio de alquiler de las 
 * películas varía en función del tipo de película. Las DVD siempre son 10% mas caras que las de VHS.

 * @author Erick Caraguay
 * @version 1.0
 */
class Pelicula {
    public String titulo, autor;
    public int anio;

    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + '}';
    }
}

class Soporte {
    public Pelicula pelicula;
    public int cantidad;
    public double precio, costoAlquiler;

    public Soporte(Pelicula pelicula, int cantidad, double precio) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public double calcularCostoAlquiler() {
        this.costoAlquiler = this.cantidad * this.precio;
        return this.costoAlquiler;
    }
    
    @Override
    public String toString() {
        return "Soporte{" + "pelicula=" + pelicula + ", cantidad=" + cantidad + ", precio=" + precio + ", costoAlquiler=" + costoAlquiler + '}';
    }
}

class Dvd extends Soporte {
    public double porcentajeRecargo;
    public String idiomas[];

    public Dvd(double porcentajeRecargo, String[] idiomas, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.porcentajeRecargo = porcentajeRecargo;
        this.idiomas = idiomas;
    }
    @Override
    public double calcularCostoAlquiler() {
        this.costoAlquiler = super.calcularCostoAlquiler() + (this.costoAlquiler * (this.porcentajeRecargo / 100));
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Dvd{" + "porcentajeRecargo=" + porcentajeRecargo + ", idiomas=" + Arrays.toString(idiomas) + '}' + super.toString();
    }
}

class Vhs extends Soporte {
    public String idioma;

    public Vhs(String idioma, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Vhs{" + "idioma=" + idioma + '}' + super.toString();
    }
}

public class Problema_2_EjecutorVideoClub {
    public static void main(String[] args) {
        String idiomas[] = {"Espanol", "Eng"};
        Pelicula peli = new Pelicula("El Mundial", "Matt", 2020);
        Dvd dvd1 = new Dvd(10, idiomas, peli, 2, 10);
        dvd1.calcularCostoAlquiler();
        
        
        System.out.println(dvd1);
        
        Vhs vhs1 = new Vhs(idiomas[0], peli, 2, 10);
        vhs1.calcularCostoAlquiler();
        System.out.println(vhs1);
    }
}

/**
 * run:
 * Dvd{porcentajeRecargo=10.0, idiomas=[Espanol, Eng]}Soporte{pelicula=Pelicula{titulo=El Mundial, autor=Matt, anio=2020}, cantidad=2, precio=10.0, costoAlquiler=22.0}
Vhs{idioma=Espanol}Soporte{pelicula=Pelicula{titulo=El Mundial, autor=Matt, anio=2020}, cantidad=2, precio=10.0, costoAlquiler=20.0}
 */