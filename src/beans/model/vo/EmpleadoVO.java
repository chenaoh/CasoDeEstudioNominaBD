package beans.model.vo;

public class EmpleadoVO {
	
	private String documento;
	private String nombre;
	private String sexo;
	private String direccion;
	private String telefono;
	private double salario;
	private String password;
	private int tipo;
	private boolean editar;//se agrega el atributo para la tabla
	private boolean permiso;//se agrega para que solo la persona autorizada pueda hacer procesos cambios en los datos
	
	public EmpleadoVO(){
		
	}
	
	public EmpleadoVO(String documento, String nombre, 
			String sexo, String direccion, String telefono,
			double salario, String password, int tipo, boolean editar) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.sexo = sexo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.salario = salario;
		this.password=password;
		this.tipo=tipo;
		this.editar=editar;//se agrega al constructor
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean isPermiso() {
		return permiso;
	}

	public void setPermiso(boolean permiso) {
		this.permiso = permiso;
	}

}
