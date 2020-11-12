package beans.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import beans.model.dao.EmpleadoDAO;
import beans.model.utilidades.PersonasUtilidades;
import beans.model.vo.EmpleadoVO;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensaje="Mensaje";//mensaje usado en el error del login
	private boolean mostrarError;// se usa cuando se muestre el error del login, es un panel oculto
	private boolean permisosAdmin;
	
	//@Inject
	private EmpleadoDAO empleadoDao;
	private EmpleadoVO empleadoVo;
	
	public LoginBean() {
		empleadoDao=new EmpleadoDAO();
		empleadoVo=new EmpleadoVO();
		PersonasUtilidades.iniciarLista();
	}
	
	public String validarUsuario() {
		String pagina="#";
		EmpleadoVO empleado=empleadoDao.consultarLogin(empleadoVo.getDocumento(), empleadoVo.getPassword());
		
		if (empleado!=null) {
			empleadoVo=empleado;
			pagina="inicio.xhtml?faces-redirect=true";//para asegurarnos de que la pagina se muestra
			setMensaje("");
			setMostrarError(false);
			
			//almacenamos la sesion del usuario para usarlo en el sistema
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", empleadoVo);
			
			if (empleadoVo.getTipo()==PersonasUtilidades.TIPO_ADMIN) {
				setPermisosAdmin(true);
			}else {
				setPermisosAdmin(false);
			}			
		}
		else {
			pagina="#";
			setMensaje("El usuario no es valido, verifique nuevamente...");
			setMostrarError(true);
		}

		return pagina;
	}
	
	//validar acceso para evitar los accesos no autorizados
		public void validarAcceso(){
			System.out.println("INGRESA A VALIDAR ACCESO");		
			try {
				FacesContext context= FacesContext.getCurrentInstance();
				EmpleadoVO miEmpleado=(EmpleadoVO) context.getExternalContext().getSessionMap().get("usuario");

				if (miEmpleado==null) {
					mensaje="Usted no tiene permisos para acceder a esta pagina, por favor inicie sesión";
					context.getExternalContext().redirect("error_ingreso.xhtml");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		public void validarPermisos(){
			System.out.println("INGRESA A VALIDAR PERMISOS");	
			try {
				FacesContext context= FacesContext.getCurrentInstance();
				EmpleadoVO miEmpleado=(EmpleadoVO) context.getExternalContext().getSessionMap().get("usuario");

				if (miEmpleado!=null && miEmpleado.getTipo()!=PersonasUtilidades.TIPO_ADMIN) {
					mensaje="Usted no tiene permisos para acceder a esta pagina, comuniquese con un Administrador ";
					context.getExternalContext().redirect("error_ingreso.xhtml");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	
	public void cerrarSesion(){
		System.out.println("INGRESA A CERRAR SESION");
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
//		return "login.xhtml";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isMostrarError() {
		return mostrarError;
	}

	public void setMostrarError(boolean mostrarError) {
		this.mostrarError = mostrarError;
	}

	public EmpleadoVO getEmpleadoVo() {
		return empleadoVo;
	}

	public void setEmpleadoVo(EmpleadoVO empleadoVo) {
		this.empleadoVo = empleadoVo;
	}

	public boolean isPermisosAdmin() {
		return permisosAdmin;
	}

	public void setPermisosAdmin(boolean permisosAdmin) {
		this.permisosAdmin = permisosAdmin;
	}

}
