/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;

import java.io.Serializable;
import java.util.List;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.bookingTable;
import jlRoomsV3.rpt.client.clientRptV3_Pdf_01;
import jlRoomsV3.rpt.roommate.roommateV2_Obj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_Make_Roommate implements Serializable{
    jlrMgrPDF_Interface iface;
    private custBean custBean=null;
    public jlrMgrPDF_Make_Roommate(jlrMgrPDF_Interface i){
        iface = i;
    }
     public void rptRoommateAll(){
         RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
         List<rptBeanColumesAmtList> list = (new roommateV2_Obj()).getRoommate( 
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
          getRoommate(null,list);
         this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptRoommate_All_PDF);
    }
    public void rptRoommate(){
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        this.custBean = iface.getJlrMgr().getClientBean();
         List<rptBeanColumesAmtList> list = (new roommateV2_Obj()).getRoommate( 
                    custBean.getCustId(),
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
         getRoommate(custBean.getDisplayFullName(),list);
         this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptRoommate_PDF);
    }
     public void rptRoommateVendor(){
         RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
         List<rptBeanColumesAmtList> list= (new roommateV2_Obj()).getRoommateVendor( 
                    iface.getJlrMgr().getVendorId(),
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
          getRoommate(iface.getJlrMgr().getVendorDesc(),list);
        this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptRoommate_Vendor_PDF);
    }
    private void getRoommate(String who,List<rptBeanColumesAmtList> list){
        who = (who == null?"":who+" ")+"Roommate Report for "+iface.getJlrMgr().getSponsorBean().getSponsorDesc();
        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                 who,
                iface.getFileNamePath(),
                iface.getUnixRoot(),
                true);
        (new clientRptV3_Pdf_01()).getRoomateRpt(who,list, bookingTable.getDocument());
        //(new clientRptV3_Pdf_01()).getRoomateRpt(who,list, bookingTable.getDocument());
        bookingTable.close();
        iface.setPdf(bookingTable.getFileName());
    }
    
  
}
