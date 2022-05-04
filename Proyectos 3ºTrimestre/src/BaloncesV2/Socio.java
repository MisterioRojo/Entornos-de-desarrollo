package BaloncesV2;



import java.util.Random;

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

public class Socio extends Persona{

	private final double cuota;

	public Socio(String nombre, String apellidos, String DNI, String CuentaBancaria, double cuota) 
	{
		super(nombre, apellidos, DNI, CuentaBancaria);
		this.cuota = cuota;
	}
	
	public Socio() 
	{
		this.cuota = 0;
	}

	//Los getters y setters
	public double getCuota() {
		return cuota;
	}
	
	public static String generaProfesion()
	{
		String profesiones[] = {"Profesiones." ,"Entrenador","administrativo","comercial"};
		
		Random r = new Random();
		
		int p = (int)(Math.random()*r.nextInt(profesiones.length));
		
		return profesiones[p];
	}
	
	@Override
	public String toString()
	{
		return "Nombre: "+this.nombre+"\nApellidos: "+this.apellidos+"\nDNI: "+this.DNI
				+ "\nCuota: "+Math.round(this.cuota*100.0)/100.0 + "€" +"\nCuentaBancaria: "+this.CuentaBancaria;
	}
}
