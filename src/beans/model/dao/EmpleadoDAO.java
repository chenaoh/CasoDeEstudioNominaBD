package beans.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import beans.connection.Conexion;
import beans.model.utilidades.PersonasUtilidades;
import beans.model.vo.EmpleadoVO;

public class EmpleadoDAO {

	public EmpleadoVO consultarLogin(String documento,String password) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		EmpleadoVO miEmpleado = null;
		connection = miConexion.getConnection();		
		System.out.println("Documento: "+documento+" , pass: "+password);		
		try {
			if (connection != null) {				
				String consulta = "SELECT * FROM empleado where documento = ? and password = ? ";

				statement = connection.prepareStatement(consulta);
				statement.setString(1, documento);
				statement.setString(2, password);								
				result = statement.executeQuery();
				
				if (result.next() == true) {
					miEmpleado = new EmpleadoVO();
					miEmpleado.setDocumento(result.getString("documento"));
					miEmpleado.setNombre(result.getString("nombre"));
					miEmpleado.setSexo(result.getString("sexo"));
					miEmpleado.setDireccion(result.getString("direccion"));
					miEmpleado.setTelefono(result.getString("telefono"));
					miEmpleado.setSalario(Double.parseDouble(result.getString("salario")));
					miEmpleado.setPassword(result.getString("password"));
					miEmpleado.setTipo(Integer.parseInt(result.getString("tipo")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al hacer el Login: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return miEmpleado;
	}

	public String registrarPersona(EmpleadoVO miEmpleado) {
		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "INSERT INTO empleado (documento,nombre,sexo,direccion,telefono,salario,password,tipo)"
				+ "  VALUES (?,?,?,?,?,?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miEmpleado.getDocumento());
			preStatement.setString(2, miEmpleado.getNombre());
			preStatement.setString(3, miEmpleado.getSexo());
			preStatement.setString(4, miEmpleado.getDireccion());
			preStatement.setString(5, miEmpleado.getTelefono());
			preStatement.setString(6, miEmpleado.getSalario()+"");
			preStatement.setString(7, miEmpleado.getDocumento());
			preStatement.setString(8, miEmpleado.getTipo()+"");
			preStatement.execute();

			resultado = "Registro Exitoso";

		} catch (SQLException e) {
			System.out.println("No se pudo registrar el empleado: " + e.getMessage());
			resultado = "No se pudo registrar el empleado";
		} finally {
			conexion.desconectar();
		}

		return resultado;
	}

	public EmpleadoVO consultarPersonaIndividual(String documento) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		FacesContext context= FacesContext.getCurrentInstance();
		EmpleadoVO personaLogueada=(EmpleadoVO) context.getExternalContext().getSessionMap().get("usuario");
		
		
		EmpleadoVO miEmpleado = null;
		System.out.println("Documento: "+documento);

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM empleado where documento = '"+documento+"'";
		System.out.println(consulta);
		try {
			if (connection != null) {
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				if (result.next() == true) {
					miEmpleado = new EmpleadoVO();
					miEmpleado.setDocumento(result.getString("documento"));
					miEmpleado.setNombre(result.getString("nombre"));
					miEmpleado.setSexo(result.getString("sexo"));
					miEmpleado.setDireccion(result.getString("direccion"));
					miEmpleado.setTelefono(result.getString("telefono"));
					miEmpleado.setSalario(Double.parseDouble(result.getString("salario")));
					miEmpleado.setPassword(result.getString("password"));
					miEmpleado.setTipo(Integer.parseInt(result.getString("tipo")));
					
					if (personaLogueada!=null) {
						String cad="Login tipo: "+personaLogueada.getTipo()+", documento: "+personaLogueada.getDocumento()+"\n";
						cad+="persona tipo: "+miEmpleado.getTipo()+", documento: "+miEmpleado.getDocumento()+"\n";
						
						System.out.println(cad);
						if (personaLogueada.getTipo()==1 ||
								personaLogueada.getDocumento().equals(miEmpleado.getDocumento())) {
							miEmpleado.setEditar(true);
						}
					}					
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del empleado: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		
		if (miEmpleado!=null) {
			System.out.println("Nombre Persona: "+miEmpleado.getNombre());
		}else{
			System.out.println("Nombre Persona: "+miEmpleado);
		}
		
		return miEmpleado;
	}
	
	public ArrayList<EmpleadoVO> obtenerListaPersonas() {
		System.out.println("En Obtiene Lista de personas");
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		EmpleadoVO miEmpleado = new EmpleadoVO();
		ArrayList<EmpleadoVO> listaPersonas = null;
		
		FacesContext context= FacesContext.getCurrentInstance();
		EmpleadoVO personaLogueada=(EmpleadoVO) context.getExternalContext().getSessionMap().get("usuario");
		
		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM empleado ";

		try {
			if (connection != null) {
				listaPersonas = new ArrayList<>();
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				while (result.next() == true) {
					miEmpleado = new EmpleadoVO();
					miEmpleado.setDocumento(result.getString("documento"));
					miEmpleado.setNombre(result.getString("nombre"));
					miEmpleado.setSexo(result.getString("sexo"));
					miEmpleado.setDireccion(result.getString("direccion"));
					miEmpleado.setTelefono(result.getString("telefono"));
					miEmpleado.setSalario(Double.parseDouble(result.getString("salario")));
					miEmpleado.setPassword(result.getString("password"));
					miEmpleado.setTipo(Integer.parseInt(result.getString("tipo")));
					
					if (personaLogueada!=null) {
						String cad="Login tipo: "+personaLogueada.getTipo()+", documento: "+personaLogueada.getDocumento()+", Nombre Logueado: "+personaLogueada.getNombre()+"\n";
						cad+="persona tipo: "+miEmpleado.getTipo()+", documento: "+miEmpleado.getDocumento()+", Nombre Registro: "+miEmpleado.getNombre()+" estado: "+miEmpleado.isEditar()+"\n";
						
						System.out.println(cad);
						if (personaLogueada.getTipo()==1 ||
							personaLogueada.getDocumento().equals(miEmpleado.getDocumento())) {
							miEmpleado.setPermiso(true);
						}
					}
					
					listaPersonas.add(miEmpleado);
				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta de la persona: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return listaPersonas;
	}

	public String actualizarPersona(EmpleadoVO empleado) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE empleado "
					+ " SET nombre = ? , sexo=? , direccion=? , telefono=? , salario= ? , tipo= ?"
					+ " WHERE documento= ? ";
			PreparedStatement preStatement = connection.prepareStatement(consulta);

			preStatement.setString(1, empleado.getNombre());
			preStatement.setString(2, empleado.getSexo());
			preStatement.setString(3, empleado.getDireccion());
			preStatement.setString(4, empleado.getTelefono());
			preStatement.setString(5, empleado.getSalario() + "");
			preStatement.setString(6, empleado.getTipo()+"");
			preStatement.setString(7, empleado.getDocumento());
			
			preStatement.executeUpdate();

			resultado = "ok";

			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "No se pudo actualizar el empleado";
		}
		return resultado;
	}

	public String eliminarPersona(String documento) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();

		String resp = "";
		try {
			String sentencia = "DELETE FROM empleado WHERE documento= ? ";

			PreparedStatement statement = connection.prepareStatement(sentencia);
			statement.setString(1, documento);

			statement.executeUpdate();

			resp = "ok";
			statement.close();
			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp = "No se pudo eliminar el empleado";
		}
		return resp;
	}
	
}
