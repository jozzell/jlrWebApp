/*

disabled="#{jlBookingMgr_V2.appCustPaymentMethond.DISABLED_BROWSE_EVENT_NEXT}"




 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon.sponsor.db.sponsorObj;
import jlRoomsCommon.vendor.db.vendorObj;
import jlRoomsCommon.vendorObjTypesENum;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.sponsorListModel;
import web.jlr.mgr.Model.vendorListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrVendor_Event_New extends jlrVendor_Event_ implements Serializable{
    private vendorListModel vendorListModel;
    //private List<lookupBean> list;
    private sponsorBean sponsorBean;
    private vendorBean vendorBean;
    private sponsorListModel sponsorListModel;
    private String seek;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    public void pickSelect(){
        String str = jlrMgr.getSponsorBean().getSponsorDesc();
        if (vendorBean == null || vendorBean.getVendorId() == 0 || str == null || str.trim().length() == 0){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            jlrMgr.saveSponsorBean(vendorBean.getVendorId());
        }
    }
    public void seekSet(){
        jlrMgr.getJlrMgrBean().setSeek(seek);
        jlrMgr.setCenterHtml(eNumPageNav.eventNew);
        //if (this.seek == null) seek = "";
        //vendorListModel = new  vendorListModel((new vendorObj(jlrMgr.getEMailKey())).getVendorBean(seek, jlrMgr.getObj()));
    }

    public String getSeek() {
        return seek;
    }

    public void setSeek(String seek) {
        this.seek = seek;
    }
    public vendorListModel getSeekVendorListModel(){
        if (vendorListModel == null){
            seek= jlrMgr.getJlrMgrBean().getSeek();
            if (seek == null) seek = "";
            vendorListModel = new  vendorListModel((new vendorObj(jlrMgr.getEMailKey())).getVendorBean(seek, jlrMgr.getObj()));
        }
        return vendorListModel;
    }
    public void editVendor(){
        if (vendorBean == null || vendorBean.getVendorId() == 0){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            
        } else {
            jlrMgr.setVendorId(vendorBean.getVendorId());
            jlrMgr.setCenterHtml(eNumPageNav.eventNew_EditVendor);
        }
    }
    public void cancelNewEvent(){
        jlrMgr.setCenterHtml(eNumPageNav.eventMgrEvent);
    }
    public void newVendor(){
        jlrMgr.setVendorId(0);
        jlrMgr.setCenterHtml(eNumPageNav.eventNew_EditVendor);
    }
     public vendorBean genEditVendor(){
        if(vendorBean == null){
            int i = jlrMgr.getVendorId();
            if (i != 0){
                
                vendorBean = (new vendorObj(jlrMgr.getEMailKey())).getVendorBean(i, jlrMgr.getObj());
            }
            
        }
        if (vendorBean == null) {
            vendorBean = new vendorBean();
            vendorBean.setVendorType(-3);
        }
        return vendorBean;
        
    }
     public void saveVendorBean(){
        if (vendorBean == null || vendorBean.getVendorName() == null || vendorBean.getVendorName().trim().length() == 0){
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
        } else {
            (new vendorObj(jlrMgr.getEMailKey())).updateVendor(vendorBean, jlrMgr.getObj());
            jlrMgr.getJlrMgrBean().setSeek(vendorBean.getVendorName());
            jlrMgr.setCenterHtml(eNumPageNav.eventNew);
        }
    }
    public void cancelVendorBean(){
        jlrMgr.setCenterHtml(eNumPageNav.eventNew);
    }
    
     /*
        =========================================================================
            Using Above
        =========================================================================
     
     */
    // -----------------------------------------------------------------------
    public void eventListNext(){
        if(sponsorBean == null){
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
        } else {
            jlrMgr.setSponsor(sponsorBean.getSponsorId());
        }
    }
    // ============================================================
    
    public vendorListModel grabCurrentVendorWWW() {
        if (vendorListModel == null){
            vendorBean b = (new vendorObj(jlrMgr.getEMailKey())).getVendorBean(jlrMgr.getSponsorBean().getVendorId(), jlrMgr.getObj());
            List<vendorBean> ary = new ArrayList<vendorBean>();
            ary.add(b);
            vendorListModel = new vendorListModel(ary);
        }
        
        return vendorListModel;
    }
   
    
   
    
    
    

public sponsorListModel getSponsorListModel(){
    
         if (sponsorListModel == null){
         
         
         sponsorListModel= new sponsorListModel( (new sponsorObj(jlrMgr.getEMailKey())).genEventList(jlrMgr.getSiteId(),jlrMgr.isDemo() , jlrMgr.getObj()));
         }
         if (sponsorListModel == null) sponsorListModel= new sponsorListModel();
         
      return sponsorListModel;
     }
    /**
     * @return the vendorListModel
     */
    

   

    /**
     * @return the vendorBean
     */
    public vendorBean getVendorBean() {
        if(vendorBean == null) vendorBean = new vendorBean();
        return vendorBean;
    }

    /**
     * @param vendorBean the vendorBean to set
     */
    public void setVendorBean(vendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    /**
     * @return the vendorListModel
     */
    public vendorListModel getVendorListModel() {
        return vendorListModel;
    }

    

    /**
     * @return the sponsorBean
     */
    public sponsorBean getSponsorBean() {
        return sponsorBean;
    }

    /**
     * @param sponsorBean the sponsorBean to set
     */
    public void setSponsorBean(sponsorBean sponsorBean) {
        this.sponsorBean = sponsorBean;
    }
    
}
