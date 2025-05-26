package tpProgr2Ticketek;


public class MiniEstadio extends SedeConPlatea{
	
	private int puestos;
	private double merch;
	

	public MiniEstadio(String nombre, String direccion, int capacidad, int asientoXfila,int puestos, double merch, String[] plateas,int[] capacidadSector, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidad, asientoXfila,plateas,capacidadSector,porcentajeAdicional);
		this.puestos = puestos;
		this.merch = merch;
	}

	@Override
    public double calcularPrecio(Funcion funcion, String platea) {
        return super.calcularPrecio(funcion, platea) + this.merch;
    }
	
}
