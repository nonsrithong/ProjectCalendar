package org.idempiere.component;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.MInOut;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class ModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		
		return MInOut.class;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		if (tableName.equals(MInOut.Table_Name)) {
		     return new MInOut(Env.getCtx(), Record_ID, trxName);
		 }
		return null;
	}
	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		
		if (tableName.equals(MInOut.Table_Name)) {
		     return new MInOut(Env.getCtx(), rs, trxName);		 			     
		   }
		 return null;
	}

}
