/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.rpt;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsV3.rpt.payment.paymentV3_Obj_Rpt;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@RequestScoped
public class jlrRptMgr_VendorPayment  implements Serializable{
    
    private List<rptBeanColumesAmtList> list;
    private paymentV3_Obj_Rpt 
            custPaymentV3_Obj;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
   
    
    public void setJlrMgr(jlrMgr m) {
        jlrMgr = m;
    }
    public String getTotalStr(){
       return  getCustPaymentV3_Obj().getTotalStr();
    }
    public double getTotal(){
        return  getCustPaymentV3_Obj().getTotal();
    }
    public List<rptBeanColumes> getRptList(){
        return  getCustPaymentV3_Obj().getRptList();
    }
    private synchronized paymentV3_Obj_Rpt getCustPaymentV3_Obj(){
        if (custPaymentV3_Obj == null){
            custPaymentV3_Obj = new paymentV3_Obj_Rpt();
            custPaymentV3_Obj.genVendorPayment(
                    jlrMgr.getVendorId(), 
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
           
        }
        
        return custPaymentV3_Obj;
    }
     public String getGrandTotal(){
        getList().size();
        return NumberFormat.getCurrencyInstance().format(custPaymentV3_Obj.getGrandTotal());
    }
    public synchronized List<rptBeanColumesAmtList> getList() {
        if (list == null){
            custPaymentV3_Obj = new paymentV3_Obj_Rpt();
            list = custPaymentV3_Obj.getVendorPaymentAll(jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
        }
        return list;
    }
}
