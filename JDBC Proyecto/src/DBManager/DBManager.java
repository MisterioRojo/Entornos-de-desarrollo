package DBManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/*
 * Por Alejandro Rodriguez Mena
 *
 * V1.3
 *
 * Ejercicio final de clase en el que accederemos a una base de datos usando java
 */


public class DBManager {

	// Conexión a la base de datos
	private static Connection conn = null;

	// Configuración de la conexión a la base de datos
	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "tienda";
	private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "1234";
	private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
	private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

	// Configuración de la tabla Clientes
	private static final String DB_CLI = "clientes";
	private static final String DB_CLI_SELECT = "SELECT * FROM " + DB_CLI;
	private static final String DB_CLI_ID = "id";
	private static final String DB_CLI_NOM = "nombre";
	private static final String DB_CLI_DIR = "direccion";

	//////////////////////////////////////////////////
	// MÉTODOS DE CONEXIÓN A LA BASE DE DATOS
	//////////////////////////////////////////////////

	/**
	 * Intenta cargar el JDBC driver.
	 * @return true si pudo cargar el driver, false en caso contrario
	 */
	public static boolean loadDriver()
	{
		try
		{
			System.out.print("Cargando Driver...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("OK!");
			return true;
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
			return false;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Intenta conectar con la base de datos.
	 *
	 * @return true si pudo conectarse, false en caso contrario
	 */
	public static boolean connect()
	{
		try
		{
			System.out.print("Conectando a la base de datos...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("OK!");
			return true;
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Comprueba la conexión y muestra su estado por pantalla
	 *
	 * @return true si la conexión existe y es válida, false en caso contrario
	 */
	public static boolean isConnected()
	{
		// Comprobamos estado de la conexión
		try
		{
			if (conn != null && conn.isValid(0))
			{
				System.out.println(DB_MSQ_CONN_OK);
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException ex)
		{
			System.out.println(DB_MSQ_CONN_NO);
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public static void close()
	{
		try
		{
			System.out.print("Cerrando la conexión...");
			conn.close();
			System.out.println("OK!");
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	//////////////////////////////////////////////////
	// MÉTODOS DE TABLA CLIENTES
	//////////////////////////////////////////////////


	// Devuelve
	// Los argumentos indican el tipo de ResultSet deseado
	/**
	 * Obtiene toda la tabla clientes de la base de datos
	 * @param resultSetType Tipo de ResultSet
	 * @param resultSetConcurrency Concurrencia del ResultSet
	 * @return ResultSet (del tipo indicado) con la tabla, null en caso de error
	 */
	public static ResultSet getTablaClientes(int resultSetType, int resultSetConcurrency)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement(DB_CLI_SELECT, resultSetType, resultSetConcurrency);
			ResultSet rs = stmt.executeQuery();
			//stmt.close();
			return rs;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Obtiene toda la tabla clientes de la base de datos
	 *
	 * @return ResultSet (por defecto) con la tabla, null en caso de error
	 */
	public static ResultSet getTablaClientes()
	{
		return getTablaClientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}

	/**
	 * Imprime por pantalla el contenido de la tabla clientes
	 */
	public static void printTablaClientes() {
		try
		{
			ResultSet rs = getTablaClientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			while (rs.next())
			{
				int id = rs.getInt(DB_CLI_ID);
				String n = rs.getString(DB_CLI_NOM);
				String d = rs.getString(DB_CLI_DIR);
				System.out.println(id + "\t" + n + "\t" + d);
			}
			rs.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 *Devuelve una lista de nombres de los clientes de la base de datos de forma alfabetica.
	 * @throws SQLException
	 */
	public static void printNombresClientes() throws SQLException
	{
		String sentenciaSQL = "{cal llamada()}";
		CallableStatement stmt = conn.prepareCall(sentenciaSQL);
		ResultSet rs = stmt.executeQuery();

		while (rs.next())
		{
			System.out.println("Nombre: "+rs.getString(1));
		}

	}

	//////////////////////////////////////////////////
	// MÉTODOS DE UN SOLO CLIENTE
	//////////////////////////////////////////////////

	/**
	 * Solicita a la BD el cliente con id indicado
	 * @param id id del cliente
	 * @return ResultSet con el resultado de la consulta, null en caso de error
	 */
	public static ResultSet getCliente(int id) {
		try
		{
			// Realizamos la consulta SQL
			String sql = DB_CLI_SELECT + " WHERE " + DB_CLI_ID + "= ?;";
			PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id+"");

			ResultSet rs = stmt.executeQuery();
			if (!rs.first())
			{
				return null;
			}

			// Todo bien, devolvemos el cliente
			return rs;

		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Comprueba si en la BD existe el cliente con id indicado
	 *
	 * @param id del cliente
	 * @return verdadero si existe, false en caso contrario
	 */
	public static boolean existsCliente(int id)
	{
		try {
			// Obtenemos el cliente
			ResultSet rs = getCliente(id);

			// Si rs es null, se ha producido un error
			if (rs == null)
			{
				return false;
			}

			// Si no existe primer registro
			if (!rs.first())
			{
				rs.close();
				return false;
			}

			// Todo bien, existe el cliente
			rs.close();
			return true;

		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Imprime los datos del cliente con id indicado
	 *
	 * @param id id del cliente
	 */
	public static void printCliente(int id)
	{
		try {
			// Obtenemos el cliente
			ResultSet rs = getCliente(id);
			if (rs == null || !rs.first())
			{
				System.out.println("Cliente " + id + " NO EXISTE");
				return;
			}

			// Imprimimos su información por pantalla
			int cid = rs.getInt(DB_CLI_ID);
			String nombre = rs.getString(DB_CLI_NOM);
			String direccion = rs.getString(DB_CLI_DIR);
			System.out.println("Cliente " + cid + "\t" + nombre + "\t" + direccion);

		}
		catch (SQLException ex)
		{
			System.out.println("Error al solicitar cliente " + id);
			ex.printStackTrace();
		}
	}

	/**
	 * Solicita a la BD insertar un nuevo registro cliente
	 *
	 * @param nombre nombre del cliente
	 * @param direccion dirección del cliente
	 * @return verdadero si pudo insertarlo, false en caso contrario
	 */
	public static boolean insertCliente(String nombre, String direccion) {
		try
		{
			// Obtenemos la tabla clientes
			System.out.print("Insertando cliente " + nombre + "...");
			ResultSet rs = getTablaClientes(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);

			// Insertamos el nuevo registro
			rs.moveToInsertRow();
			rs.updateString(DB_CLI_NOM, nombre);
			rs.updateString(DB_CLI_DIR, direccion);
			rs.insertRow();

			// Todo bien, cerramos ResultSet y devolvemos true
			rs.close();
			System.out.println("OK!");
			return true;

		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * Solicita a la BD modificar los datos de un cliente
	 *
	 * @param id id del cliente a modificar
	 * @param nombre nuevo nombre del cliente
	 * @param direccion nueva dirección del cliente
	 * @return verdadero si pudo modificarlo, false en caso contrario
	 */
	public static boolean updateCliente(int id, String nuevoNombre, String nuevaDireccion) {
		try
		{
			// Obtenemos el cliente
			System.out.print("Actualizando cliente " + id + "... ");
			ResultSet rs = getCliente(id);

			// Si no existe el Resultset
			if (rs == null)
			{
				System.out.println("Error. ResultSet null.");
				return false;
			}

			// Si tiene un primer registro, lo eliminamos
			if (rs.first())
			{
				rs.updateString(DB_CLI_NOM, nuevoNombre);
				rs.updateString(DB_CLI_DIR, nuevaDireccion);
				rs.updateRow();
				rs.close();
				System.out.println("OK!");
				return true;
			}
			else
			{
				System.out.println("ERROR. ResultSet vacío.");
				return false;
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * Solicita a la BD eliminar un cliente
	 *
	 * @param id id del cliente a eliminar
	 * @return verdadero si pudo eliminarlo, false en caso contrario
	 */
	public static boolean deleteCliente(int id)
	{
		try {
			System.out.print("Eliminando cliente " + id + "... ");

			// Obtenemos el cliente
			ResultSet rs = getCliente(id);

			// Si no existe el Resultset
			if (rs == null)
			{
				System.out.println("ERROR. ResultSet null.");
				return false;
			}

			// Si existe y tiene primer registro, lo eliminamos
			if (rs.first())
			{
				rs.deleteRow();
				rs.close();
				System.out.println("OK!");
				return true;
			}
			else
			{
				System.out.println("ERROR. ResultSet vacío.");
				return false;
			}

		}

		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 *
	 * @param tabla_
	 */
	public static void volcarTabla(String tabla_)
	{
		try
		{
			FileWriter fw = new FileWriter(tabla_ + ".txt");
			String sentenciaSQL = "select * from ";

			Statement statement = conn.createStatement();
			ResultSet tablaRS = statement.executeQuery(sentenciaSQL + tabla_);

			ResultSetMetaData tablaMD = tablaRS.getMetaData();

			// Nombre BD y Nombre Tabla
			fw.write(DB_NAME + " " + tablaMD.getTableName(1) + "\n");

			// Cabecera
			int cantidadCol = tablaMD.getColumnCount();

			for(int i = 1; i <= cantidadCol; i++)
			{
				String nombreCol = tablaMD.getColumnName(i);
				fw.write(i < cantidadCol ? nombreCol+",":nombreCol+"\n");
			}

			// Datos
			while(tablaRS.next())
			{
				String registro = "";
				for (int i = 1; i <= cantidadCol; i++)
				{
					if(tablaMD.getColumnType(i) == Types.INTEGER)
					{
						registro += i < cantidadCol ? tablaRS.getInt(i) + ",": tablaRS.getInt(i) + "\n";
					}
					else
					{
						registro += i < cantidadCol ? tablaRS.getString(i) + ",": tablaRS.getString(i) + "\n";
					}
				}
				fw.write(registro);
			}

			statement.close();
			tablaRS.close();
			fw.close();
		}
		catch (Exception ex)
		{
			System.err.println("ERROR. No se ha podido volcar los datos.");
			System.out.println(ex.getMessage());
		}

	}

	// METODO INSERTAR
	/**
	 *
	 * @param ruta
	 */
	public static void insertarPorFichero(String ruta)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(ruta));

			String linea = "";
			String[] infoPrincipal = new String[3];

			// BD, Nombre de tabla y Cabecera
			for (int nl = 0; nl <= 2; nl++)
			{
				linea = br.readLine();
				infoPrincipal[nl] = linea;
			}

			String bd = infoPrincipal[0];
			String nombre = infoPrincipal[1];
			String columnas = infoPrincipal[2];

			Statement statement = conn.createStatement();
			ResultSet tablaRS = statement.executeQuery("select " + columnas + " from " + nombre + " limit 1;");
			ResultSetMetaData tablaMD = tablaRS.getMetaData();

			// Busco los tipos de cada columna
			int cantidadColumnas = tablaMD.getColumnCount();
			String[] tipos = new String[cantidadColumnas];

			for (int i = 1; i <= cantidadColumnas; i++)
			{
				tipos[i - 1] = tablaMD.getColumnTypeName(i);
			}

			// Separo cada registro por columna y verifico con el array que dato es
			// y lo agrego a los registros totales
			String registros = "\n";

			while ((linea = br.readLine()) != null)
			{
				String[] datos = linea.split(",");
				String registro = "";

				for (int i = 0; i < cantidadColumnas; i++)
				{
					registro += tipos[i] == "VARCHAR" ? "'" + datos[i] + "'," : datos[i] + ",";
				}

				registro = registro.substring(0, registro.length() - 1);
				registros += "(" + registro + "),\n";
			}

			registros = registros.substring(0, registros.length() - 2);

			String stringSQL = "insert into " + bd + "." + nombre + "\n(" + columnas + ") \nvalues " + registros;
			statement.executeUpdate(stringSQL);

			statement.close();
			tablaRS.close();
			br.close();
		}
		catch (Exception ex)
		{
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ruta
	 */
	public static void updatePorFichero(String ruta)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(ruta));

			String linea = "";
			String[] infoPrincipal = new String[3];

			// BD, Nombre de tabla y Cabecera
			for (int nl = 0; nl <= 2; nl++)
			{
				linea = br.readLine();
				infoPrincipal[nl] = linea;
			}

			String bd = infoPrincipal[0];
			String nombre = infoPrincipal[1];
			String columnas = infoPrincipal[2];

			Statement statement = conn.createStatement();
			ResultSet tablaRS = statement.executeQuery("select " + columnas + " from " + nombre + " limit 1");
			ResultSetMetaData tablaMD = tablaRS.getMetaData();

			// Busco los tipos de cada columna
			int cantidadColumnas = tablaMD.getColumnCount();
			String[] tipos = new String[cantidadColumnas];

			for (int i = 1; i <= cantidadColumnas; i++)
			{
				tipos[i - 1] = tablaMD.getColumnTypeName(i);
			}

			// Busco las primary keys
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSet rspk = dmd.getPrimaryKeys(null, null, nombre);

			String primaryKeys = "";
			while (rspk.next())
			{
				primaryKeys = rspk.getString("COLUMN_NAME");
			}

			// Formulo la sentencia y envio el update
			while ((linea = br.readLine()) != null)
			{
				String[] datos = linea.split(",");
				String[] columnas_ = columnas.split(",");
				String primaryKeys_ = datos[0];
				String sentenciaWhere = primaryKeys + "=" + primaryKeys_;
				String sentenciaSet = "";

				for (int i = 0; i <= columnas_.length-1; i++)
				{
					sentenciaSet += tipos[i] == "VARCHAR" ? columnas_[i] + "='" + datos[i+1] + "',"
							: columnas_[i] + "=" + datos[i+1] + ",";
				}
				sentenciaSet = sentenciaSet.substring(0, sentenciaSet.length() - 1);

				String stringSQL = "update " + bd + "." + nombre + " set " + sentenciaSet + " where " + sentenciaWhere;
				statement.executeUpdate(stringSQL);
			}

			statement.close();
			tablaRS.close();
			br.close();
		}
		catch (Exception ex)
		{
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ruta
	 */
	public static void removePorFichero(String ruta)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(ruta));

			String linea = "";
			String[] infoPrincipal = new String[2];

			// BD, Nombre de tabla y Cabecera
			for (int nl = 0; nl <= 1; nl++) {
				linea = br.readLine();
				infoPrincipal[nl] = linea;
			}

			String bd = infoPrincipal[0];
			String nombre = infoPrincipal[1];

			Statement statement = conn.createStatement();

			// Busco las primary keys
//			DatabaseMetaData dmd = conn.getMetaData();
//			ResultSet rspk = dmd.getPrimaryKeys(null, null, nombre);

//			String primaryKeyNombre = "";
//			while (rspk.next())
//				primaryKeyNombre = rspk.getString("nombre");

			while ((linea = br.readLine()) != null) {
				String[] primaryKeys = linea.split(",");
				for (int i = 0; i < primaryKeys.length; i++) {
					String sentenciaWhere = "nombre" + "=" +"'" + primaryKeys[i]+"'";
					String stringSQL = "delete from " + bd + " " + nombre + " where " + sentenciaWhere;
					statement.executeUpdate(stringSQL);
				}
			}

			statement.close();
			br.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
