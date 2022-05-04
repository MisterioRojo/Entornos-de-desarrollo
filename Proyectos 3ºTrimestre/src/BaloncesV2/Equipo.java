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

public class Equipo {
	
	protected static ArrayList<Jugador> ListaJugadores = new ArrayList<Jugador>();

	private int dorsal;

	//Constructor con datos
	public Equipo(int dorsal) 
	{
		this.dorsal = dorsal;
	}
	
	//Constructor vacio.
	public Equipo()
	{
		this.dorsal = 0;
	}

	//Setters y getters
	public static ArrayList<Jugador> getListaJugadores() {
		return ListaJugadores;
	}

	public static void setListaJugadores(ArrayList<Jugador> listaJugadores) {
		ListaJugadores = listaJugadores;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public void mayorParticipacion() //Calcula el jugador con mayor participacion. Devuelve el dorsal del jugador con mayor participacion
	{
		int NumPartidos = 0;
		int dorsal = 0;
		String nombre = "",apellido = "";
		for (int i = 0;i<ListaJugadores.size();i++) 
		{

			Jugador jugadorAux = ListaJugadores.get(i);
			if(ListaJugadores.get(i).getNumPartidos() >= NumPartidos)
			{
				NumPartidos = jugadorAux.getNumPartidos();
				dorsal = jugadorAux.getDorsal();
				nombre = jugadorAux.getNombre();
				apellido = jugadorAux.getApellidos();
			}
		}
		System.out.println("\nEl jugador con mayor participacion es: " + nombre + " " + apellido );
		System.out.println("Dorsal: " + dorsal);		
		System.out.println("Total de partidos jugados: " + NumPartidos + "\n");
		
		System.out.println(ListaJugadores.size());
	}
	
	public void mayorPuntuacion() //Calcula el jugador con mayor participacion. Devuelve el dorsal del jugador con mayor puntuacion
	{
		int puntuacion = 0;
		int dorsal = 0;
		String nombre = "",apellido = "";
		for (int i = 0;i<Equipo.ListaJugadores.size();i++) 
		{
			Jugador jugadorAux = Equipo.ListaJugadores.get(i);	
			if(jugadorAux.Estadistica.get(i).getPunConseguidos() >= puntuacion)
			{
				puntuacion = jugadorAux.Estadistica.get(i).getPunConseguidos();
				dorsal = jugadorAux.getDorsal();
				nombre = jugadorAux.getNombre();
				apellido = jugadorAux.getApellidos();
			}
		}
		System.out.println("\nEl jugador con mayor participacion es: " + nombre + " " + apellido );
		System.out.println("Dorsal: " + dorsal);		
		System.out.println("Puntuacion total de la temporada del jugador: " + puntuacion + "\n");
	}
	
	public void mayorValoracion() //Calcula el jugador con mayor participacion. Devuelve el dorsal del jugador con mayor puntuacion
	{
		double valoracion = 0;
		int dorsal = 0;
		String nombre = "",apellido = "";
		for (int i = 0;i<Equipo.ListaJugadores.size();i++) 
		{
			Jugador jugadorAux = Equipo.ListaJugadores.get(i);
			if(jugadorAux.Estadistica.get(i).getValObtenido() >= valoracion)
			{
				valoracion = jugadorAux.Estadistica.get(i).getValObtenido();
				dorsal = jugadorAux.getDorsal();
				nombre = jugadorAux.getNombre();
				apellido = jugadorAux.getApellidos();
			}
		}
		System.out.println("\nEl jugador con mayor participacion es: " + nombre + " " + apellido);
		System.out.println("Dorsal del jugador: " + dorsal);		
		System.out.println("Valoracion total de la temporada del jugador: " + (int)valoracion + "\n");
	}

	public Jugador buscarJugador(int dorsal) //Funcion que servira para comprobar segun el dorsal introducido los datos de los partidos jugados
	{
		for (int i = 0;i<ListaJugadores.size();i++) 
		{
			Jugador jugadorAux = ListaJugadores.get(i);
			if(jugadorAux.getDorsal() == dorsal)
			{
				return jugadorAux;
			}
		}
		return null;
	}
}
