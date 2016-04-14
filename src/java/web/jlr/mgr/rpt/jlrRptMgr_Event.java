/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.rpt;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import jlRoomsV3.rpt.block.blockRptV3_Obj;
import jlRoomsV3.rpt.client.clientRptV3_Obj;
import jlRoomsV3.rpt.roommate.roommateV2_Obj;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean
@RequestScoped
public class jlrRptMgr_Event extends objMgr implements Serializable{
    private List<rptBeanColumesAmtList> rptBlock;
    private List<rptBeanColumes> list;
    private String cnt;
    private String amt;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr m) {
        jlrMgr = m;
    }   
    private String val1,val2;
    /**
     * @return the rptBlock
     */
    public List<rptBeanColumes> getSponsor1stNight(){
         if (list == null){
             clientRptV3_Obj o = new clientRptV3_Obj();
            list = o.get1StNight( 
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
             val1 = ""+o.getCnt();
             val2 =  NumberFormat.getCurrencyInstance().format(o.getTotal());
        }
        return list;
    }           
    public List<rptBeanColumes> getVendor1stNight(){
         if (list == null){
             clientRptV3_Obj o = new clientRptV3_Obj();
            list = o.get1StNight( 
                    jlrMgr.getVendorId(),
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
             val1 = ""+o.getCnt();
             val2 =  NumberFormat.getCurrencyInstance().format(o.getTotal());
        }
        return list;
    }
    public List<rptBeanColumes> getVendorBooking(){
         if (list == null){
            list = (new clientRptV3_Obj()).genClientBooking( 
                    jlrMgr.getVendorId(),
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
                    
        }
        return list;
    }
    public List<rptBeanColumesAmtList> getRoommate(){
         if (rptBlock == null){
            rptBlock = (new roommateV2_Obj()).getRoommate( 
                    jlrMgr.getClientBean().getCustId(),
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
                    
        }
        return rptBlock;
    }
    public List<rptBeanColumesAmtList> getRoommateVendor(){
         if (rptBlock == null){
            rptBlock = (new roommateV2_Obj()).getRoommateVendor( 
                    jlrMgr.getVendorId(),
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
                    
        }
        return rptBlock;
    }
    public List<rptBeanColumesAmtList> getRoommateAll(){
         if (rptBlock == null){
            rptBlock = (new roommateV2_Obj()).getRoommate( 
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
                    
        }
        return rptBlock;
    }
    public List<rptBeanColumes> getClientBooking(){
        if (list == null){
            clientRptV3_Obj o = new clientRptV3_Obj();
            list = o.genClientBooking( 
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
             
        }
        return list;
    }
    
    public List<rptBeanColumesAmtList> getRptBlock() {
        if (rptBlock == null){
            blockRptV3_Obj o = new blockRptV3_Obj();
            rptBlock = o.genBlockRpt(
                    jlrMgr.getSponsorId(), 
                    jlrMgr.getEMailKey(), 
                    jlrMgr.getObj());
             cnt = ( new DecimalFormat("#.00")).format(o.getCnt());
             amt = this.getDollarFormat(o.getAmt());
        }
        return rptBlock;
    }

    /**
     * @return the val1
     */
    public String getVal1() {
        return val1;
    }

    /**
     * @return the val2
     */
    public String getVal2() {
        return val2;
    }

    /**
     * @return the cnt
     */
    public String getCnt() {
        return cnt;
    }

    /**
     * @return the amt
     */
    public String getAmt() {
        return amt;
    }
}
