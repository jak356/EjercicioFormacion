public class Persona {
  private String Nombre;
  private String Poblacion;
  private int Edad;
  private int count;
  public Persona(String nombre,String poblacion,int edad)
  {
    String Nombre = nombre;
    String Poblacion = poblacion;
    int Edad = edad;
  }
  public Persona()
  {

  }

  public String getNombre() {
    return Nombre;
  }

  public void setNombre(String nombre) {
    Nombre = nombre;
  }

  public String getPoblacion() {
    return Poblacion;
  }

  public void setPoblacion(String poblacion) {
    Poblacion = poblacion;
  }

  public int getEdad() {
    return Edad;
  }

  public void setEdad(int edad) {
    Edad = edad;
  }

  @Override
  public String toString() {

    return
        "Nombre:" + Nombre + '.' +
        "Población:" + Poblacion + '.' +
        "Edad:" + Edad +"\n";
  }
}
