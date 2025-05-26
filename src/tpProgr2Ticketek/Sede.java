package tpProgr2Ticketek;

public abstract class Sede {
	
	private String nombre;
	private int capacidad;
	private String direccion;

	//constructor
	public Sede(String nombre, String direccion, int capacidad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidad = capacidad;
		
	}
	
	public abstract double calcularPrecio (Funcion funcion, String platea);

	public  String getNombre(){
		return nombre;
	}

    public int getCapacidad(){
		return capacidad;
	}

}
