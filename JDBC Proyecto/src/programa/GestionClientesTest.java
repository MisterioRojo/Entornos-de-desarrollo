package programa;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import DBManager.DBManager;

class GestionClientesTest {

	@Test
	void testPideInt() {
		GestionClientes.pideInt("Prueba entero: ");
	}

	@Test
	void testPideLinea() {
		GestionClientes.pideLinea("Prueba linea: ");
	}

	@Test
	void testOpcionMostrarClientes() {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionMostrarClientes();
	}

	@Test
	void testOpcionVolcarTabla() throws IOException {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionVolcarTabla();
	}

	@Test
	void testOpcionMostrarNombres() throws SQLException {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionMostrarNombres();
	}

	@Test
	void testOpcionInsertarCliente() throws IOException {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionInsertarClienteFichero();
	}

	@Test
	void testUpdateCliente() {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.updateCliente();
	}

	@Test
	void testDeleteCliente() {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.deleteCliente();
	}

	@Test
	void testOpcionInsertarClienteFichero() throws IOException {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionInsertarClienteFichero();
	}

	@Test
	void testOpcionActualizarClienteFichero() {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionActualizarClienteFichero();
	}

	@Test
	void testOpcionEliminarClienteFichero() throws SQLException {
		DBManager.connect();
		DBManager.getTablaClientes();
		GestionClientes.opcionEliminarClienteFichero();
	}

	@Test
	void testMenuPrincipal() throws SQLException, IOException {
		GestionClientes.menuPrincipal();
	}

	@Test
	void testMain() throws SQLException, IOException {
		GestionClientes.main(null);
	}

}
