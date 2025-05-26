package tpProgr2Ticketek;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Funcion {
	private Date fecha;
	private List <Sede> sedes;
	private double precioBase;
	private List <Entrada> entradasVendidas;

	//constructor
	public Funcion(Date fecha, List<Sede> sedes, double precioBase) {
        this.fecha = fecha;
        this.sedes = new ArrayList<>(sedes); // Copia para evitar modificaciones externas
        this.precioBase = precioBase;
        this.entradasVendidas = new ArrayList<>();
    }

    public boolean estaDisponible(Sede sede) {
        return sedes.contains(sede);
    }
    
    // Método para agregar entradas vendidas (llamado desde Ticketek)
    public void addEntradaVendida(Entrada entrada) {
        this.entradasVendidas.add(entrada);
    }

	public double calcularPrecioFinal(String asientoSector) {
       
        if (sedes.isEmpty()) {
            throw new RuntimeException("La función no tiene una sede asociada para calcular el precio.");
        }
        Sede sedePrincipal = sedes.get(0); // se toma la primera sede asociada a la función

        // El cálculo de precio lo delega a la Sede polimórficamente
        return sedePrincipal.calcularPrecio(this, asientoSector);
    }


   	public List<Entrada> getEntradasVendidas() {
       		return entradasVendidas;
    	}

	public Date getFecha() {
        	return fecha;
    	}

    public double getPrecioBase() {
        return precioBase;
	}

    public Sede[] getSedes() {
        return sedes.toArray(new Sede[0]);
    }

	public Sede getSede() { // Getter para obtener la Sede principal de la función
        if (sedes.isEmpty()) {
            return null; // O lanzar excepción si una función siempre debe tener una sede
        }
        return sedes.get(0); // Retorna la primera sede asociada a esta función
    }

}
