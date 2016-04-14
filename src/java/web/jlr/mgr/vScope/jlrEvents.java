/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon.sponsor.db.sponsorObj;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrEvents  implements Serializable{
    
    private vendorBean vendorBean;
    
    private sponsorBean sponsorBean;
    
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    // ============================================================
   
    // ============================================================
    public sponsorBean grabEvent(){
        if (sponsorBean == null){
            sponsorBean = jlrMgr.getSponsorBean();
        }
        return sponsorBean;
    }
   
    
    
    // =========================================================================
    // =========================================================================
    public void newEvent(){
        sponsorBean = new sponsorBean();
        sponsorBean.setKeyStr(Calendar.getInstance().getTime().toString());
        
    }
    public List<lookupBean> getFlagStatusList() {
       
           List<lookupBean>  flagStatusList = new ArrayList<lookupBean>();
            flagStatusList.add(new lookupBean("Active", 0));
            flagStatusList.add(new lookupBean("Non-Active", 1));
       
        return flagStatusList;
    }

    public void editEventCancel(){
        
    }
    public void editEventBack(){
        
    }
    public void editEventSave(){
        if (this.sponsorBean.getSponsorDesc() == null || this.sponsorBean.getSponsorDesc().trim().length() == 0){
            this.sponsorBean.setSponsorDesc(this.sponsorBean.getVendorName());
        }
        //this.sponsorObj.update(sponsorBean,iface.getObj());
        
       
        
        
    }
    
   
   
    public void vendorBack() {
        //this.getVendorObjMgr_V2().setVendorList(sponsorBean.getVendorId(),true);
        
    }

    
    public void vendorNext() {
        //endorBean = this.getVendorObjMgr_V2().getVendorBean();
        this.getSponsorBean().setVendorId(vendorBean.getVendorId());
        this.getSponsorBean().setVendorName(vendorBean.getVendorName());
        
        
    }

   
    public sponsorBean getSponsorBean() {
        if (sponsorBean == null) sponsorBean = new sponsorBean();
        return sponsorBean;
    }
}
