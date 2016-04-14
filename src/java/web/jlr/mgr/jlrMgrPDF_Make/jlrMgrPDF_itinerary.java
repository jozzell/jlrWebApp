/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;


import com.itextpdf.text.DocumentException;
import cv.bisc.db.dbMgr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon._beans.rptItineraryBean;
import jlRoomsCommon.bookingTable;
import jlRoomsCommon.rpt.db.rpt_Itinerary_db;
import jlRoomsV3.rpt.client.clientRptV3_Pdf;
import jlRoomsV3.rpt.client.clientRptV3_Pdf_01;
import jlRoomsV3.rpt.payment.paymentV3_Obj_Rpt;
import jlRoomsV3.rpt.payment.paymentV3_Pdf;
import jlRoomsV3.rpt.roommate.roommateV2_Obj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_itinerary  implements Serializable{
    public void genItinerary(jlrMgrPDF_Interface iface){
       RequestContext.getCurrentInstance().execute("PF('statusDialog').show()");
        
        custBean custBean = iface.getJlrMgr().getClientBean();
        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                  custBean.getDisplayFullName()+" Itinerary",
                    iface.getFileNamePath(),
                    iface.getUnixRoot(),
                    false);
        jlrMgrPDF_itinerary_Bean bean = new jlrMgrPDF_itinerary_Bean( custBean.getID(),
                iface.getJlrMgr().getSponsorId(),
                iface.getJlrMgr().getEMailKey(),
                bookingTable,
                iface.getJlrMgr().getObj());
        
        (new jlrMgrPDF_Make_ClientBooking_Obj()).genClientBooking(
                iface.getJlrMgr().getClientBean(),
                iface.getJlrMgr().getSponsorId(),
                iface.getJlrMgr().getEMailKey(),
                bookingTable,
                iface.getJlrMgr().getObj());
        genRoommateItinerary(bean,bookingTable);
        payment(bean,bookingTable);
        bookingTable.close();
        iface.setPdf(bookingTable.getFileName());
         iface.getJlrMgr().setCenterHtml(eNumPageNav.showPdf_itinerary);
    }
    public void genRoommateItinerary(jlrMgrPDF_itinerary_Bean bean, bookingTable bookingTable){
        clientRptV3_Pdf_01 rpt = new clientRptV3_Pdf_01();
        List<rptBeanColumesAmtList> list = (new roommateV2_Obj()).getRoommate( 
                    bean.getId(),
                    bean.getSponsor(), 
                    bean.getKey(), 
                    bean.getDb());
        if (list.isEmpty()) return;
        rpt.getRoomateRpt("Roommate Report",list, bookingTable.getDocument());
    }
    public void payment(jlrMgrPDF_itinerary_Bean bean, bookingTable bookingTable){
        try {
            paymentV3_Obj_Rpt rpt = new paymentV3_Obj_Rpt();
            rpt.genCustPayment(
                    bean.getId(),
                    bean.getSponsor(), 
                    bean.getKey(), 
                     bean.getDb());
            if (rpt.getRptList().isEmpty()) return;
            rptBeanColumesAmtList amt = new rptBeanColumesAmtList();
            amt.setRptList(rpt.getRptList());
            amt.setHeader(rpt.getWho());
            amt.setAmt(rpt.getTotal());
            List<rptBeanColumesAmtList> x = new ArrayList<rptBeanColumesAmtList>();
             x.add(amt);
            (new paymentV3_Pdf()).getPayment(x, bookingTable.getDocument());
            
        } catch (DocumentException ex) {
            Logger.getLogger(jlrMgrPDF_itinerary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}

/*
List<rptItineraryBean> list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                custBean.getCustId(),
                iface.getJlrMgr().getSponsorId(),
                -2,
                iface.getJlrMgr().getObj());
        clientRptV3_Pdf clientRptV3_Pdf = new clientRptV3_Pdf();
        
        if(list.size() > 0) clientRptV3_Pdf.genHotel(list,"Hotel Confirmation" , bookingTable.getDocument());
        
        list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                    custBean.getCustId(), 
                    iface.getJlrMgr().getSponsorId(),
                    -12, 
                    iface.getJlrMgr().getObj());
        if(list.size() > 0) clientRptV3_Pdf.genAir(list,"Airline Confirmation" , bookingTable.getDocument());
        
        list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                    custBean.getCustId(), 
                    iface.getJlrMgr().getSponsorId(),
                    -13, 
                    iface.getJlrMgr().getObj());
        if(list.size() > 0) clientRptV3_Pdf.genHotel(list,"Car Rentel Confirmation" , bookingTable.getDocument());
        
        list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                    custBean.getCustId(), 
                    iface.getJlrMgr().getSponsorId(),
                    -32, 
                    iface.getJlrMgr().getObj());
        if(list.size() > 0) clientRptV3_Pdf.genAir(list,"Railroad Confirmation" ,bookingTable.getDocument());
       
        list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                   custBean.getCustId(), 
                    iface.getJlrMgr().getSponsorId(),
                    -32, 
                    iface.getJlrMgr().getObj());
        if(list.size() > 0) clientRptV3_Pdf.genAir(list,"Railroad Confirmation", bookingTable.getDocument());
        
        
        // Ticket
          list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                   custBean.getCustId(), 
                    iface.getJlrMgr().getSponsorId(),
                    -33, 
                    iface.getJlrMgr().getObj());
         if(list.size() > 0) clientRptV3_Pdf.genTicket("",list, bookingTable.getDocument());
        
         // item
            list = (new rpt_Itinerary_db(iface.getJlrMgr().getEMailKey())).getFlt(
                   custBean.getCustId(), 
                    iface.getJlrMgr().getSponsorId(),
                    -34, 
                    iface.getJlrMgr().getObj());
            
         if(list.size() > 0) clientRptV3_Pdf.genTicket("",list, bookingTable.getDocument());

*/