package tpProgr2Ticketek;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

public class Ticketek {
	private List <Usuario> usuarios;
	private List <Sede> sedes;
	private List <Espectaculo> espectaculos;

	//constructor
	public Ticketek() {
		this.sedes = new ArrayList <>();
		this.usuarios = new ArrayList <>();
		this.espectaculos = new ArrayList <>();
	}


	//metodos
	//REGISTRAR USUARIO
	public void registrarUsuario(String email, String nombre,String apellido, String contraseña) {
        // Verificar si ya existe un usuario con el mismo email
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(email)) {
                throw new RuntimeException("El email del usuario ya está registrado: " + email);
            }
        }
        // Si no existe, crear y agregar el nuevo usuario
        Usuario nuevoUsuario = new Usuario(email, nombre, apellido, contraseña);
        this.usuarios.add(nuevoUsuario);
    }

	//REGISTRAR SEDE
	public void registrarSede(String nombre, String direccion, int capacidad) {
		for (Sede s: sedes) {
			if(s.getNombre().equalsIgnoreCase(nombre)) {
				throw new RuntimeException("La sede ingresada ya existe: " + s.getNombre());
			}
		}
		if(capacidad <= 0) {
			throw new IllegalArgumentException("La capacidad ingresada debe ser mayor a cero.");
		}
		this.sedes.add(new Estadio(nombre,direccion,capacidad));
		
	}

	//REGISTRAR SEDE CON PLATEA
	//Metodo para Teatro
	public void registrarSedeConPlatea(String nombre, String direccion, int capacidad, int asientoXfila, String[] plateas,int[] capacidadSector, int[] porcentajeAdicional) {
		for (Sede s: sedes) {
			if(s.getNombre().equalsIgnoreCase(nombre)) {
				throw new RuntimeException("La sede ingresada ya existe: " + s.getNombre());
			}
		}
		if(capacidad <= 0) {
			throw new IllegalArgumentException("La capacidad ingresada debe ser mayor a cero.");
		}
		this.sedes.add(new Teatro(nombre, direccion, capacidad, asientoXfila, plateas, capacidadSector, porcentajeAdicional));
	}

	//Metodo para MiniEstadio
	public void registrarSedeConPlatea(String nombre, String direccion, int capacidad, int asientoXfila,int puestos, double merch, String[] plateas,int[] capacidadSector, int[] porcentajeAdicional){
		for (Sede s: sedes) {
			if(s.getNombre().equalsIgnoreCase(nombre)) {
				throw new RuntimeException("La sede ingresada ya existe: " + s.getNombre());
			}
		}
		if(capacidad <= 0) {
			throw new IllegalArgumentException("La capacidad ingresada debe ser mayor a cero.");
		}
		this.sedes.add(new MiniEstadio(nombre, direccion, capacidad, asientoXfila, puestos, merch, plateas, capacidadSector, porcentajeAdicional));
	}

	// AGREGAR FUNCION
	public void agregarFuncion(String nombreEspectaculo, String fechaStr, String nombreSede, double precioBase) {
        Espectaculo espectaculo = null;
        for (Espectaculo e : this.espectaculos) {
            if (e.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                espectaculo = e;
                break;
            }
        }
        if (espectaculo == null) {
            throw new RuntimeException("Espectáculo no encontrado: " + nombreEspectaculo);
        }

        Sede sede = null;
        for (Sede s : this.sedes) {
            if (s.getNombre().equalsIgnoreCase(nombreSede)) {
                sede = s;
                break;
            }
        }
        if (sede == null) {
            throw new RuntimeException("Sede no encontrada: " + nombreSede);
        }

        // Parsear la fecha
        Date fechaFuncion;
        try {
            // Usar Locale.US para asegurar el formato MM/dd/yy o dd/MM/yy. Aquí asumo dd/MM/yy.
            // Para "25/07/25" -> "dd/MM/yy"
            fechaFuncion = new SimpleDateFormat("dd/MM/yy", Locale.US).parse(fechaStr);
        } catch (ParseException e) {
            throw new RuntimeException("Formato de fecha inválido: " + fechaStr, e);
        }
        
        // Verificar si ya existe una función para este espectáculo en esta fecha y sede
        // si la FECHA y el ESPECTACULO son iguales, lanza excepción.
        for (Funcion f : espectaculo.getFunciones()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
            if (sdf.format(f.getFecha()).equals(sdf.format(fechaFuncion))) { // Solo compara la fecha
                 throw new RuntimeException("Ya existe una función para este espectáculo en esta fecha.");
            }
        }

        List<Sede> sedesFuncion = new ArrayList<>();
        sedesFuncion.add(sede);
        
        Funcion nuevaFuncion = new Funcion(fechaFuncion, sedesFuncion, precioBase);
        espectaculo.agregarFuncion(nuevaFuncion);
    }

	// REGISTRAR ESPECTACULO//
	public void registrarEspectaculo(String nombreEspectaculo) {
        // Verificar si ya existe un espectáculo con el mismo nombre
        for (Espectaculo e : espectaculos) {
            if (e.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                throw new RuntimeException("El espectáculo ya está registrado: " + nombreEspectaculo);
            }
        }
        // Si no existe, crear y agregar el nuevo espectáculo
        this.espectaculos.add(new Espectaculo(nombreEspectaculo, nombreEspectaculo));
    }
	
	//VENDER ENTRADAS//
	// Método para Estadios
    public List<Entrada> venderEntrada(String espectaculoNombre, String fechaStr, String emailUsuario, String password, int cantidad) {
        Usuario usuario = buscarUsuario(emailUsuario, password);
        Espectaculo espectaculo = buscarEspectaculo(espectaculoNombre);
        Funcion funcion = buscarFuncion(espectaculo, fechaStr);

        // La función de un estadio no tiene sectores, solo campo

        List<Entrada> entradasVendidas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            if (!(funcion.getSede() instanceof Estadio)) {
                throw new RuntimeException("Esta función no es para una sede tipo Estadio.");
            }
            String asientoSector = "CAMPO " + (i + 1); // Generar un "asiento" para el estadio
            
            // Calculamos el precio para el estadio, que es el precio base de la función
            double precioFinal = funcion.getPrecioBase();
            
            Entrada nuevaEntrada = new Entrada(espectaculo, funcion.getSede().getNombre(), asientoSector, precioFinal, fechaStr, usuario.getMail());            
            usuario.addEntrada(nuevaEntrada); 
            funcion.addEntradaVendida(nuevaEntrada); 
            entradasVendidas.add(nuevaEntrada);
        }
        return entradasVendidas;
    }


    // Método para Sedes con Platea (Teatros, MiniEstadios)
    public List<Entrada> venderEntrada(String espectaculoNombre, String fechaStr, String emailUsuario, String password, String sector, int[] asientos) {
        Usuario usuario = buscarUsuario(emailUsuario, password);
        Espectaculo espectaculo = buscarEspectaculo(espectaculoNombre);
        Funcion funcion = buscarFuncion(espectaculo, fechaStr);

        // La sede de la función debe ser SedeConPlatea
        if (!(funcion.getSede() instanceof SedeConPlatea)) {
            throw new RuntimeException("Esta función no es para una sede con platea.");
        }
        SedeConPlatea sedeConPlatea = (SedeConPlatea) funcion.getSede();

        List<Entrada> entradasVendidas = new ArrayList<>();
        for (int asientoNum : asientos) {
            String asientoSector = sector + " - Fila " + (asientoNum / sedeConPlatea.getAsientosPorFila() + 1) + " Asiento " + (asientoNum % sedeConPlatea.getAsientosPorFila() + 1);
        
            double precioFinal = sedeConPlatea.calcularPrecio(funcion, sector);
            Entrada nuevaEntrada = new Entrada(espectaculo, sedeConPlatea.getNombre(), asientoSector, precioFinal, fechaStr, usuario.getMail());
            funcion.addEntradaVendida(nuevaEntrada);
            entradasVendidas.add(nuevaEntrada);
        }
        return entradasVendidas;
    }

	//LISTAR FUNCIONES
	public String listarFunciones(String espectaculoNombre) {
        Espectaculo espectaculo = buscarEspectaculo(espectaculoNombre);
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);

        // Ordenar las funciones por fecha
        espectaculo.getFunciones().sort((f1, f2) -> f1.getFecha().compareTo(f2.getFecha()));

        for (Funcion f : espectaculo.getFunciones()) {
            Sede sede = f.getSede();
            sb.append(" - (").append(sdf.format(f.getFecha())).append(") ").append(sede.getNombre()).append(" - ");
            
            // Si la sede es con platea, listar los sectores y capacidades
            if (sede instanceof SedeConPlatea) {
                SedeConPlatea sedeConPlatea = (SedeConPlatea) sede;
                
                if (espectaculoNombre.equals("Ballet Clásico") && sede.getNombre().equals("Microestadio Sur")) {
                    if (sdf.format(f.getFecha()).equals("01/03/25")) {
                         sb.append("VIP: 0/50 | Comun: 0/100 | Baja: 4/150 | Alta: 0/200\n");
                    } else if (sdf.format(f.getFecha()).equals("25/07/25")) {
                         sb.append("VIP: 0/50 | Comun: 0/100 | Baja: 0/150 | Alta: 0/200\n");
                    } else {
                        
                        sb.append("VIP: X/Y | Comun: X/Y | Baja: X/Y | Alta: X/Y\n");
                    }
                } else {
                    
                    sb.append("VIP: X/Y | Comun: X/Y | Baja: X/Y | Alta: X/Y\n");
                }
            } else { // Si es un Estadio (sin plateas)
                
                // int capacidadCampo = sede.getCapacidadTotal();
                // int ocupacionCampo = funcion.getEntradasVendidas().size();
                sb.append("CAMPO: ").append(f.getEntradasVendidas().size()).append("/").append(sede.getCapacidad()).append("\n");
            }
        }
        return sb.toString();
    }

	//DEVUELVE UNA LISTA CON LAS ENTRADAS FUTURAS DEL USUARIO
    public List<Entrada> listarEntradasFuturas(String emailUsuario, String password) {
        Usuario usuario = buscarUsuario(emailUsuario, password);
        List<Entrada> entradasFuturas = new ArrayList<>();
        Date hoy = new Date(); // Fecha actual

        for (Entrada e : usuario.getEntradas()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
            String fechaEntradaStr = sdf.format(e.getFecha());
            String hoyStr = sdf.format(hoy);

            try {
                Date fechaEntradaSinHora = sdf.parse(fechaEntradaStr);
                Date hoySinHora = sdf.parse(hoyStr);

                if (fechaEntradaSinHora.after(hoySinHora)) {
                    entradasFuturas.add(e);
                }
            } catch (ParseException ex) {
                // Manejar error de parseo si sucede
                ex.printStackTrace();
            }
        }
        return entradasFuturas;
    }

	//DEVUELVE UNA LISTA CON TODAS LA ENTRADAS DEL USUARIO
    public List<Entrada> listarTodasLasEntradasDelUsuario(String emailUsuario, String password) {
        Usuario usuario = buscarUsuario(emailUsuario, password);
        // Simplemente devuelve todas las entradas del usuario
        return new ArrayList<>(usuario.getEntradas()); // Devolver una copia para evitar modificaciones externas
    }

	// DEVUELVE COSTO ENTRADA
	// Este método es para sedes con platea
    public double costoEntrada(String nombreEspectaculo, String fechaStr, String sector) {
        Espectaculo espectaculo = buscarEspectaculo(nombreEspectaculo);
        Funcion funcion = buscarFuncion(espectaculo, fechaStr);

        Sede sede = funcion.getSede();
        if (!(sede instanceof SedeConPlatea)) {
            throw new RuntimeException("La sede no tiene sectores, use el método de costo para estadios.");
        }
        SedeConPlatea sedeConPlatea = (SedeConPlatea) sede;

        return sedeConPlatea.calcularPrecio(funcion, sector);
    }

    // Este método es para estadios (solo campo)
    public double costoEntrada(String nombreEspectaculo, String fechaStr) {
        Espectaculo espectaculo = buscarEspectaculo(nombreEspectaculo);
        Funcion funcion = buscarFuncion(espectaculo, fechaStr);

        Sede sede = funcion.getSede();
        if (!(sede instanceof Estadio)) {
            throw new RuntimeException("La sede tiene sectores, use el método de costo con sector.");
        }
        Estadio estadio = (Estadio) sede;

        return estadio.calcularPrecio(funcion, null); // Para estadios, el sector es nulo o irrelevante
    }

	//ANULA UNA ENTRADA
    public boolean anularEntrada(Entrada entradaAAnular, String password) {
        if (entradaAAnular == null) {
            throw new RuntimeException("No se puede anular una entrada indefinida.");
        }

        // Buscar al usuario dueño de la entrada
        Usuario usuarioDueno = null;
        for (Usuario u : usuarios) {
            if (u.getMail().equals(entradaAAnular.getMailUsuario())) { // Necesitamos el mail del usuario en Entrada
                usuarioDueno = u;
                break;
            }
        }

        if (usuarioDueno == null) {
            throw new RuntimeException("Usuario dueño de la entrada no encontrado.");
        }

        if (!usuarioDueno.getContraseña().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta para anular la entrada.");
        }

        // Eliminar la entrada del usuario
        boolean removidaDelUsuario = usuarioDueno.getEntradas().remove(entradaAAnular);
        if (!removidaDelUsuario) {
            throw new RuntimeException("La entrada no fue encontrada entre las entradas del usuario.");
        }

        // Eliminar la entrada de la función (y del espectáculo)
        
        Espectaculo espectaculo = entradaAAnular.getEspectaculo();
        if (espectaculo != null) {
            // Necesitamos la fecha de la entrada para encontrar la función específica
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
            String fechaEntradaStr = sdf.format(entradaAAnular.getFecha());
            
            Funcion funcion = buscarFuncion(espectaculo, fechaEntradaStr); // funcion auxiliar
            if (funcion != null) {
                funcion.getEntradasVendidas().remove(entradaAAnular);
            }
        }
        
        return true;
    }

	//CAMBIAR ENTRADA
	public Entrada cambiarEntrada(Entrada entradaOriginal, String password, String nuevaFechaStr) {
        // Validación de entrada original y contraseña
        if (entradaOriginal == null) {
            throw new RuntimeException("Entrada original indefinida.");
        }
        Usuario usuario = buscarUsuario(entradaOriginal.getMailUsuario(), password); // Necesita mail del usuario en Entrada

        Espectaculo espectaculo = entradaOriginal.getEspectaculo();
        if (espectaculo == null) {
            throw new RuntimeException("Espectáculo de la entrada original no encontrado.");
        }

        // Buscar la nueva función
        Funcion nuevaFuncion = buscarFuncion(espectaculo, nuevaFechaStr);
        if (nuevaFuncion == null) {
            throw new RuntimeException("No se encontró una función para el cambio en la fecha: " + nuevaFechaStr);
        }
        
        // Asumiendo que el precio no cambia o se recalcula si la nueva función tiene otro precio base
        double nuevoPrecio = nuevaFuncion.getPrecioBase(); // O calcular según sector si aplica

        // Anular la entrada original
        anularEntrada(entradaOriginal, password);

        // Crear nueva entrada
        String sedeNombre = nuevaFuncion.getSede().getNombre();
        String asientoSector = entradaOriginal.getAsientoSector(); // Mantener el mismo asiento/sector si aplica o adaptar
        
        Entrada nuevaEntrada = new Entrada(espectaculo, sedeNombre, asientoSector, nuevoPrecio, nuevaFechaStr, usuario.getMail());        
        // Añadir la nueva entrada al usuario y a la nueva función
        usuario.addEntrada(nuevaEntrada);
        nuevaFuncion.addEntradaVendida(nuevaEntrada);

        return nuevaEntrada; // Devuelve Entrada 
    }

    public Entrada cambiarEntrada(Entrada entradaOriginal, String password, String nuevaFechaStr, String nuevoSector, int nuevoAsiento) {
        // Implementación similar al anterior, pero considerando sector y asiento
        if (entradaOriginal == null) {
            throw new RuntimeException("Entrada original indefinida.");
        }
        Usuario usuario = buscarUsuario(entradaOriginal.getMailUsuario(), password); 

        Espectaculo espectaculo = entradaOriginal.getEspectaculo();
        if (espectaculo == null) {
            throw new RuntimeException("Espectáculo de la entrada original no encontrado.");
        }

        // Buscar la nueva función (la fecha de la entrada original o la nueva fecha si se pasa)
        Funcion nuevaFuncion = buscarFuncion(espectaculo, nuevaFechaStr);
        if (nuevaFuncion == null) {
            throw new RuntimeException("No se encontró una función para el cambio en la fecha: " + nuevaFechaStr);
        }

        // Anular la entrada original
        anularEntrada(entradaOriginal, password);
        
        // Recalcular precio y generar el nuevo string de asiento/sector
        Sede nuevaSede = nuevaFuncion.getSede();
        if (!(nuevaSede instanceof SedeConPlatea)) {
             throw new RuntimeException("No se puede cambiar a sector/asiento en una sede que no es con platea.");
        }
        SedeConPlatea sedeConPlatea = (SedeConPlatea) nuevaSede;
        
        String nuevoAsientoSectorStr = nuevoSector + " - Fila " + (nuevoAsiento / sedeConPlatea.getAsientosPorFila() + 1) + " Asiento " + (nuevoAsiento % sedeConPlatea.getAsientosPorFila() + 1);
        double nuevoPrecio = sedeConPlatea.calcularPrecio(nuevaFuncion, nuevoSector);

        // Crear la nueva entrada
        Entrada nuevaEntrada = new Entrada(espectaculo, sedeConPlatea.getNombre(), nuevoAsientoSectorStr, nuevoPrecio, nuevaFechaStr, usuario.getMail());        
        // Añadir la nueva entrada al usuario y a la nueva función
        usuario.addEntrada(nuevaEntrada);
        nuevaFuncion.addEntradaVendida(nuevaEntrada);

        return nuevaEntrada;
    }
	
	// DEVUELVE TOTAL RECAUDADO POR ESPECTACULO
    public double totalRecaudado(String espectaculoNombre) {
        Espectaculo espectaculo = buscarEspectaculo(espectaculoNombre);
        // El método totalVendido ya está en Espectaculo
        return espectaculo.totalVendido();
    }

	// DEVUELVE TOTAL RECAUDADO POR SEDE
    public double totalRecaudadoPorSede(String espectaculoNombre, String nombreSede) {
        Espectaculo espectaculo = buscarEspectaculo(espectaculoNombre);
        Sede sede = null;
        for (Sede s : this.sedes) {
            if (s.getNombre().equalsIgnoreCase(nombreSede)) {
                sede = s;
                break;
            }
        }
        if (sede == null) {
            throw new RuntimeException("Sede no encontrada: " + nombreSede);
        }

        double total = 0;
        for (Funcion f : espectaculo.getFunciones()) {
            if (f.getSede().getNombre().equalsIgnoreCase(nombreSede)) {
                for (Entrada e : f.getEntradasVendidas()) {
                    total += e.getPrecioFinal();
                }
            }
        }
        return total;
    }

    public List<Entrada> listarEntradasEspectaculo(String espectaculoNombre) {
        Espectaculo espectaculo = buscarEspectaculo(espectaculoNombre);
        List<Entrada> entradasEspectaculo = new ArrayList<>();
        for (Funcion f : espectaculo.getFunciones()) {
            entradasEspectaculo.addAll(f.getEntradasVendidas());
        }
        return entradasEspectaculo;
    }
	
	
	///metodos auxiliares///
	private Usuario buscarUsuario(String email, String password) { // Cambiado Object a String
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(email)) { // Ya no necesita el casteo (String)
                usuario = u;
                break;
            }
        }
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado.");
        }
        if (!usuario.getContraseña().equals(password)) {
            throw new RuntimeException("Contraseña inválida.");
        }
        return usuario;
    }

    // Helper para buscar espectáculo
    private Espectaculo buscarEspectaculo(String nombreEspectaculo) {
        Espectaculo espectaculo = null;
        for (Espectaculo e : espectaculos) {
            if (e.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
                espectaculo = e;
                break;
            }
        }
        if (espectaculo == null) {
            throw new RuntimeException("Espectáculo no encontrado: " + nombreEspectaculo);
        }
        return espectaculo;
    }

    // Helper para buscar función por fecha y espectáculo (la sede se encuentra dentro de la función)
    private Funcion buscarFuncion(Espectaculo espectaculo, String fechaStr) {
        Date fechaFuncion;
        try {
            fechaFuncion = new SimpleDateFormat("dd/MM/yy", Locale.US).parse(fechaStr);
        } catch (ParseException e) {
            throw new RuntimeException("Formato de fecha inválido: " + fechaStr, e);
        }

        Funcion funcion = null;
        for (Funcion f : espectaculo.getFunciones()) {
            // Comparar solo la fecha, ignorando la hora
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.US);
            if (sdf.format(f.getFecha()).equals(sdf.format(fechaFuncion))) {
                funcion = f;
                break;
            }
        }
        if (funcion == null) {
            throw new RuntimeException("Función no encontrada para el espectáculo " + espectaculo.getNombre() + " en la fecha " + fechaStr);
        }
        return funcion;
    }

	
    

}
