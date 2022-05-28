package programa;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import DBManager.DBManager;

/*
 * Por Alejandro Rodriguez Mena
 *
 * V1.3.1
 *
 * Ejercicio final de clase en el que accederemos a una base de datos usando java
 */
public class GestionClientes {

	public static Scanner ent = new Scanner(System.in);
	
    private static final String insertar = "./insertar.txt";
    
    private static final String actualizar = "./update.txt";
    
    private static final String borrar = "./remove.txt";

    /**
     * Funcion que pide un numero entero y devuelve un numero entero. Controla excepciones
     * @param mensaje
     * @return
     */
	public static int pideInt(String mensaje)
	{
		while(true)
		{
			try
			{
				System.out.print(mensaje);
				int valor = ent.nextInt();
				return valor;
			}
			catch (InputMismatchException e)
			{
				System.out.println("No has introducido un número entero. Vuelve a intentarlo.");
			}
			finally
			{
				ent.nextLine();
			}
		}
	}

	/**
	 * Funcion que pide una cadena de String y devuelve una cadena de String. Controla las excepciones
	 * @param mensaje
	 * @return
	 */
	public static String pideLinea(String mensaje){

		while(true) {
			try {
				System.out.print(mensaje);
				String linea = ent.nextLine();
				return linea;
			} catch (Exception e) {
				System.out.println("No has introducido una cadena de texto. Vuelve a intentarlo.");
			}
			finally
			{
				ent.nextLine();
			}
		}
	}

	/**
	 * Metodo que llama a una funcion que muestra el contenido de la bbdd
	 */
	public static void opcionMostrarClientes()
	{
		System.out.println("Listado de Clientes:");
		DBManager.printTablaClientes();
	}
	
	/**
	 * Metodo que llama a una funcion que vuelca la bbdd en un fichero.
	 * @throws IOException 
	 */
	public static void opcionVolcarTabla() throws IOException
	{
		System.out.println("¿Que tabla quieres volcar?: ");
		String tabla = ent.nextLine();;
		DBManager.volcarTabla(tabla);
		System.out.println("Clientes volcados al fichero.");
	}

	/**
	 * MEtodo que llama a una funcion  que muestra los clientes de la bbdd
	 * @throws SQLException
	 */
	public static void opcionMostrarNombres() throws SQLException
	{
		System.out.println("Listado de Clientes: \n");
		DBManager.printNombresClientes();
	}
	
	/**
	 * Metodo que añade un cliente a la bbdd
	 */
	public static void opcionInsertarCliente()
	{
		System.out.println("Introduce los datos del nuevo cliente:");
		String nombre = pideLinea("Nombre: ");
		String direccion = pideLinea("Direccion: ");

		boolean res = DBManager.insertCliente(nombre, direccion);

		if (res)
		{
			System.out.println("Cliente registrado correctamente");
		}
		else
		{
			System.out.println("Error :(");
		}
	}
	
	/**
	 * Metodo que actualiza la informacion de un cliente en la bbdd
	 */
	public static void updateCliente()
	{
		int id = pideInt("Indica el id del cliente a modificar: ");

		// Comprobamos si existe el cliente
		if (!DBManager.existsCliente(id))
		{
			System.out.println("El cliente " + id + " no existe.");
			return;
		}

		// Mostramos datos del cliente a modificar
		DBManager.printCliente(id);

		// Solicitamos los nuevos datos
		String nombre = pideLinea("Nuevo nombre: ");
		String direccion = pideLinea("Nueva direccion: ");

		// Registramos los cambios
		boolean res = DBManager.updateCliente(id, nombre, direccion);

		if (res)
		{
			System.out.println("Cliente modificado correctamente");
		}
		else
		{
			System.out.println("Error :(");
		}
	}
	
	/**
	 * Metodo que elimina un cliente de la bbdd
	 */
	public static void deleteCliente()
	{
		int id = pideInt("Indica el id del cliente a eliminar: ");

		// Comprobamos si existe el cliente
		if (!DBManager.existsCliente(id))
		{
			System.out.println("El cliente " + id + " no existe.");
			return;
		}

		// Eliminamos el cliente
		boolean res = DBManager.deleteCliente(id);

		if (res)
		{
			System.out.println("Cliente eliminado correctamente");
		}
		else
		{
			System.out.println("Error :(");
		}
	}
	
	/**
	 * Metodo que llama a una funcion que añade clientes mediante un fichero ya diseñado
	 * @throws IOException 
	 */
	public static void opcionInsertarClienteFichero() throws IOException
	{
		DBManager.insertarPorFichero(insertar);
	}
	
	/**
	 * Metodo que llama a una funcion que actualiza clientes mediante un fichero ya diseñado
	 */
	public static void opcionActualizarClienteFichero()
	{
		DBManager.updatePorFichero(actualizar);
	}
	
	/**
	 * Metodo que llama a una funcion que elimina clientes mediante un fichero ya diseñado
	 */
	public static void opcionEliminarClienteFichero()
	{
		DBManager.removePorFichero(borrar);
	}

	/**
	 * Metodo que muestra el menu principal.
	 * @return
	 * @throws SQLException
	 * @throws IOException 
	 */
	public static boolean menuPrincipal() throws SQLException, IOException
	{
		System.out.println("");
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Listar clientes");
		System.out.println("2. Nuevo cliente");
		System.out.println("3. Actualizar cliente");
		System.out.println("4. Eliminar cliente");
		System.out.println("5. Nuevo cliente por fichero");
		System.out.println("6. Actualizar cliente por fichero");
		System.out.println("7. Eliminar cliente por fichero");
		System.out.println("8. Ver nombres de los clientes ordenados");
		System.out.println("9. Volcar datos de una tabla");
		System.out.println("10. Salir");

		int opcion = pideInt("Elige una opcion: ");
		switch (opcion)
		{
		case 1:
		{
			opcionMostrarClientes();
			return false;
		}
		case 2:
		{
			opcionInsertarCliente();
			return false;
		}
		case 3:
		{
			updateCliente();
			return false;
		}
		case 4:
		{
			opcionEliminarClienteFichero();
			return false;
		}
		case 5:
		{
			opcionInsertarClienteFichero();
			return false;
		}
		case 6:
		{
			opcionActualizarClienteFichero();
			return false;
		}
		case 7:
		{
			opcionEliminarClienteFichero();
			return false;
		}
		case 8:
		{
			opcionMostrarNombres();
			return false;
		}
		case 9:
		{
			opcionVolcarTabla();
			return false;
		}
		case 10:
		{
			System.out.println("Has salido del menu.");
			return true;
		}		
		default:
		{
			System.err.println("Opcion elegida incorrecta");
			return false;
		}
		}
	}

	/**
	 * Main
	 * @param args
	 * @throws SQLException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException
	{
		DBManager.loadDriver();
		DBManager.connect();

		boolean salir = false;
		do
		{
			salir = menuPrincipal();
		}
		while (!salir);

		DBManager.close();
	}
}