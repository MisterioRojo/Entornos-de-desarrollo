package BaloncesV2;



import java.util.Random;

/*
 * Por Alejandro Rodriguez Mena
 * 
 * Baloncesto V2
 * 
Se requiere una informaci�n estad�stica que obtenga la siguiente informaci�n:
1) El dorsal (o nombre) del jugador que m�s puntos ha conseguido durante la
temporada.
2) El dorsal (o nombre) del jugador que m�s partidos ha sido convocado
durante la temporada.
3) Para un determinado jugador, relaci�n de partidos convocados y valoraci�n
obtenida en cada partido.
4) Para un determinado jugador, cu�l fue el partido con m�s valoraci�n
obtenida.
5) (Opcional): para cada jugador, mostrar el porcentaje de su participaci�n total
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
				+ "\nCuota: "+Math.round(this.cuota*100.0)/100.0 + "�" +"\nCuentaBancaria: "+this.CuentaBancaria;
	}
}
