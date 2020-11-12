package beans.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * JDBC.
Java DataBase Connectivity o JDBC es una API que permite realizar operaciones de bases de datos 
desde un sistema en lenguaje Java
JDBC cuenta con una colección de interfaces Java y diferentes métodos para realizar la 
conexión a bases de datos SQL

Connection: Para establecer conexiones con las bases de datos
Statement: Para ejecutar sentencias SQL y enviarlas a las BBDD
PreparedStatement: La ruta de ejecución está predeterminada en el servidor de base de datos que
 le permite ser ejecutado varias veces
ResultSet: Para almacenar el resultado de la consulta
 * 
 */

public class Conexion {
	private String nombreBd="nomina_bd";
	private String usuario="root";
	private String password="";
	private String url="jdbc:mysql://localhost:3306/"+nombreBd+"?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";

	Connection conn=null;
	//constructor de la clase
	public Conexion(){
		try {
			//obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//obtener la conexion
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn==null) {
				System.out.println("******************NO SE PUDO CONECTAR "+nombreBd);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: "+e.getMessage());
			System.out.println("Verifique que Mysql esté encendido...");
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn=null;
	}
}
