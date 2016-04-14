/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon.vendor.db.vendorObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;
import web.jlr.mgr.jlrMgrEnum;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class jlrVendor_Edit implements Serializable{
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    private vendorBean vendorBean = null;
    /**
     * @param jlrMgr the jlrMgr to set
     */
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    public void saveVendor(){
        if (vendorBean == null || vendorBean.getVendorName() == null || vendorBean.getVendorName().trim().length() == 0){
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
        } else {
            (new vendorObj(jlrMgr.getEMailKey())).updateVendor(vendorBean, jlrMgr.getObj());
            seek();
        }
    }
    public void editCancel(){
        seek();
    }
    private void seek(){
         switch (jlrMgr.getJlrMgrEnum().getAppType()) {
                    case jlrMgrEnum.roommate_value:
                        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek_Roommate);
                        break;
                    case jlrMgrEnum.block:
                        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seekBlock);
                        break;
                    default:
                        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek);
                        break;
         }
    }
    /**
     * @return the vendorBean
     */
    public vendorBean getVendorBean() {
        if (vendorBean == null){
            int i = jlrMgr.getJlrMgrBean().getVendorId();
           
             vendorBean = jlrMgr.getVendorBean();
             
             
        }
        return vendorBean;
    }
    
    
}
