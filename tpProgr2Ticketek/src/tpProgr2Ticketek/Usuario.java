package tpProgr2Ticketek;

  import java.util.Date;
  import java.util.List;

  public class Usuario {
	
	      private String mail;
	      private String nombre;
	      private String apellido;
	      private String contraseña;
	      private List <Entrada> entradas;
	
	public void crearUsuario (String mail, String nombre, String apellido, String contraseña) {
		this.mail = mail;
        	this.nombre = nombre;
        	this.apellido = apellido;
        	this.contraseña = contraseña;
        	this.entradas = new ArrayList<>();
	}

	public void comprarEntrada(Funcion funcion, Espectaculo espectaculo, String sede, String asientoSector) {
    		funcion.venderEntrada(this.mail, this.contraseña, espectaculo, sede, asientoSector);
	}

	public void anularEntrada(String codigo, String contraseña) {

		if (!this.contraseña.equals(contraseña)) {			//controla la contraseña
       			System.out.println("Contraseña incorrecta.");
    		}

    		for (Entrada e : entradas) {					//buscala entrada en la lista del usuario
        		if (e.getCodigo().equals(codigo)) {			  cuando la encuntra la elimina
				entradas.remove(e);
            			System.out.println("Entrada anulada con éxito.");
        		}
    		}

    		System.out.println("No se encontró una entrada con ese código.");
	}


// Getters y Setters
  public String getMail() {
      return mail;
  }

  public void setMail(String mail) {
      this.mail = mail;
  }

  public String getNombre() {
      return nombre;
  }

  public void setNombre(String nombre) {
      this.nombre = nombre;
  }  
  
  public String getApellido() {
      return apellido;
  }

  public void setApellido(String apellido) {
      this.apellido = apellido;
  }

  public String getContraseña() {
      return contraseña;
  }

  public void setContraseña(String contraseña) {
      this.contraseña = contraseña;
  }

  public List<Entrada> getEntradas() {                  //con este get obtengo una lista de las entradas... y como tiene fechas puedo saber si son futuraqs o viejas
      return entradas;
  }

  public void setEntradas(List<Entrada> entradas) {
      this.entradas = entradas;
  }

}
