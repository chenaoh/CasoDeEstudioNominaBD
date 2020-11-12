package beans.model.utilidades;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("datosConverter")
public class DatosConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		String valor="";
		System.out.println();
		System.out.println("****************************");
		System.out.println("VALOR: "+value);
		
		if (value!=null) {
			valor= value+"";
			
			switch (valor) {
			case "1":valor="Administrador";					
				break;
			case "2":valor="Empleado";					
				break;
			case "M":valor="Masculino";					
				break;
			case "F":valor="Femenino";					
				break;
			default:
				break;
			}			
		}
		return valor;
	}
	
}
