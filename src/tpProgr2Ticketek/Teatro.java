package tpProgr2Ticketek;

public class Teatro extends SedeConPlatea{
	
	// Los atributos no son necesarios porque se heredan

	//contructor
	public Teatro(String nombre, String direccion, int capacidad, int asientoXfila, String[] plateas, int[] capacidadSector, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidad, asientoXfila, plateas, capacidadSector, porcentajeAdicional);
    }

	
}
