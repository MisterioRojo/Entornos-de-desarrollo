package BaloncesV2;

import java.util.ArrayList;

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

public class Jugador extends Persona {

	private int dorsal;
	private int NumPartidos = 0;
	protected ArrayList<Partido> ListaPartidos = new ArrayList<Partido>();
	protected ArrayList<Partido> Estadistica = new ArrayList<Partido>();

	//Constructor con datos
	public Jugador(String nombre, String apellidos, String DNI, String CuentaBancaria,int dorsal,int NumPartidos) 
	{
		super(nombre, apellidos, DNI, CuentaBancaria);
		this.dorsal = dorsal;
	}
	
	//Constructor vacio.
	public Jugador() 
	{
		this.dorsal = 0;
	}

	public int getDorsal() {
		return dorsal;
	}

	public int getNumPartidos() {
		return NumPartidos;
	}

	public void setDorsal(int dorsal) 
	{
		this.dorsal = dorsal;
	}
	
	public ArrayList<Partido> getListaPartidos() {
		return ListaPartidos;
	}

	public ArrayList<Partido> getEstadistica() {
		return Estadistica;
	}

	public void setListaPartidos(ArrayList<Partido> listaPartidos) {
		ListaPartidos = listaPartidos;
	}

	public void setEstadistica(ArrayList<Partido> estadistica) {
		Estadistica = estadistica;
	}
	
	public void setNumPartidos(int numPartidos) 
	{
		this.NumPartidos = numPartidos;
	}

	public void AgregarPartido(Partido p, boolean participar) //Funcion que agregara un partido jugado
	{
		if(participar)
		{
			NumPartidos++; //En caso de que el booleano participar sea true, se le sumara 1 a los partidos jugados de un jugador en concreto, no de todos.
			ListaPartidos.add(p);
			
		}
		else
		{
			ListaPartidos.add(p);
		}
	}
	
	public void AgregarEstadistica(Partido p)
	{
		Estadistica.add(p);
	}

	//Funcion que mostrara los partidos jugados y sus datos. Segun el dorsal introducido por otra funcion, veremos sus partidos jugados por el jugador.
	public void muestraPartidos() 
	{
		for(int i = 0; i < ListaPartidos.size(); i++)
		{
			Partido p = (Partido)ListaPartidos.get(i);
			
			if(p.getValObtenido() > -1)
			{
				System.out.println(p);
			}
		}
	}
	
	//Funcion que muestra las estadisticas de los jugadores. Segun el dorsal introducido por otra funcion, veremos sus estadisticas de todos los partidos jugados por el jugador
	public void mostrarEstadisticas() 
	{
		for(int i = 0; i < Estadistica.size(); i++)
		{
			Partido p1 = (Partido)ListaPartidos.get(i);
			Partido p2 = (Partido)Estadistica.get(i);
			
			if(p1.getValObtenido() > -1)
			{
				System.out.println("\nNombre del equipo rival: " + p1.getNomEquipo());
				System.out.println("Porcentaje de minutos jugados: "+((p1.getMinJugados()*100)/p2.getMinJugados()) + "%");
				System.out.println("Porcentaje de puntos conseguidos: "+((p1.getPunConseguidos()*100)/p2.getPunConseguidos()) + "%");
				System.out.println("Porcentaje de rebotes: "+((p1.getNumRebotes()*100)/p2.getNumRebotes()) + "%");
				System.out.println("Porcentaje de asistencias: "+((p1.getNumAsistencias()*100)/p2.getNumAsistencias()) + "%");
				System.out.println("Porcentaje de tapones: "+((p1.getNumTapones()*100)/p2.getNumTapones()) + "%");
				System.out.println("Porcentaje de valoracion obtenida: "+((p1.getValObtenido()*100)/p2.getValObtenido()) + "%");
			}
		}
	}

	@Override
	public String toString ()
	{
		return "Nombre del jugador: "+this.getNombre() + "\nApellidos: "+this.getApellidos()+"\nDNI: "
				+this.getDNI()+"\nDorsal: "+this.getDorsal() + "\nHa jugado: "+this.getNumPartidos();
	}
}
