
import static java.util.stream.Collectors.toList;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Principal {



  public static void main(String[] args) throws IOException {
    //Extraemos a una List de String las lineas de nuestro documento de texto
    List<String> per = Files.readAllLines(Paths.get("Ejercicio.txt"), Charset.defaultCharset());

    //per.stream().forEach(System.out::println);

    //Creamos una lista de personas que usando stream() y el método static toPersona separara las palabras por ':' las a añade como nombre,poblacion y edad
    //a un objeto persona  y crea una lista de aquellas personas menores de 25 años con un filter además de imprimir por pantalla
    List<Persona> persona = per.stream().map(p -> toPersona(p)).collect(toList());
    persona.stream().filter(p -> p.getEdad() < 25).forEach(System.out::println);


  }

  public static Persona toPersona(String linea)  {
    Persona persona = new Persona();
    //Extraemos los valores de la linea y después de comprobar si están o no vacios  lo añadimos al objeto persona
    String[] valores = linea.split(":",3);
    //System.out.println(valores.length);

    if (valores[0] != "") {
      persona.setNombre(valores[0]);
    } else {
      persona.setNombre("Desconocido");
    }
    if (valores[1] != "") {
      persona.setPoblacion(valores[1]);
    } else {
      persona.setPoblacion("Desconocida");
    }
    if (valores[2] != "") {
      persona.setEdad(Integer.parseInt(valores[2]));
    } else {
      persona.setEdad(26);

    }
    return persona;


  }

}
