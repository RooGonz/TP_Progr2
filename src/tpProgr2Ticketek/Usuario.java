package tpProgr2Ticketek;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String mail;
    private String nombre;
    private String apellido;
    private String contraseña;
    private List <Entrada> entradas;

    public Usuario (String mail, String nombre, String apellido, String contraseña) {
        this.mail = mail;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.entradas = new ArrayList<>();
    }

    public void addEntrada(Entrada entrada) {
        this.entradas.add(entrada);
    }

    public void removeEntrada(Entrada entrada) {
        this.entradas.remove(entrada);
    }


// Getters 
  public String getMail() {
      return mail;
  }

  public String getContraseña() {
      return contraseña;
  }

  public List<Entrada> getEntradas() {                  //con este get obtengo una lista de las entradas... y como tiene fechas puedo saber si son futuraqs o viejas
      return entradas;
  }

}
