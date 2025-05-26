package tpProgr2Ticketek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SedeConPlatea extends Sede{
	
	private int asientoXfila;
	private List <Integer> porcentajeAdicional;
	private List<String> plateas;
	private List <Integer> capacidadSector;
	

	// Constructor para Teatro
    public SedeConPlatea(String nombre, String direccion, int capacidad, int asientoXfila, String[] plateasArr, int[] capacidadSectorArr, int[] porcentajeAdicionalArr) {
        super(nombre, direccion, capacidad);
        this.asientoXfila = asientoXfila;
        this.plateas = new ArrayList<>(Arrays.asList(plateasArr));
        this.capacidadSector = new ArrayList<>();
        for (int cap : capacidadSectorArr) {
            this.capacidadSector.add(cap);
        }
        this.porcentajeAdicional = new ArrayList<>();
        for (int perc : porcentajeAdicionalArr) {
            this.porcentajeAdicional.add(perc);
        }
    }

    // Constructor para MiniEstadio
    public SedeConPlatea(String nombre, String direccion, int capacidad, int asientoXfila, int puestos, double merch, String[] plateasArr, int[] capacidadSectorArr, int[] porcentajeAdicionalArr) {
        super(nombre, direccion, capacidad);
        this.asientoXfila = asientoXfila;
        this.plateas = new ArrayList<>(Arrays.asList(plateasArr));
        this.capacidadSector = new ArrayList<>();
        for (int cap : capacidadSectorArr) {
            this.capacidadSector.add(cap);
        }
        this.porcentajeAdicional = new ArrayList<>();
        for (int perc : porcentajeAdicionalArr) {
            this.porcentajeAdicional.add(perc);
        }
        
    }

    @Override
	public double calcularPrecio(Funcion funcion, String platea) {
        int index = plateas.indexOf(platea);
        if (index == -1) {
            throw new RuntimeException("Platea no válida: " + platea);
        }
        double precioBase = funcion.getPrecioBase();
        double porcentaje = porcentajeAdicional.get(index) / 100.0; // Convertir a decimal
        return precioBase * (1 + porcentaje);
    }
    
    public int getAsientosPorFila() {
        return asientoXfila;
    }

    public List<String> getPlateas() {
        return plateas;
    }

    public int getCapacidadPorSector(String sector) {
        int index = plateas.indexOf(sector);
        if (index == -1) {
            throw new RuntimeException("Sector no válido: " + sector);
        }
        return capacidadSector.get(index);
    }
}
