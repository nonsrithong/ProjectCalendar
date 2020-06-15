package org.idempiere.component;

import java.sql.Timestamp;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.base.event.LoginEventData;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MRequest;
import org.compiere.model.MRequestType;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

public class DocEvent extends AbstractEventHandler{
	private static CLogger log = CLogger.getCLogger(DocEvent.class);
	private String trxName = "";
	private PO po = null;
	private String m_processMsg = "";

	@Override
	protected void doHandleEvent(Event event) {
		String type = event.getTopic();
		//testing that it works at login
		if (type.equals(IEventTopics.AFTER_LOGIN)) {
			LoginEventData eventData = getEventData(event);
			log.fine(" topic="+event.getTopic()+" AD_Client_ID="+eventData.getAD_Client_ID()
					+" AD_Org_ID="+eventData.getAD_Org_ID()+" AD_Role_ID="+eventData.getAD_Role_ID()
					+" AD_User_ID="+eventData.getAD_User_ID());
			}
		else 
		{
			setPo(getPO(event));
			setTrxName(po.get_TrxName());
			log.info(" topic="+event.getTopic()+" po="+po);
			if (po instanceof MInOut ){
				if (IEventTopics.DOC_AFTER_COMPLETE == type){
					//get the shipments document
					MInOut inout = (MInOut)po;
					setTrxName(trxName);
					//get the Datemovement
					Timestamp datemovement = inout.getMovementDate();		
					//create new Request event for the Calendar to display
					MRequest request = new MRequest(Env.getCtx(),0,trxName);
					request.setM_InOut_ID(inout.getM_InOut_ID());
					request.setDateStartPlan(datemovement);			
					//Summary Information that will be displayed on the Dashboard Calendar
					
					MInOutLine lines[] =inout.getLines();
					StringBuilder builder = new StringBuilder(inout.getC_BPartner().getName());
					for (MInOutLine line:lines){
						 builder =  builder.append(line.getM_Product().getName());
					
					
					request.setSummary(builder.toString());
					
					MRequestType rt = new Query(Env.getCtx(),MRequestType.Table_Name,"Name='Service Request'",null).first();
					request.setR_RequestType_ID(rt.get_ID());
					request.setSalesRep_ID(inout.getSalesRep_ID());
					request.saveEx(trxName);
					log.info("Creating new Request "+request.get_ID());
					//Assign DocType for CustomerReturn 
					
					log.info("REQUEST for Calendar created from inout: "+inout.getDocumentNo());
				}
			}
				}
		}
	}
				
	
	@Override
	protected void initialize(){
		registerEvent(IEventTopics.DOC_AFTER_COMPLETE, MInOut.Table_Name);
		log.info("<PLUGIN> REQUEST CALENDAR IS NOW INITIALIZED");
		
		
	}

			private void setPo(PO eventPO) {
				 po = eventPO;
			}

			private void setTrxName(String get_TrxName) {
				trxName = get_TrxName;		
			}
		}
