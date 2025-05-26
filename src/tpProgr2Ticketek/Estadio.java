package tpProgr2Ticketek;

public class Estadio extends Sede{
	//Los atributos son heredados 
	
	//constructor
	public Estadio(String nombre, String direccion, int capacidad) {
		
		super(nombre, direccion, capacidad);
		
	}
	@Override
	public double calcularPrecio(Funcion funcion, String platea) {
		return funcion.getPrecioBase(); // el estadio tiene solo campo y es precio unico.
	}
	
	public String getNombre() {
		return super.getNombre();
	}


}
