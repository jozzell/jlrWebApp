/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;

import java.io.Serializable;
import java.util.List;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon.bookingTable;
import jlRoomsV3.rpt.client.clientRptV3_Obj;
import jlRoomsV3.rpt.client.clientRptV3_Pdf_01;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_Make_ClientBooking  implements Serializable{
    private custBean custBean=null;
    jlrMgrPDF_Interface iface;
    public jlrMgrPDF_Make_ClientBooking(jlrMgrPDF_Interface i){
        iface = i;
    }
    
     public void rptClientBookingVendor(){
         RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
         clientRptV3_Obj o = new clientRptV3_Obj();
            List<rptBeanColumes> list = o.genClientBooking( 
                    iface.getJlrMgr().getVendorId(),
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
            genBooking(iface.getJlrMgr().getVendorDesc(),list,o.getTotal());
            this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptClientVendor_PDF); 
    }
    public void rptClientBooking(){
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        clientRptV3_Obj o = new clientRptV3_Obj();
            List<rptBeanColumes> list = o.genClientBooking( 
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
            genBooking(null,list,o.getTotal());
             this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptClient_PDF);
    }
    private void genBooking(String who,List<rptBeanColumes> list,double total){
        who = (who == null?"":who+" ")+"Client Booking for "+iface.getJlrMgr().getSponsorBean().getSponsorDesc();
        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                 who,
                iface.getFileNamePath(),
                iface.getUnixRoot(),
                true);
        (new clientRptV3_Pdf_01()).getBookingRpt(who, total, list, bookingTable.getDocument());
        bookingTable.close();
        iface.setPdf(bookingTable.getFileName());
        
        
    }
    public void genClientBooking(){
        RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        custBean= iface.getJlrMgr().getClientBean();
        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                 custBean.getDisplayFullName()+" Itemeary for "+iface.getJlrMgr().getSponsorBean().getSponsorDesc(),
                iface.getFileNamePath(),
                iface.getUnixRoot(),
                true);
        (new jlrMgrPDF_Make_ClientBooking_Obj()).genClientBooking(custBean,iface.getJlrMgr().getSponsorId(),iface.getJlrMgr().getEMailKey(),bookingTable,iface.getJlrMgr().getObj());
        bookingTable.close();
        iface.setPdf(bookingTable.getFileName());
        this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptCustomerBooking_PDF);
    }
    
}
