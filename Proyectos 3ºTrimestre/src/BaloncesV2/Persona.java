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

public abstract class Persona {
	
	protected String nombre;
	protected String apellidos;
	protected final String DNI ;
	protected final String CuentaBancaria;

	//Constructor con parametros
	public Persona(String nombre, String apellidos, String DNI,String CuentaBancaria) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.DNI = DNI;
		this.CuentaBancaria = CuentaBancaria;
	}
	
	//Constructor vacio.
	public Persona()
	{
		this.nombre = "";
		this.apellidos = "";
		this.DNI = "";
		this.CuentaBancaria = "";
	}
	
	//Los getters y setters
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDNI() {
		return DNI;
	}
	
	public String getCuentaBancaria() {
		return CuentaBancaria;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public static String generaDNI() //Genera un DNI y lo devulve
	{
		int NumeroDNI =  (int)(Math.random()*(99999999)+10);
		String NDNI=String.valueOf(NumeroDNI); 
		int numero = NumeroDNI % 23;

		char letrasDNI[] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};

		return NDNI + letrasDNI[numero];
	}

	public static String generaIBAN() //Genera un iban y lo devulve
	{
		int numero1 = (int)(Math.random()*9999+1);
		int numero2 = (int)(Math.random()*9999+1);
		int numero3 = (int)(Math.random()*9999+1);
		int numero4 = (int)(Math.random()*9999+1);
		int numero5 = (int)(Math.random()*9999+1);
		
		return "ES-" + numero1 + "-" + numero2+ "-" + numero3+ "-" + numero4+ "-" + numero5;
	}
	
	public static String generarNombre() //Genera un nombre de forma aleatoria y lo devuelve
	{
		Random r = new Random();
		
		String nombres []= {"Juan", "Pedro", "Pablo", "Ernesto", 
				"Ariel", "Carlos", "Luis", "Oscar", "Alicia", "Maria", 
				"Brenda", "Cindy", "Diana", "Eva", "Fatima", "Gabriela", 
				"Isabel", "Julia", "Karen", "Laura", "Monica", "Nancy", 
				"Olga", "Patricia", "Sofia"};
		
		int n = (int)(Math.random()*r.nextInt(nombres.length));
		
		return nombres[n];
	}
	
	public static String generarApellido() //Genera un apellido de forma aleatoria y lo devuelve
	{
		Random r = new Random();
		
		String apellidos[] = {"Aguilar","Perez","Gonzalez","Sanchez","Lopez",
				"Ramirez","Torres","Gomez","Diaz","Castro","Ruiz","Alvarez","Morales"
				,"Jimenez","Moreno","Vazquez","Flores","Ordaz","Chavez","Herrera",
				"Carrillo","Santos","Gutierrez"};
		
		int m = (int)(Math.random()*r.nextInt(apellidos.length));
		
		return apellidos[m];
	}
	
	public abstract String toString();
}
