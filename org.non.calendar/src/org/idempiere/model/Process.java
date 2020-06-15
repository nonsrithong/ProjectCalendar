package org.idempiere.model;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class Process extends SvrProcess{

	public Process() {
		
	}
	   private int p_AD_Client_ID = 0;
	   
	protected void prepare() {
		// TODO Auto-generated method stub
			ProcessInfoParameter[] para = getParameter();
		 for (int i = 0; i < para.length; i++) {
	            String name = para[i].getParameterName();
	            if (para[i].getParameter() == null);
	            else if (name.equals("AD_Client_ID")) {
	                p_AD_Client_ID = ((BigDecimal) para[i].getParameter()).intValue();
	            }  else {
	                log.log(Level.SEVERE, "Unknown Parameter: " + name);
	            }
	        }

	    }

	@Override
	protected String doIt() throws Exception {
		String Message ="";
		// TODO Auto-generated method stub
		return Message;
	}

}
