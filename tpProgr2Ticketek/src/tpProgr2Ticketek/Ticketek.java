package tpProgr2Ticketek;

import java.util.List;
import java.util.Map;

public class Ticketek {
	private Map <String, Usuario> usuarios;
	private List <Sede> sedes;
	private Map <String, Espectaculo> espectaculos;

	public void crearTicketek() {
		this.sedes = sedes;
		this.usuarios = usuarios;
		this.espectaculos = espectaculos;
	}
	public void agregarUsuario(String email, String nombre,String apellido, String contraseña) {
		
	}
	public void agregarSede(Sede sede) {
		
	}
	public void registrarEspectaculo(String nombreEspectaculo) {
		
	}
	public void venderEntrada(String email, String pass, int codEspectaculo, String sede, String funcion, String asientoSector) {
		
	}
	public void anularEntrada(String codEntrada, String contraseña) {
		
	}
	public void cambiarEntradaSede(String codEntrada, String nuevaSede) {
		
	}
	public double calcularValorEntrada(String codEspectaculo, String sede, String sector) {
		
	}
	public float consultarValor(String codEspectaculo, String sede, String sector) {
		
	}
	public List <Entrada> consultarEntradasUsuario(String mail) {
		
	}
	public List <Entrada> consultarEntradasFuturas(String mail) {
		
	}
	public double totalRecaudadoUNEspectaculo(String codEspectaculo) {
		
	}

}
