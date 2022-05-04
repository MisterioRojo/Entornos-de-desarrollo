package BaloncesV2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
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

public class Programa {
	
	public static Scanner ent = new Scanner(System.in);

	private static final int PARTIDOS_TEMPORADA = 15; //Numero de temporadas
	
	public static int dorsal = 0; //Valor del dorsal. Esta puesto en static fuera del main, ya que si esta dentro del metodo, no contara mas de 10, de esta forma puede crear tantos dorsales como se quiera sin repetirse.
	
	//Diferentes arraylist
	public static ArrayList<Socio> ListaSocios = new ArrayList<Socio>(); //Contendra los socios que creemos
	
	public static ArrayList<Trabajador> ListaTrabajadores = new ArrayList<Trabajador>(); //Contendra los trabajadores que creemos.

	private static String temporada []= new String[PARTIDOS_TEMPORADA];

	public static void nuevoSocio() //Metodo para crear socios y jugadores
	{
		String nombre,apellido,DNI,IBAN;
		int NumPartidos = 0;
		double cuota;
		for (int i = 0; i < 10; i++) 
		{
			//Se hacen llamadas a la clase Persona y al metodo necesario para crear, tanto nombres, apellidos y diversos datos de forma aleatoria.
			System.out.print("Nombre: ");
			System.out.println(nombre = Persona.generarNombre());

			System.out.print("Apellido: ");
			System.out.println(apellido = Persona.generarApellido());

			System.out.print("DNI: ");
			System.out.println(DNI = Persona.generaDNI());

			System.out.print("Cuenta bancaria: ");
			System.out.println(IBAN = Persona.generaIBAN());

			System.out.print("Cuota de socio: ");
			cuota = Math.random()*(700 )+400;
			System.out.println(Math.round(cuota*100.0)/100.0 + "€"); 

			System.out.print("Dorsal como jugador: ");
			System.out.println((dorsal++)+1 + "\n");

			Equipo.ListaJugadores.add((new Jugador(nombre,apellido,DNI,IBAN,dorsal,NumPartidos))); //Se guardara el objeto en el arrayList en la clase Jugador

			ListaSocios.add(new Socio(nombre,apellido,DNI,IBAN,cuota)); //Se guarda el socio en el arraylist ListaSocios
		}
	}
	
	public static void nuevoTrabajador() //Metodo para crear trabajadores
	{
		String nombre,apellido,DNI,IBAN,profesion;
		double remuneracion;

		//Se hacen llamadas a la clase Persona y al metodo necesario para crear, tanto nombres, apellidos y diversos datos de forma aleatoria.
		System.out.print("\nNombre: ");
		System.out.println(nombre = Persona.generarNombre()); 
		
		System.out.print("Apellido: ");
		System.out.println(apellido = Persona.generarApellido());

		System.out.print("DNI: ");
		System.out.println(DNI = Persona.generaDNI());

		System.out.print("Cuenta bancaria: ");
		System.out.println(IBAN = Persona.generaIBAN());

		System.out.print("Profesion: ");
		System.out.println(profesion = Socio.generaProfesion());

		System.out.print("Remuneracion: ");
		remuneracion = Math.random()*(1300)+100;
		System.out.println(Math.round(remuneracion*100.0)/100.0 + "€\n");
		
		ListaTrabajadores.add(new Trabajador(nombre,apellido,DNI,IBAN,profesion,remuneracion)); //Se guarda el trabajdor en el arraylist ListaTrabajadores
	}


	public static void nuevoPartido()  //Metodo que permitira jugar la temporada de 15 partidos
	{
		int minJugados, puntosOb, rebotes, asistencias, tapones = 0, valoracionOb;
		
		if (Equipo.ListaJugadores.size() == 0)
		{
			System.err.println("No hay jugadores para jugar partidos");
		}
		else if(Equipo.ListaJugadores.size() < 10)
		{
			System.err.println("Se necesitan minimo 10 jugadores. Actualmente hay: " + Equipo.ListaJugadores.size() + " jugadores.");
		}
		else
		{
			for (int i = 0;i<PARTIDOS_TEMPORADA;i++) 
			{
				int minJugadosTotal = 0,puntosObTotal = 0,rebotesTotal = 0,
						asistenciasTotal = 0,taponesTotal = 0,valoracionObTotal = 0;

				System.out.println("\nPartido "+(i+1));
				
				String nomEquipo = Partido.nombresEquipos();
				
				temporada[i] = nomEquipo;
				
				System.out.println("Nombre del equipo rival: " + nomEquipo);

				for (int j = 0; j < Equipo.ListaJugadores.size(); j++) 
				{
					//booleano que segun el numero, se encontrara en un estado u otro
					boolean participacion;
					Random random = new Random();
					participacion = random.nextBoolean();
					
					Jugador jugadorAux = Equipo.ListaJugadores.get(j);
					
					//Se generan datos de forma aleatoria por cada partido jugado por cada jugador.
					minJugados = (int)(Math.random()*60+1);

					puntosOb = (int)(Math.random()*45+1);

					rebotes = (int)(Math.random()*15+1);

					asistencias = (int)(Math.random()*10+1);

					tapones = (int)(Math.random()*10+1);
					
					valoracionOb = (int)(Math.random()*50+1);
					
					//

					if(participacion) //Si participaciones es true, se guardan los valores generados de forma aleatoria.
					{
						jugadorAux.AgregarPartido(new Partido(nomEquipo, minJugados, puntosOb, rebotes, asistencias, 
								tapones, valoracionOb),participacion); //Guarda el objeto en el arraylist ListaPartidos de la clase Jugador
						
						//Suma total de los datos de los partidos de cada jugadores en cada partido.
						minJugadosTotal += minJugados;
						
						puntosObTotal += puntosOb;
						
						rebotesTotal += rebotes;
						
						asistenciasTotal += asistencias;
						
						taponesTotal += tapones;
						
						valoracionObTotal += valoracionOb;
					}
					
					else //En caso de ser false la participacion. Se guardan datos, uno de ellos en negativo para hacer verificacion en las respectivas funciones de la clase Jugador 
					{
						jugadorAux.AgregarPartido(new Partido("", 0, 0, 0, 0, 0, -1),participacion); //Guarda el objeto en el arraylist ListaPartidos de la clase Jugador
					}
				}
				for (int k = 0; k < Equipo.ListaJugadores.size(); k++) 
				{
					Jugador jugadorAux = Equipo.ListaJugadores.get(k);
					//Guarda el objeto en el arraylist Estadistica de la clase Jugador
					jugadorAux.AgregarEstadistica(new Partido(nomEquipo,minJugadosTotal,puntosObTotal,rebotesTotal,asistenciasTotal,taponesTotal,valoracionObTotal)); 
				}	
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Equipo equipo = new Equipo();
		Jugador jugador = null;
		int op;
		
		do //Bucle que continuara hasta pulsar el numero 11
		{
			System.out.print("1 - Captar socios/Jugadores. \n2 - Contratar trabajadores. "
					+ "\n3 - Ver datos de los jugadores \n4 - Ver datos de los socios \n5 - Ver datos de los trabajadores "
					+ "\n6 - jugar partidos \n7 - Ver resultados de los partidos.\n8 - Mayor participacion \n9 - Mayor puntuacion \n10 - Mayor valoracion \n11 - Salir."
					+ "\nEscoge una opcion: ");
			op = ent.nextInt();
			ent.nextLine();
			
			System.out.println();
			switch(op)
			{
			case 1:
			{
				nuevoSocio(); //Se llamara al metodo que creara socios y jugadores. Se crean 10 en total
				break;
			}
			case 2:
			{
				nuevoTrabajador(); //Se llamara al metodo que creara trabajadores.
				break;
			}
			case 3: //Mostrara los datos de los jugadores.
			{
				if (Equipo.ListaJugadores.size() == 0)
				{
					System.err.println("ERROR. No se pueden mostrar los jugadores. No hay jugadores");
				}
				else
				{
					System.out.println("\nLista de jugadores. \n*************");
					for (int i = 0; i < Equipo.ListaJugadores.size(); i++) 
					{
						System.out.println("Nombre: " + Equipo.ListaJugadores.get(i).getNombre()); ;
						System.out.println("Apellido: " + Equipo.ListaJugadores.get(i).getApellidos());
						System.out.println("Dorsal: " + Equipo.ListaJugadores.get(i).getDorsal());
						System.out.println("Partidos jugados: " + Equipo.ListaJugadores.get(i).getNumPartidos() + "\n");
					}
				}
				break;
			}
			case 4:
			{
				if (ListaSocios.size() == 0)
				{
					System.err.println("ERROR. No se pueden mostrar los socios. No hay socios");
				}
				else
				{
					System.out.println("\nLista de socios. \n*************");
					for (Socio s:ListaSocios) 
					{
						System.out.println(s.toString() + "\n"); //Mostrara los datos de los socios.
					}
				}
				break;
			}
			case 5:
			{
				if (ListaTrabajadores.size() == 0)
				{
					System.err.println("ERROR. No se pueden mostrar los trabajadores. No hay trabajadores");
				}
				else
				{
					System.out.println("\nLista de socios. \n*************");
					for (Trabajador t:ListaTrabajadores) 
					{
						System.out.println(t.toString()); //Mostrara los datos de los trabajadores.
					}
				}

				break;
			}
			case 6:
			{
				nuevoPartido(); //Llamada al metodo para crear partidos jugados, se jugaran 15 en total.
				
				//Bucle que usamos para dotar al objeto "jugador" del tamaño del ArrayList.
				//Al no ser un arraylist estatico, al hacer una llamada a metodo sin pasar por bucle, el resultado del ArrayList siempre era 0.
				for (int j = 0; j < Equipo.ListaJugadores.size(); j++) 
				{
					jugador = Equipo.ListaJugadores.get(j);
				}
				break;
			}
			case 7:
			{
				int dorsal;
				
				if(Equipo.ListaJugadores.size() == 0)
				{
					System.err.println("ERROR. No hay jugadores");
				}
				else if(jugador.getListaPartidos().size() == 0)
				{
					System.err.println("ERROR. No se puede mostrar partidos ni estadisticas. No se han jugador partidos.");
				}
				else
				{
					System.out.println("\nLista de jugadores. \n*************");
					for (int i = 0; i < Equipo.ListaJugadores.size(); i++) 
					{
						System.out.println("Nombre: " + Equipo.ListaJugadores.get(i).getNombre()); ;
						System.out.println("Apellido: " + Equipo.ListaJugadores.get(i).getApellidos());
						System.out.println("Dorsal: " + Equipo.ListaJugadores.get(i).getDorsal());
						System.out.println("Partidos jugados: " + Equipo.ListaJugadores.get(i).getNumPartidos() + "\n");
					}

					System.out.println("Dorsal de jugador quieres saber las estadisticas: ");
					dorsal = ent.nextInt();

					Jugador jugadorAux = equipo.buscarJugador(dorsal); //Llamada a fuuncion que devolvera un objeto tipo Jugador, donde mostrara los datos cuyo dorsal sea el mismo que en el objeto

					System.out.println("Estadisticas: ");
					jugadorAux.mostrarEstadisticas(); //Llamada a funcion que muestran las estadisticas del jugador escogido.

					System.out.println("\nPartidos: ");
					jugadorAux.muestraPartidos(); //Llamada a funcion que muestran los partidos del jugador escogido
				}
				break;
			}
			case 8:
			{
				if(Equipo.ListaJugadores.size() == 0)
				{
					System.err.println("ERROR. No hay jugadores");
				}
				else if(jugador.getListaPartidos().size() == 0)
				{
					System.err.println("ERROR. No se puede mostrar mayor participacion. No se han jugador partidos.");
				}
				else
				{
					System.out.println("Mayor participacion: ");
					equipo.mayorParticipacion(); //Llamada a funcion que muestra cual es el jugador con mayor participacion
				}
				break;
			}
			case 9:
			{
				if(Equipo.ListaJugadores.size() == 0)
				{
					System.err.println("ERROR. No hay jugadores");
				}
				else if(jugador.getListaPartidos().size() == 0)
				{
					System.err.println("ERROR. No se puede mostrar mayor puntuacion. No se han jugador partidos.");
				}
				else
				{
					System.out.println("Mayor puntuacion: ");
					equipo.mayorPuntuacion(); //Llamada a funcion que muestra cual es el jugador con mayor puntuacion
				}
				break;
			}

			case 10:
			{
				if(Equipo.ListaJugadores.size() == 0)
				{
					System.err.println("ERROR. No hay jugadores");
				}
				else if(jugador.getListaPartidos().size() == 0)
				{
					System.err.println("ERROR. No se puede mostrar mayor valoracion. No se han jugado partidos.");
				}
				else
				{
					System.out.println("Mayor valoracion: ");
					equipo.mayorValoracion(); //Llamada a funcion que muestra cual es el jugador con mayor valoracion
				}
				break;
			}
			case 11: //Sales del menu
			{
				System.out.println("Has salido del menu.");
				break;
			}
			default:
			{
				System.err.println("ERROR. La opcion: " + op + " no existe");
				break;
			}
			}
		}
		while(op != 11);
	}
}