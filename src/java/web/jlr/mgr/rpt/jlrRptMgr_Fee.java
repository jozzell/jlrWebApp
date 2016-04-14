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
import jlRoomsCommon.objMgr;
import jlRoomsV3.rpt.payment.feeV3_Obj_;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean
@RequestScoped
public class jlrRptMgr_Fee  extends objMgr implements Serializable{
    private List<rptBeanColumesAmtList> ary;
    private List<rptBeanColumes> list;
    private String cnt;
    private String amt;
     private String val1,val2;
     @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr m) {
        jlrMgr = m;
    }   
    public List<rptBeanColumesAmtList> get_FeeAll(){
        if(ary == null){
            feeV3_Obj_ o = new feeV3_Obj_();
        ary = o.getFee_All(
                   jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
            setVal1(NumberFormat.getCurrencyInstance().format(o.getGrandTotal()));
        }
        
        return ary;
    }

    /**
     * @return the cnt
     */
    public String getCnt() {
        return cnt;
    }

    /**
     * @param cnt the cnt to set
     */
    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    /**
     * @return the amt
     */
    public String getAmt() {
        return amt;
    }

    /**
     * @param amt the amt to set
     */
    public void setAmt(String amt) {
        this.amt = amt;
    }

    /**
     * @return the val1
     */
    public String getVal1() {
        return val1;
    }

    /**
     * @param val1 the val1 to set
     */
    public void setVal1(String val1) {
        this.val1 = val1;
    }

    /**
     * @return the val2
     */
    public String getVal2() {
        return val2;
    }

    /**
     * @param val2 the val2 to set
     */
    public void setVal2(String val2) {
        this.val2 = val2;
    }
}
