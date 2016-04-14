/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr;

import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_Make_ClientBooking;
import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_Make_rpt1StNight;
import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_Make_fees;
import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_Make_Payment;
import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_Interface;
import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_Make_Roommate;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgrPDF_Make.jlrMgrPDF_itinerary;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@SessionScoped
public class jlrMgrPDF implements jlrMgrPDF_Interface,Serializable {
    private String pathStr= null,unixRoot,fileName;
    
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr m) {
        jlrMgr = m;
    }
    public jlrMgrPDF(){
        StringBuilder sb = new StringBuilder();
        sb.append((new SimpleDateFormat("yyyyMMdd")).format(Calendar.getInstance().getTime()))
        .append("/")
        .append((new SimpleDateFormat("HHmmssSSSZ")).format(Calendar.getInstance().getTime()))
        .append("/")
        ;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    
    @Override
    public String getUnixRoot(){
        if (unixRoot == null){
            unixRoot = jlrMgr.getuserAccountBean().getPath();
        }
        return unixRoot;
    }
    @Override
    public String getFileNamePath(){
        if (pathStr == null){
            pathStr = jlrMgr.getPath();
            
        }
        return pathStr;
    }
    public String getPdf(){
        return fileName;
    }
    @Override
    public void setPdf(String str){
        this.fileName = str;
    }
    // ===============================================================
    public void rpt_1StNight(){
        jlrMgr.resetTab();
        this.jlrMgr.setCenterHtml(eNumPageNav.Rpt1stNight_All);
    }
    public void rpt_payment(){
        jlrMgr.resetTab();
        this.jlrMgr.setCenterHtml(eNumPageNav.RptVendorPayment_All);
    }
    public void rpt_block(){
        jlrMgr.resetTab();
        this.jlrMgr.setCenterHtml(eNumPageNav.RptBlock);
    }
    public void rpt_client_roommate(){
        jlrMgr.resetTab();
        this.jlrMgr.setCenterHtml(eNumPageNav.RptRoommate_All);
    }
    public void rpt_client_booking(){
        jlrMgr.resetTab();
         this.jlrMgr.setCenterHtml(eNumPageNav.RptClient);
    }
    public void rpt_client_payment(){
        jlrMgr.resetTab();
         this.jlrMgr.setCenterHtml(eNumPageNav.RptCustomerPayment_All);
    }
    public void rpt_fee_payment(){
        jlrMgr.resetTab();
         this.jlrMgr.setCenterHtml(eNumPageNav.Rpt_Fees_ALL);
    }
    // ================================================================
    public void itinerary(){
        (new jlrMgrPDF_itinerary()).genItinerary(this);
    }
     public void itineraryBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.customer_custPg02);
    }
    // ------------------------------------------------------------
    public void rptRoommate(){
        (new jlrMgrPDF_Make_Roommate(this)).rptRoommate();
    }
    public void rptRoomateBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.customer_custPg02);
    }
    public void rptRoommateVendor(){
         (new jlrMgrPDF_Make_Roommate(this)).rptRoommateVendor();
    }
    public void rptRoomateVendorBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.RptRoommate_Vendor);
    }
    public void rptRoommateAll(){
        (new jlrMgrPDF_Make_Roommate(this)).rptRoommateAll();
    }
    public void rptRoomateBackAll(){
        this.jlrMgr.setCenterHtml(eNumPageNav.RptRoommate_All);
    }
    // -----------------------------------------------------------
    public void rptClientBookingVendor(){
        (new jlrMgrPDF_Make_ClientBooking(this)).rptClientBookingVendor();
         
    }
    public void rptClientBookingVendorBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.RptClientVendor); 
        
    }
    public void rptClientBooking(){
        (new jlrMgrPDF_Make_ClientBooking(this)).rptClientBooking();
    }
     public void rptClientBookingBack(){
         this.jlrMgr.setCenterHtml(eNumPageNav.RptClient);
     }
     
    // -----------------------------------------------------------
    public void rpt1StNightVendor(){
        (new jlrMgrPDF_Make_rpt1StNight(this)).rpt1StNightVendor();
        
    }
    public void rpt1StNightVendorBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.Rpt1stNight);
    }
    public void rpt1StNightSponsor(){
         (new jlrMgrPDF_Make_rpt1StNight(this)).rpt1StNightSponsor();
      
    }
    public void rpt1StNightSponsorBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.Rpt1stNight_All);
    }
    
    // -----------------------------------------------------------
    public void genClientBooking(){
        (new jlrMgrPDF_Make_ClientBooking(this)).genClientBooking();  
    }
    public void genClientBookingBack(){
        //
        this.jlrMgr.setCenterHtml(eNumPageNav.customer_custPg02);
    }
    // -----------------------------------------------------------
    public void genVendorPaymentAll(){
       (new jlrMgrPDF_Make_Payment(this)).genVendorPaymentAll();
    }
    public void genVendorPaymentAll_Back(){
        this.jlrMgr.setCenterHtml(eNumPageNav.RptVendorPayment_All);
    }
    public void genVendorPayment(){
        (new jlrMgrPDF_Make_Payment(this)).genVendorPayment();
   
    }
    public void genVendorPayment_Back(){
        this.jlrMgr.setCenterHtml(eNumPageNav.RptVendorPayment);
    }
    // -----------------------------------------------------------
    
    public void genPaymentPdf_FeeAll(){
        (new jlrMgrPDF_Make_fees(this)).genPaymentPdf_FeeAll();
    }
    public void genPaymentPdf_FeeAll_Back(){
        this.jlrMgr.setCenterHtml(eNumPageNav.Rpt_Fees_ALL);
    }
    public void genPaymentPdfRptAllBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.RptCustomerPayment_All);
    }
    public void genPaymentPdfRptAll(){
            (new jlrMgrPDF_Make_Payment(this)).genPaymentPdfRptAll();
          
       
    }
    
    public void genPaymentPdfRptBack(){
        this.jlrMgr.setCenterHtml(eNumPageNav.customer_custPg02);
    }
    public void genPaymentPdfRpt(){
     (new jlrMgrPDF_Make_Payment(this)).genPaymentPdfRpt();
    }
    

    @Override
    public jlrMgr getJlrMgr() {
        return jlrMgr;
    }
   
}
