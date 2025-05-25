package tpProgr2Ticketek;

  import java.util.Date;
  import java.util.UUID;          // propone para generar un codigo automatico para la entrada


  public class Entrada {
  
	    private Fecha fecha;
	    private Espectaculo espectaculo;
	    private String sede;
	    private String asientoSector;
	    private int precioFinal;

  //conatructor
	public void crearEntrada(Fecha fecha, Espectaculo espectaculo, Sting Sede, String asientoSector, int precioFinal) {
 
	  	this.codigo = UUID.randomUUID().toString();
      		this.fecha = fecha;
	  	this.espectaculo = espectaculo;
	  	this.sede = sede;
	  	this.asientoSector = asientoSector;
	  	this.precioFinal = precioFinal;
  	}

   
// Getters y Setters
  public String getCodigo() {
      return codigo;
    }

  public Date getFecha() {
      return fecha;
    }

  public void setFecha(Date fecha) {
      this.fecha = fecha;
    }

  public Espectaculo getEspectaculo() {
      return espectaculo;
    }

  public void setEspectaculo(Espectaculo espectaculo) {
      this.espectaculo = espectaculo;
    }

  public String getSede() {
      return sede;
    }

  public void setSede(String sede) {
      this.sede = sede;
    }

  public String getAsientoSector() {
      return asientoSector;
    }

  public void setAsientoSector(String asientoSector) {
      this.asientoSector = asientoSector;
    }

  public int getPrecioFinal() {
      return precioFinal;
    }

  public void setPrecioFinal(int precioFinal) {
      this.precioFinal = precioFinal;
    }
}
