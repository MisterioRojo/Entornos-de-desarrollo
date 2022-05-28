package programa;
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
	
	
	public static void opcionInsertarCliente()
	{
		DBManager.insertCliente(insertar);
	}
	
	public static void updateCliente()
	{
		DBManager.updatePorFichero(actualizar);
	}
	
	public static void deleteCliente()
	{
		DBManager.removePorFichero(borrar);
	}
	
	public static void opcionInsertarClienteFichero()
	{
		DBManager.insertarPorFichero(insertar);
	}
	
	public static void opcionActualizarClienteFichero()
	{
		DBManager.updatePorFichero(actualizar);
	}
	
	public static void opcionEliminarClienteFichero()
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