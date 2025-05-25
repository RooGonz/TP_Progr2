package tpProgr2Ticketek;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Funcion {
	private Fecha fecha;
	private List <Sede> sedes;
	private double precioBase;
	private List <Entrada> entradasVendidas;

	//constructor
	public crearFuncion(Fecha fecha, List<Sede> sedes, double precioBase) {   
        	this.fecha = fecha;
        	this.sedes = sedes;
        	this.precioBase = precioBase;
        	this.entradasVendidas = new ArrayList<>();

	}

	//operaciones 
	public boolean estaDisponible(Sede sede) {
		return sedes.contains(sede); 		// devuelve una lista conl as sedes de esa funcion
	
	}
	
	public void venderEntrada(String mail, String contraseña, Espectaculo espectaculo, String sedeNombre, String funcion, String asientoSector) {
		if (mail == null) {
            		System.out.println("Usuario no válido");
            		return;
       		}

        	Sede sede = null;
       		for (Sede s : sedes) {						//recorre las sedes buscando la indicada para al venta de la entrada
            		if (s.getNombre().equalsIgnoreCase(sedeNombre)) {	 // cuando la encuentra sale del ciclo
                		sede = s;
                		break;
            		}
        	}

        	if (sede == null) {					//si la sede queda en null despues de terminar con el ciclo
           		System.out.println("Sede no encontrada");	 // quiere decir que no la encontro
            		return;
       		}

        	double precioFinal = calcularPrecioFinal(asientoSector);	//calcula el precio de la entrada a vender

        	Entrada entrada = new Entrada(new Date(), espectaculo, sede.getNombre(), asientoSector, int precioFinal);  //se crea la entrada
        	usuario.getEntradas().add(entrada);						//se agrega a la lista de entradas del usuario
        	entradasVendidas.add(entrada);						//y se agrega a la lista de vendidas

        	System.out.println("Entrada vendida a " + usuario.getMail() + " por $" + precioFinal);  //informa la accion (NO SE SI ES NECESARIO)
	}
	
	public double calcularPrecioFinal(String asientoSector) {
		//COMPLETAR
	}

	public void anularEntrada(String codigo, String contraseña, Funcion funcion) {
    		if (!this.contraseña.equals(contraseña)) {			//controla el usuario
        		System.out.println("Contraseña incorrecta.");
        		return;
    		}

    		Entrada encontrada = null;					//busca la enrada
    		for (Entrada e : entradas) {
        		if (e.getCodigo().equals(codigo)) {
            			encontrada = e;
            			break;
        		}
    		}

    		if (encontrada != null) {					//cuando encuentra alentrada la elimina
        		entradas.remove(encontrada);				  //de la lista de entradas
        		funcion.getEntradasVendidas().remove(encontrada);
        		System.out.println("Entrada anulada en el sistema.");
        		return;
    		} else {
        		System.out.println("No se encontró la entrada.");
        		return;
    		}
	}


   	public List<Entrada> getEntradasVendidas() {
       		return entradasVendidas;
    	}

	public Fecha getFecha() {
        	return fecha;
    	}
}
