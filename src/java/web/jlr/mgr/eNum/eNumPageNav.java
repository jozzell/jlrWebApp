/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.eNum;

/**
 *
 * @author Lloyd
 * 
 */
public enum eNumPageNav {
    showPdf_itinerary("rpt/showPdf_itinerary.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    password("_login/password.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    loginPage_01("_login/loginPage_01.xhtml",1,eNumPageNavDisplay.steps_start),
    company_info_Edit("company/company_info_Edit.xhtml",-1,null),
    eventMgrEvent("sponsor/eventMgrEvent.xhtml",3,eNumPageNavDisplay.steps_start),
    eventNew("vendor/eventNew.xhtml",2,eNumPageNavDisplay.steps_start),
    eventNew_EditVendor("vendor/eventNew_EditVendor.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    
    eventPg02_edit("sponsor/eventPg02_edit.xhtml",1,eNumPageNavDisplay.event,"Event Detail"),
    event("sponsor/eventSponsorPg00.xhtml",2,eNumPageNavDisplay.event,"Event Detail"),
    
    
    
    eventVendor_Pg1("vendor/vendorPg1_seek_Event.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    eventVendor_Pg2("vendor/vendorPg2_seek_Event.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    eventVendor_edit("vendor/vendorPg1_seek_Event_Edit.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    
    flow_home("flow_home.xhtml",-1,eNumPageNavDisplay.steps_NONE),
    
    
    
    
    RptBlock("rpt/RptBlock.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    
    client_home("client_home.xhtml",4,eNumPageNavDisplay.steps_start),
    
    Rpt_Fees_ALL("rpt/fees/Rpt_Fees_ALL.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    Rpt_Fees_ALL_PDF("rpt/fees/Rpt_Fees_ALL_PDF.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    
    RptClient("rpt/RptClient.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptClient_PDF("rpt/RptClient_PDF.xhtml",1,eNumPageNavDisplay.step_rpt,"Report"),
    RptClientVendor("rpt/RptClientVendor.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptClientVendor_PDF("rpt/RptClientVendor_PDF.xhtml",1,eNumPageNavDisplay.step_rpt,"Report"),
    
    RptRoommate("rpt/RptRoommate.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptRoommate_All("rpt/RptRoommate_All.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptRoommate_Vendor("rpt/RptRoommate_Vendor.xhtml",0,eNumPageNavDisplay.step_rpt),
    RptRoommate_PDF("rpt/RptRoommate_PDF.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptRoommate_All_PDF("rpt/RptRoommate_All_PDF.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptRoommate_Vendor_PDF("rpt/RptRoommate_Vendor_PDF.xhtml",1,eNumPageNavDisplay.step_rpt),
    
    RptCustomerPayment_All("rpt/RptCustomerPayment_All.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptCustomerPayment_All_PDF("rpt/RptCustomerPayment_All_PDF.xhtml",1,eNumPageNavDisplay.step_rpt,"Report"),
    
    customer_custPg01_Seek("customer/custPg01_Seek.xhtml",0,eNumPageNavDisplay.client_A0),
    customer_custPg01_Add("customer/custPg01_Add.xhtml",1,eNumPageNavDisplay.client_Edit),
    customer_custPg02("customer/custPg02.xhtml",1,eNumPageNavDisplay.client_A0),
    customer_custPg02_pdf("customer/custPg02_PDF.xhtml",1,eNumPageNavDisplay.client_A0),
    RptCustomerBooking_PDF("rpt/RptCustomerBooking_PDF.xhtml",1,eNumPageNavDisplay.client_A0),
    
    sponsorHotel_SponsorHotelV2Pg01("sponsorHotel/SponsorHotelV2Pg01.xhtml",0,eNumPageNavDisplay.sponsor),
    sponsorHotel_SponsorHotelV2Pg02_Payment("sponsorHotel/SponsorHotelV2Pg02_Payment.xhtml",1,eNumPageNavDisplay.sponsorPayment,"Payment Wizard"),
    sponsorHotel_SponsorHotelV2Pg02_edit("sponsorHotel/SponsorHotelV2Pg02_edit.xhtml",1,eNumPageNavDisplay.sponsorEdit),
    
    
    
    clientBooking_clientBookingPg01_List("clientBooking/clientBookingPg01_List.xhtml",2,eNumPageNavDisplay.client_A1,"Reservation Wizard"),
    clientBooking_clientBookingPg04_items("clientBooking/clientBookingPg04_items.xhtml",6,eNumPageNavDisplay.client_A3,"Reservation Wizard"),
    clientBooking_clientBookingPg04_ticket("clientBooking/clientBookingPg04_ticket.xhtml",6,eNumPageNavDisplay.client_A3,"Reservation Wizard"),
    clientBooking_clientBookingPg04_Detail("clientBooking/clientBookingPg04_Detail.xhtml",6,eNumPageNavDisplay.client_A3,"Reservation Wizard"),

    
    rm_roommate("rm/roommate.xhtml",2,eNumPageNavDisplay.client_roommate,"Roommate Wizard"),
    rm_roommate_view("rm/roommate_view.xhtml",6,eNumPageNavDisplay.client_roommate,"Roommate Wizard"),
    rm_add_customer("rm/add_customer.xhtml",1,eNumPageNavDisplay.client_roommate,"Roommate Wizard"),
    
    maintenance_lookupList("maintenance/lookupList.xhtml",5,eNumPageNavDisplay.client_A3,"Reservation Wizard"),
    maintenance_lookupListBlock("maintenance/lookupList.xhtml",3,eNumPageNavDisplay.block_A1,"Block Wizard"),
    maintenance_lookupEdit("maintenance/lookupEdit.xhtml",5,eNumPageNavDisplay.client_A3,"Reservation Wizard"),
    
    maintenance_lookupEditBlock("maintenance/lookupEdit.xhtml",3,eNumPageNavDisplay.block_A1,"Block Wizard"),
    maintenance_lookupListFlt("maintenance/lookupListFlt.xhtml",6,eNumPageNavDisplay.flt,"Reservation Wizard"),
    maintenance_lookupListFltBlock("maintenance/lookupListFlt.xhtml",1,eNumPageNavDisplay.block_A1,"Block Wizard"),
    
    blocks_BlockV2Pg00("blocks/BlockV2Pg00.xhtml",0,eNumPageNavDisplay.block_A0,"Block Wizard"),
    blocks_blockVendorUpdateFlt("blocks/blockVendorUpdateFlt.xhtml",4,eNumPageNavDisplay.block_A1,"Block Wizard"),
    blocks_blockVendorUpdate("blocks/blockVendorUpdate.xhtml",4,eNumPageNavDisplay.block_A1,"Block Wizard"),
    
    flt_pg0_viewFiights("flt/pg0_viewFiights.xhtml",5,eNumPageNavDisplay.flt,"Reservation Wizard"),
    flt_pg0_viewFiightsBlock("flt/pg0_viewFiights.xhtml",3,eNumPageNavDisplay.block_A1,"Block Wizard"),
    
    flt_pg1_flightInfoEdit("flt/pg1_flightInfoEdit.xhtml",7,eNumPageNavDisplay.flt,"Reservation Wizard"),
    flt_pg0_flightInfoEdit("flt/pg0_flightInfoEdit.xhtml",7,eNumPageNavDisplay.flt,"Reservation Wizard"),
    
    vendor_vendorPg0_type("vendor/vendorPg0_type.xhtml",3,eNumPageNavDisplay.client_A2,"Reservation Wizard"),
    vendor_vendorPg0_type_Roomate("vendor/vendorPg0_type.xhtml",3,eNumPageNavDisplay.client_roommate,"Roommate Wizard"),
    vendor_vendorPg0_typeBlock("vendor/vendorPg0_type.xhtml",1,eNumPageNavDisplay.block_A1,"Block Wizard"),
    
    vendor_vendorPg1_Edit("vendor/vendorPg1_Edit.xhtml",4,eNumPageNavDisplay.client_A3,"Reservation Wizard"),
    vendor_vendorPg1_EditBlock("vendor/vendorPg1_Edit.xhtml",2,eNumPageNavDisplay.block_A1,"Block Wizard"),
    vendor_vendorPg1_Edit_roommate("vendor/vendorPg1_Edit.xhtml",4,eNumPageNavDisplay.client_roommate,"Roommate Wizard"),
    
    vendor_vendorPg1_seek("vendor/vendorPg1_seek.xhtml",4,eNumPageNavDisplay.client_A3,"Reservation Wizard"),
    vendor_vendorPg1_seekBlock("vendor/vendorPg1_seek.xhtml",2,eNumPageNavDisplay.block_A1,"Block Wizard"),
    vendor_vendorPg1_seek_Roommate("vendor/vendorPg1_seek.xhtml",5,eNumPageNavDisplay.client_roommate,"Roommate Wizard"),
    vendor_vendorPg1_seek_Ftl("vendor/vendorPg1_seek.xhtml",4,eNumPageNavDisplay.flt,"Reservation Wizard"),
    
    RptVendorPayment_All("rpt/RptVendorPayment_All.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptVendorPayment("rpt/RptVendorPayment.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    RptVendorPayment_All_PDF("rpt/RptVendorPayment_All_PDF.xhtml",1,eNumPageNavDisplay.step_rpt,"Report"),
    RptVendorPayment_PDF("rpt/RptVendorPayment_PDF.xhtml",1,eNumPageNavDisplay.step_rpt,"Report"),
    
    
   
    
    Rpt1stNight_All("rpt/Rpt1stNight_All.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    Rpt1stNight("rpt/Rpt1stNight.xhtml",0,eNumPageNavDisplay.step_rpt,"Report"),
    Rpt1stNight_All_PDF("rpt/Rpt1stNight_All_PDF.xhtml",1,eNumPageNavDisplay.step_rpt,"Report"),
    Rpt1stNight_PDF("rpt/Rpt1stNight_PDF.xhtml",1,eNumPageNavDisplay.step_rpt),
    
    clientPayment_payment_LookupEditMethod("clientPayment/payment_LookupEdit.xhtml",4,eNumPageNavDisplay.client_payment,"Payment Wizard"),
    clientPayment_payment_LookupEditMethod_Block("clientPayment/payment_LookupEdit.xhtml",3,eNumPageNavDisplay.sponsorPayment,"Payment Wizard"),
    
    clientPayment_payment_LookupEditCatergory("clientPayment/payment_LookupEdit.xhtml",3,eNumPageNavDisplay.client_payment,"Payment Wizard"),
    clientPayment_payment_LookupEditCatergory_Block("clientPayment/payment_LookupEdit.xhtml",3,eNumPageNavDisplay.sponsorPayment,"Payment Wizard"),
    
    clientPayment_payment_Pg01_Type("clientPayment/payment_Pg01_Type.xhtml",2,eNumPageNavDisplay.client_payment,"Payment Wizard"),
    clientPayment_payment_Pg01_Type_Block("clientPayment/payment_Pg01_Type.xhtml",2,eNumPageNavDisplay.sponsorPayment,"Payment Wizard"),
    
    clientPayment_payment_Pg02_Category("clientPayment/payment_Pg02_Category.xhtml",3,eNumPageNavDisplay.client_payment,"Payment Wizard"),
    clientPayment_payment_Pg02_Category_Block("clientPayment/payment_Pg02_Category.xhtml",3,eNumPageNavDisplay.sponsorPayment,"Payment Wizard"),
    
    clientPayment_payment_Pg03_Method("clientPayment/payment_Pg03_Method.xhtml",4,eNumPageNavDisplay.client_payment,"Payment Wizard"),
    clientPayment_payment_Pg03_Method_Block("clientPayment/payment_Pg03_Method.xhtml",4,eNumPageNavDisplay.sponsorPayment,"Payment Wizard"),
    
    clientPayment_payment_Pg04("clientPayment/payment_Pg04.xhtml",5,eNumPageNavDisplay.client_payment,"Payment Wizard"),
    clientPayment_payment_Pg04_Block("clientPayment/payment_Pg04.xhtml",5,eNumPageNavDisplay.sponsorPayment,"Payment Wizard")
    
    ;
    private boolean showFlow=false;
    private final String pg,header;
    private final int tab;
    private final eNumPageNavDisplay eNav;
    
    private eNumPageNav(String pg,int tab,eNumPageNavDisplay eNav){
        this(pg,tab,eNav,null);
    }
    private eNumPageNav(String pg,int tab,eNumPageNavDisplay eNav,String header){
        this.pg = pg;
        this.tab = tab;
        this.eNav = eNav;
        this.header = header;
    }
    public eNumPageNavDisplay getNav(){
        if (eNav == null) return eNumPageNavDisplay.steps_NONE;
        return this.eNav;
    }
    /**
     * @return the pg
     */
    public String getPg() {
        if (showFlow){
            return this.getNav().getWk().getWorkFlow();
        } else {
            return pg;
        }
    }

    /**
     * @return the tab
     */
    public int getTab() {
        return tab;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param showFlow the showFlow to set
     */
    public void setShowFlow(boolean showFlow) {
        this.showFlow = showFlow;
    }
}
