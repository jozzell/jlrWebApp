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
public enum eNumWorkFlow {
    
    work_flow("flow/work_flow.xhtml"),
    work_flow_VendorPayment("flow/work_flow_VendorPayment.xhtml"),
    work_flow_Block("flow/work_flow_Block.xhtml"),
    work_flow_ClientBooking("flow/work_flow_Client_Booking.xhtml"),
    work_flow_Client_Payment("flow/work_flow_Client_Payment.xhtml"),
    work_flow_Client_Roommate("flow/work_flow_Client_Roommate.xhtml")
    
    ;
    private final String workFlow;
    private eNumWorkFlow(String str){
        this.workFlow = str;
    }

    /**
     * @return the workFlow
     */
    public String getWorkFlow() {
        return workFlow;
    }

   
}
