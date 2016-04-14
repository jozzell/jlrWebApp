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
public class jlrMgrPDF_Make_rpt1StNight  implements Serializable{
    private custBean custBean=null;
    public jlrMgrPDF_Make_rpt1StNight(jlrMgrPDF_Interface i){
        iface = i;
        
    }
    jlrMgrPDF_Interface iface;
     public void rpt1StNightSponsor(){
         RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        clientRptV3_Obj o = new clientRptV3_Obj();
          List<rptBeanColumes> list  = o.get1StNight( 
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
          gen1St(null,list,o.getCnt(),o.getTotal());
        this.iface.getJlrMgr().setCenterHtml(eNumPageNav.Rpt1stNight_All_PDF);
    }
      public void rpt1StNightVendor(){
          RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
         clientRptV3_Obj o = new clientRptV3_Obj();
          List<rptBeanColumes> list  = o.get1StNight( 
                   iface.getJlrMgr().getVendorId(),
                    iface.getJlrMgr().getSponsorId(), 
                    iface.getJlrMgr().getEMailKey(), 
                    iface.getJlrMgr().getObj());
       gen1St(iface.getJlrMgr().getVendorDesc(),list,o.getCnt(),o.getTotal());
       this.iface.getJlrMgr().setCenterHtml(eNumPageNav.Rpt1stNight_PDF);
    }
    private void gen1St(String who,List<rptBeanColumes> list,double cnt,double total){
        who = (who == null?"":who+" ")+"1st Night Deposit for "+iface.getJlrMgr().getSponsorBean().getSponsorDesc();
        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                 who,
                iface.getFileNamePath(),
                iface.getUnixRoot(),
                true);
        (new clientRptV3_Pdf_01()).get1StNight(who, cnt, total, list, bookingTable.getDocument());
        bookingTable.close();
        iface.setPdf(bookingTable.getFileName());
        
        
    }
}
