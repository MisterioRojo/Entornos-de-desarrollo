package DBManager;

import java.io.File;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DBManagerTest {

	@Test
	void testLoadDriver() {
		DBManager.loadDriver();
	}

	@Test
	void testConnect() {
		DBManager.connect();
	}

	@Test
	void testIsConnected() {
		DBManager.isConnected();
	}

	@Test
	void testClose() {
		DBManager.connect();
		DBManager.close(); 
	}

	@Test
	void testGetTablaClientesIntInt() {
		DBManager.getTablaClientes();
	}
	@Test
	void testGetTablaClientes() {
		DBManager.getTablaClientes();
	}
	@Test
	void testPrintTablaClientes() {
		DBManager.connect();
		DBManager.printTablaClientes();
	}
	
	@Test
	void testPrintNombresClientes() throws SQLException {
		DBManager.connect();
		DBManager.printNombresClientes();
	}

	@Test
	void testGetCliente() {
		DBManager.connect();
		DBManager.getCliente(1);
	}

	@Test
	void testExistsCliente() {
		DBManager.connect();
		DBManager.existsCliente(1);
	}

	@Test
	void testPrintCliente() {
		DBManager.connect();
		DBManager.printCliente(1);
	}

	@Test
	void testInsertCliente() {
		DBManager.connect();
		DBManager.insertCliente("Carlos", "Marruecos");
	}

	@Test
	void testUpdateCliente() {
		DBManager.connect();
		DBManager.updateCliente(3, "Alejandro", "Malaga");
	}

	@Test
	void testDeleteCliente() {
		DBManager.connect();
		DBManager.deleteCliente(5);
	}

	@Test
	void testVolcarTabla() {
		
		DBManager.volcarTabla("./clientes.txt");
	}

	@Test
	void testInsertarPorFichero() {
		DBManager.insertarPorFichero("./insertar.txt");
	}

	@Test
	void testUpdatePorFichero() {
		DBManager.updatePorFichero("./update.txt");
	}

	@Test
	void testLeerDatosFichero() {
		File ruta = new File("./clientes.txt");
		DBManager.leerDatosFichero(ruta);
	}

	@Test
	void testBorrarDatosDesdeFichero() throws SQLException {
		File ruta = new File("./remove.txt");
		DBManager.borrarDatosDesdeFichero(ruta);
	}

}
