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

public class Partido{

	private String nomEquipo;
	private double minJugados;
	private int punConseguidos;
	private int numRebotes;
	private int numAsistencias;
	private int numTapones;
	private int valObtenido;
	
	//Constructor con parametros
	public Partido(String nomEquipo, double minJugados, int punConseguidos, int numRebotes, int numAsistencias,
			int numTapones, int valObtenido) 
	{
		this.nomEquipo = nomEquipo;
		this.minJugados = minJugados;
		this.punConseguidos = punConseguidos;
		this.numRebotes = numRebotes;
		this.numAsistencias = numAsistencias;
		this.numTapones = numTapones;
		this.valObtenido = valObtenido;
	}
	
	//Constructor vacio
	public Partido() 
	{
		this.nomEquipo = "";
		this.minJugados = 0;
		this.punConseguidos = 0;
		this.numRebotes = 0;
		this.numAsistencias = 0;
		this.numTapones = 0;
		this.valObtenido = 0;
	}
	
//	Los setters y getters
	public String getNomEquipo() {
		return nomEquipo;
	}

	public double getMinJugados() {
		return minJugados;
	}

	public int getPunConseguidos() {
		return punConseguidos;
	}

	public int getNumRebotes() {
		return numRebotes;
	}

	public int getNumAsistencias() {
		return numAsistencias;
	}

	public int getNumTapones() {
		return numTapones;
	}

	public double getValObtenido() {
		return valObtenido;
	}

	public void setNomEquipo(String nomEquipo) {
		this.nomEquipo = nomEquipo;
	}

	public void setMinJugados(double minJugados) {
		this.minJugados = minJugados;
	}

	public void setPunConseguidos(int punConseguidos) {
		this.punConseguidos = punConseguidos;
	}

	public void setNumRebotes(int numRebotes) {
		this.numRebotes = numRebotes;
	}

	public void setNumAsistencias(int numAsistencias) {
		this.numAsistencias = numAsistencias;
	}

	public void setNumTapones(int numTapones) {
		this.numTapones = numTapones;
	}

	public void setValObtenido(int valObtenido) {
		this.valObtenido = valObtenido;
	}
	
	//Funcion que devolvera un nombre de equipo de forma random.
	public static String nombresEquipos()
	{
		Random r = new Random();
		
	    String[] equipos = { "Real Madrid", "Barcelona", "Valencia", "Getafe", "Athletic Bilbao", "Espanyol", "Sevilla",
	            "Celta Vigo", "Atletico Madrid", "Villareal", "Levante", "Granada", "Real Valladolid", "Betis", "Deportivo",
	            "Real Sociedad", "Leganes", "Mallorca", "Osasuna", "Eibar", "Alaves", "Espanyol", "Girona", "Huesca",
	            "Rayo Vallecano", "Sporting Gijon", "Tenerife", "Valladolid", "Numancia", "Real Zaragoza", "Deportivo Alavés",
	            "Real Oviedo", "Cádiz", "Mirandés", "Cultural Leonesa", "Lorca FC", "Elche", "Fuenlabrada", "Córdoba",
	            "Extremadura UD", "AD Alcorcón", "Racing Santander", "Zaragoza", "Buriram United", "Bangkok United",
	            "Muang Thong United", "Chiangrai United", "Nakhon Ratchasima FC" };
	    
	    int e = (int)(Math.random()*r.nextInt(equipos.length));
	    
	    return equipos[e];
	}
	
	@Override
	public String toString()
	{
		return "Nombre del equipo rival: " + this.getNomEquipo() + "\nMinutos jugados: "+this.getMinJugados() + "\nPuntos conseguidos: "+this.getPunConseguidos() 
		+ "\nRebotes: "+this.getNumRebotes() + "\nAsistencias: "+this.getNumAsistencias()+"\nTapones: "+this.getNumTapones()+"\nValoracion obtenida: "+this.getValObtenido() + "\n";
	}
}
