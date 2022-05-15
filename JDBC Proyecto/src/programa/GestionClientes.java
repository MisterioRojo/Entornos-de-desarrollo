package programa;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import DBManager.DBManager;

/*
 * Por Alejandro Rodriguez Mena
 *
 * V1.3
 *
 * Ejercicio final de clase en el que accederemos a una base de datos usando java
 */
public class GestionClientes {

	public static Scanner ent = new Scanner(System.in);
	
    private static final String insertar = "./insertar.txt";
    
    private static final String actualizar = "./update.txt";
    
    private static final String borrar = "./delete.txt";

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

	public static void opcionMostrarClientes()
	{
		System.out.println("Listado de Clientes:");
		DBManager.printTablaClientes();
	}
	
	public static void opcionVolcarTabla()
	{
		System.out.println("¿Que tabla quieres volvar?: ");
		String tabla = ent.nextLine();;
		DBManager.volcarTabla(tabla);
	}

	public static void opcionMostrarNombres() throws SQLException
	{
		System.out.println("Listado de Clientes: \n");
		DBManager.printNombresClientes();
	}
	public static void opcionNuevoCliente()
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

	public static void opcionModificarCliente()
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

	public static void opcionEliminarCliente()
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
	
	public static void insertarCliente()
	{
		DBManager.insertarPorFichero(insertar);
	}
	
	public static void actualizarCliente()
	{
		DBManager.updatePorFichero(actualizar);
	}
	
	public static void  eliminarCliente()
	{
		DBManager.removePorFichero(borrar);
	}

	public static boolean menuPrincipal() throws SQLException
	{
		System.out.println("");
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Listar clientes");
		System.out.println("2. Nuevo cliente");
		System.out.println("3. Actualizar cliente");
		System.out.println("4. Modificar cliente");
		System.out.println("5. Eliminar cliente");
		System.out.println("6. Ver nombres de los clientes ordenados");
		System.out.println("7. Volcar datos de una tabla");
		System.out.println("7. Eliminar datos de una tabla");
		System.out.println("8. Salir");

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
//			opcionNuevoCliente();
			insertarCliente();
			return false;
		}
		case 3:
		{
			actualizarCliente();
			return false;
		}
		case 4:
		{
			opcionModificarCliente();
			return false;
		}
		case 5:
		{
			opcionEliminarCliente();
			return false;
		}
		case 6:
		{
			opcionMostrarNombres();
			return false;
		}
		case 7:
		{
			opcionVolcarTabla();
			return false;
		}
		case 8:
		{
			System.out.println("Has salido del menu.");
			return true;
		}
		case 9:
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

	public static void main(String[] args) throws SQLException
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