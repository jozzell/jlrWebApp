/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.jlrMgrPDF_Make;

import cv.bisc.db.dbMgr;
import java.util.List;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.rptItineraryBean;
import jlRoomsCommon.bookingTable;
import jlRoomsCommon.rpt.db.rpt_Itinerary_db;
import jlRoomsV3.rpt.client.clientRptV3_Pdf;

/**
 *
 * @author lmeans
 */
public class jlrMgrPDF_Make_ClientBooking_Obj {
    public void genClientBooking(custBean custBean,int sponsor,String key,bookingTable bookingTable,dbMgr db){
        
        //this.custBean = iface.getJlrMgr().getClientBean();
        //int id = iface.getJlrMgr().getSponsorId();
        //dbMgr db = iface.getJlrMgr().getObj();
        //String key = iface.getJlrMgr().getEMailKey();
        /*
        bookingTable bookingTable = new bookingTable(iface.getJlrMgr().getCompanyBean(),custBean,iface.getJlrMgr().getSponsorBean());
        bookingTable.openWithFile(
                 custBean.getDisplayFullName()+" Itemeary for "+iface.getJlrMgr().getSponsorBean().getSponsorDesc(),
                iface.getFileNamePath(),
                iface.getUnixRoot(),
                true);
        */
        clientRptV3_Pdf clientRptV3_Pdf = new clientRptV3_Pdf();
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        List<rptItineraryBean> list = (new rpt_Itinerary_db(key)).getFlt(
                custBean.getCustId(),
                sponsor,
                -2,
                db);
        if(list.size() > 0) clientRptV3_Pdf.genHotel(list,"Hotel Confirmation" , bookingTable.getDocument());
        
        list = (new rpt_Itinerary_db(key)).getFlt(
                    custBean.getCustId(), 
                    sponsor,
                    -12, 
                    db);
        if(list.size() > 0) clientRptV3_Pdf.genAir(list,"Airline Confirmation" , bookingTable.getDocument());
        
        list = (new rpt_Itinerary_db(key)).getFlt(
                    custBean.getCustId(), 
                    sponsor,
                    -13, 
                    db);
        if(list.size() > 0) clientRptV3_Pdf.genHotel(list,"Car Rentel Confirmation" , bookingTable.getDocument());
        
        list = (new rpt_Itinerary_db(key)).getFlt(
                    custBean.getCustId(), 
                    sponsor,
                    -32, 
                    db);
        if(list.size() > 0) clientRptV3_Pdf.genAir(list,"Railroad Confirmation" ,bookingTable.getDocument());
       
        list = (new rpt_Itinerary_db(key)).getFlt(
                    custBean.getCustId(), 
                    sponsor,
                    -33, 
                    db);
        
        if(list.size() > 0) clientRptV3_Pdf.genTicket("Ticket/Package Infomation",list, bookingTable.getDocument());
        
        list= (new rpt_Itinerary_db(key)).getFlt(
                    custBean.getCustId(), 
                    sponsor,
                    -34, 
                    db);
        if(list.size() > 0) clientRptV3_Pdf.genTicket("Merchandise",list, bookingTable.getDocument());
        
        
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //if (closeWhenDone) bookingTable.close();
        //iface.setPdf(bookingTable.getFileName());
        
        //this.iface.getJlrMgr().setCenterHtml(eNumPageNav.RptCustomerBooking_PDF);
         // ---------------------------------------------------------
         
    }
}
