package tpProgr2Ticketek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Espectaculo {
    private String codigo;
    private String nombre;
    private List<Funcion> funciones;
 

    // Constructor 
     public Espectaculo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.funciones = new ArrayList<>();
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public List<String> verTodasLasSedes() {                                      //devuelve lista con todas las funciones
        Set<String> sedes = new HashSet<>();                                      // Usamos Set para evitar repetidos, no es un map porque no me sirve de nada terne clave y dato
        for (Funcion f : funciones) {
            for (Sede s : f.getSedes()) {
                sedes.add(s.getNombre());
            }
        }
        return new ArrayList<>(sedes);
    }

    public double totalVendido() {
        double total = 0;                                    //recorre totdas las funciones y y por cada funcion las entradas vendidas
        for (Funcion f : funciones) {                        // y suma el preci0 final de todas las entradas
            for (Entrada e : f.getEntradasVendidas()) {
                total += e.getPrecioFinal();
            }
        }
        return total;
    }

// Getters y  setters
    public List<Funcion> getFunciones() {
        return funciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

   
}
