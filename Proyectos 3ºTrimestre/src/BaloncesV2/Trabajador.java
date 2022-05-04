package BaloncesV2;

/*
 * Por Alejandro Rodriguez Mena
 * 
 * Baloncesto V2
 * 
Se requiere una información estadística que obtenga la siguiente información:
1) El dorsal (o nombre) del jugador que más puntos ha conseguido durante la
temporada.
2) El dorsal (o nombre) del jugador que más partidos ha sido convocado
durante la temporada.
3) Para un determinado jugador, relación de partidos convocados y valoración
obtenida en cada partido.
4) Para un determinado jugador, cuál fue el partido con más valoración
obtenida.
5) (Opcional): para cada jugador, mostrar el porcentaje de su participación total
de la temporada en cada uno de los apartados registrados.
 */

public class Trabajador extends Persona{

	private final double remuneracion;
	private String profesion;
	
	public Trabajador(String nombre, String apellidos, String DNI, String CuentaBancaria, String profesion,
			double remuneracion) 
	{
		super(nombre, apellidos, DNI, CuentaBancaria);
		this.remuneracion = remuneracion;
		this.profesion = profesion;
	}
	
	public Trabajador() 
	{
		this.remuneracion = 0;
		this.profesion = "";
	}

	//Los getters y setters
	public String getProfesion() {
		return profesion;
	}

	public double getRemuneracion() {
		return remuneracion;
	}
	
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	@Override
	public String toString ()
	{
		return "\nNombre: "+this.nombre+"\nApellidos: "+this.apellidos+"\nDNI: "+this.DNI+"\nProfesion: "+this.profesion
				+ "\nRemuneracion: "+ Math.round(this.remuneracion*100.0)/100.0 + "€" +"\nCuentaBancaria: "+this.CuentaBancaria + "\n";
	}
}
