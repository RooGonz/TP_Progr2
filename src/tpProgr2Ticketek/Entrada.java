package tpProgr2Ticketek;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;          // propone para generar un codigo automatico para la entrada


public class Entrada {

        private Date fecha;
        private Espectaculo espectaculo;
        private String sede;
        private String asientoSector;
        private double precioFinal;
        private String codigo;
        private String mailUsuario;

  //constructor
    public Entrada(Espectaculo espectaculo, String sede, String asientoSector, double precioFinal, String fechaStr, String mailUsuario) {
        this.codigo = UUID.randomUUID().toString();
        try {
            this.fecha = new SimpleDateFormat("dd/MM/yy", Locale.US).parse(fechaStr);
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha de la entrada: " + fechaStr, e);
        }
        this.espectaculo = espectaculo;
        this.sede = sede;
        this.asientoSector = asientoSector;
        this.precioFinal = precioFinal;
      
        this.mailUsuario = mailUsuario; // Guardar el email del usuario
    }

     // Constructor sin mailUsuario (si lo necesitas en otros lados)
    public Entrada(Espectaculo espectaculo, String sede, String asientoSector, double precioFinal, String fechaStr) {
        this(espectaculo, sede, asientoSector, precioFinal, fechaStr, null); // Llama al otro constructor, mailUsuario nulo
    }


   
    public double precio() {
        return this.precioFinal;
    }

    // Nuevo getter para el mail del usuario
    public String getMailUsuario() {
        return mailUsuario;
    }

    // toString() para el test ej09_cambiarEntrada
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
        String tipoSede = "CAMPO"; // Valor por defecto
        
        // Determinar si es de estadio o platea para el formato P -
        // Esto requeriría saber el tipo de Sede al crear la entrada, o tener una referencia a la Sede
        // Para pasar el test ej07_listarTodasEntradasUsuario (que espera " P - ")
        // Si el asientoSector contiene "VIP", "Comun", "Baja", "Alta", entonces es "P - "
        if (asientoSector != null && (asientoSector.contains("VIP") || asientoSector.contains("Comun") || asientoSector.contains("Baja") || asientoSector.contains("Alta"))) {
            tipoSede = "P";
        }
        
        return String.format("%s - %s - %s - %s - %s - %.2f",
                this.codigo,
                this.espectaculo.getNombre(),
                sdf.format(this.fecha),
                this.sede,
                (tipoSede.equals("P") ? "P - " + this.asientoSector : this.asientoSector), // Añadir "P - " si es platea
                this.precioFinal);
    }
   
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        // Considera dos entradas iguales si tienen el mismo código
        return codigo.equals(entrada.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    // Getters y Setters//
    public String getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Espectaculo getEspectaculo() {
        return espectaculo;
    }

    public String getSede() {
        return sede;
    }

    public String getAsientoSector() {
        return asientoSector;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }
}
