/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.eNum;

/**
 *
 * @author Lloyd
 */
public enum eNumPageNavDisplay {
    steps_start("steps/steps_start.xhtml"),
    event("steps/steps_Event.xhtml"),
    step_rpt("steps/step_rpt.xhtml"),
    
    
    sponsorPayment("steps/sponsor/steps_sponsor_payments.xhtml",eNumWorkFlow.work_flow_VendorPayment),
    sponsor("steps/sponsor/steps_sponsor.xhtml"),
    sponsorEdit("steps/sponsor/steps_sponsor_edit.xhtml"),
    
    block_A0("steps/block/steps_block.xhtml",eNumWorkFlow.work_flow_Block),
    block_A1("steps/block/steps_block_1.xhtml"),
    block_Rpt("steps/block/steps_block_rpt.xhtml"),
    
    steps_NONE("steps/step_NONE.xhtml"),
    cust_default("steps/steps_client_new_booking.xhtml"),
    
    client_Edit("steps/client/steps_client_info.xhtml"),
    client_A0("steps/client/steps_client.xhtml"),
    client_A1("steps/client/steps_client_1.xhtml",eNumWorkFlow.work_flow_ClientBooking),
    client_A2("steps/client/steps_client_2.xhtml"),
    client_A3("steps/client/steps_client_3.xhtml"),
    flt("steps/client/steps_flt.xhtml"),
    client_Rpt("steps/client/steps_client_booking_Rpt.xhtml"),
    
    client_payment("steps/steps_payment.xhtml",eNumWorkFlow.work_flow_Client_Payment),
    client_roommate("steps/steps_roommate.xhtml",eNumWorkFlow.work_flow_Client_Roommate)
    ;
    private String pg;
    private eNumWorkFlow wk;
    private eNumPageNavDisplay(String pg){
        this(pg,null);
    }
    private eNumPageNavDisplay(String pg,eNumWorkFlow wk){
        this.pg = pg;
        this.wk = wk;
    }
    public String getNavDisplay(){
        return pg;
    }

    /**
     * @return the wk
     */
    public eNumWorkFlow getWk() {
        if(wk == null) return eNumWorkFlow.work_flow;
        return wk;
    }

    /**
     * @param wk the wk to set
     */
    public void setWk(eNumWorkFlow wk) {
        this.wk = wk;
    }
}
