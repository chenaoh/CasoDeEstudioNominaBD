package beans.model.utilidades;

import java.util.ArrayList;
import java.util.List;

import beans.model.vo.EmpleadoVO;

public class PersonasUtilidades {

	public static List<EmpleadoVO> empleadosList=new ArrayList<EmpleadoVO>();
	static int bandera=0;
	
	public final static int TIPO_ADMIN=1;
	public final static int TIPO_EMPLEADO=2;
	
	public static void iniciarLista() {
		if (bandera==0) {
			empleadosList.add(new EmpleadoVO("admin", "Administrador", "-", "-", "-", 0.0,"admin",TIPO_ADMIN,false));
			empleadosList.add(new EmpleadoVO("123", "Pedro Zapata", "M", "Armenia Centro", "31135846", 589462.0,"123",TIPO_EMPLEADO,false));
			bandera=1;
		}
	}
}
