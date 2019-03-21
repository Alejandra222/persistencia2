package persistencia2es.avalon.persona.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persona {

	private String nombre;
	private String apellidos;
	private int edad;

	
	public Persona() {
		super();
	}

	


public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}




public Persona(String nombre, String apellidos, int edad) {
	super();
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.edad = edad;
}




public String getNombre() {
	return nombre;
}




public void setNombre(String nombre) {
	this.nombre = nombre;
}




public String getApellidos() {
	return apellidos;
}




public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}




public int getEdad() {
	return edad;
}




public void setEdad(int edad) {
	this.edad = edad;
}




public static List<Persona> buscarTodos() {

		List<Persona> ordenadores = new ArrayList<Persona>();
		String sql = "select * from Persona";

		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);
				ResultSet rs = sentencia.executeQuery(sql)) {

			while (rs.next()) {
				String modelo = rs.getString("nombre");
				String marca = rs.getString("apellidos");
				int precio = rs.getInt("edad");
				Persona o = new Persona(modelo, marca, precio);
				ordenadores.add(o);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e);
			// e.printStackTrace();
		}

		return ordenadores;
	}

	public void insertarOrdenador() {

		String sql = " insert into persona (nombre, apellidos, edad) values(?,?,?)";

		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {

			sentencia.setString(1, getNombre());
			sentencia.setString(2, getApellidos());
			sentencia.setDouble(3, getEdad());
			sentencia.executeUpdate();

			System.out.println(this.getNombre() + " fue insertado");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteOrdenador() {

		String sql = "delete from persona where nombre = ?";
	
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {

			sentencia.setString(1, this.getNombre());
			sentencia.executeUpdate();
			System.out.println(this.getNombre() + " fue eliminado");

			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	public void updateOrdenado() {

		String sql = "update persona set apellidos = ?, edad = ? where nombre = ?";
		
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {

			sentencia.setString(1, this.getApellidos());
			sentencia.setDouble(2, this.getEdad());
			sentencia.setString(3, this.getNombre());
			sentencia.executeUpdate();

			System.out.println(this.getNombre() + " se actualizo correctamente");

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
	}
	
	

	public static void deleteOrdenador(String nombre) throws Exception {

		String sql = "delete from persona where nombre = ?";
	
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {

			sentencia.setString(1, nombre);
			sentencia.executeUpdate();
			System.out.println(nombre + " fue eliminado");

			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
		}

	}

	public static void updateOrdenado(String modelo, String marca, double precio) {

		String sql = "update persona set apellidos = ?, edad = ? where nombre = ?";
	
		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {

			sentencia.setString(1, marca);
			sentencia.setDouble(2, precio);
			sentencia.setString(3, modelo);
			sentencia.executeUpdate();

			System.out.println(modelo + " se actualizo correctamente");

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
	}

	public static Persona buscarUnOrdenadorPorModelo(String nombre) {

		String sql = "select * from persona where nombre = ? ";
		Persona p = null;

		try (Connection conexion = DBHelper.crearConexion();
				PreparedStatement sentencia = DBHelper.crearPreparedStatement(conexion, sql);) {

			sentencia.setString(1, nombre);
			ResultSet rs = sentencia.executeQuery();// No le pasamos la sql
			rs.next();

			p = new Persona(rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"));
			// System.out.println("************* " + ordenador);

		} catch (Exception e) {
			System.out.println("Error en clase: " + e);
			// e.printStackTrace();
		}
		return p;
	}
	
	
	}


