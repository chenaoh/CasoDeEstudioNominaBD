package beans.security;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

import beans.model.vo.EmpleadoVO;

@SuppressWarnings("serial")
public class CacheControlPhaseListener implements PhaseListener
{
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }

    public void afterPhase(PhaseEvent event)        
    {
    }

    public void beforePhase(PhaseEvent event)
    {
    	System.out.println("****************************");
        System.out.println("EN LO DEL CACHE");
        
       FacesContext facesContext = event.getFacesContext();
       HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();

    	   response.addHeader("Pragma", "no-cache");
           response.addHeader("Cache-Control", "no-cache");
           // Stronger according to blog comment below that references HTTP spec
           response.addHeader("Cache-Control", "no-store");
           response.addHeader("Cache-Control", "must-revalidate");
           // some date in the past
           response.addHeader("Expires", "0");
      // }
       
       
       
    }
} 
