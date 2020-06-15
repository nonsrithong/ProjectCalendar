package org.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.model.POWrapper;

public class MInOut extends org.compiere.model.MInOut{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MInOut(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MInOut(Properties ctx, int M_InOut_ID, String trxName) {
		super(ctx, M_InOut_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	//TODO Using POWrapper to extend model functionality
		I_M_InOut newfield = POWrapper.create(this, I_M_InOut.class);
	
		//getter
		int field = newfield.getNew_ID();
		
		//setter
		public void setNewfield(I_M_InOut  newfield) {
			this.newfield = newfield;
		}

	}