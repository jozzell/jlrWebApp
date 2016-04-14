/*
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
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon.vendor.db.vendorObj;
import jlRoomsCommon.vendorObjTypesENum;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.lookupListModel;
import web.jlr.mgr.Model.vendorListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;
import web.jlr.mgr.jlrMgrEnum;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class jlrVendor implements Serializable{
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    
    @ManagedProperty("#{vendorBean}")
    private vendorBean vendorBean = null;
    
    //@ManagedProperty("#{lookupBean}")
    private lookupBean lookupBean= null;
    
    private vendorListModel vendorListModel;
    private lookupListModel lookupListModel;
    private List<lookupBean> list;
    private int lookupType=0;
    public jlrVendor(){
        
    }
    
    public lookupListModel getLookupListModel() {
        if (lookupListModel == null) {
            jlrMgr.rootClientReset();
            switch (jlrMgr.getJlrMgrEnum().getAppType()) {
                case jlrMgrEnum.roommate_value:
                    
                    genRoommate();
                    break;
                default:
                    genVendorList();

                    break;
            }
            lookupListModel = new lookupListModel(list);
        }
        if(lookupListModel == null) lookupListModel = new lookupListModel();
        return lookupListModel;
    }
    private void genVendorList(){
        gen(vendorObjTypesENum.HOTEL);
        gen(vendorObjTypesENum.CARRENTAL);
        gen(vendorObjTypesENum.AIRLINE);
        gen(vendorObjTypesENum.TICKETS);
        gen(vendorObjTypesENum.merchandise);
        gen(vendorObjTypesENum.RAILROAD);
        gen(vendorObjTypesENum.CRUISE);
    }
    private void genRoommate(){
        gen(vendorObjTypesENum.HOTEL);
        gen(vendorObjTypesENum.CRUISE);
        
    }
    private void gen(vendorObjTypesENum x){
       lookupBean l = new lookupBean();
       l.setLookupType(x.getType());
       l.setLookupId(x.getType());
       l.setDesc(x.getRoomDesc());
       if (list == null) list = new ArrayList();
       list.add(l);
    }
    public void pg0_typeCanel(){
        
        switch(jlrMgr.getJlrMgrEnum().getAppType()){
            case jlrMgrEnum.block:
                jlrMgr.setCenterHtml(eNumPageNav.blocks_BlockV2Pg00);
                break;
            case jlrMgrEnum.reservation_id:
                jlrMgr.reservation();
                break;
            
            default:
                jlrMgr.setCenterHtml(eNumPageNav.customer_custPg02);    
    
                break;
        }
    }
    public void pg0_typeNext() {
        if (lookupBean == null) {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            jlrMgr.setVendorType(lookupBean.getLookupType(), lookupBean.getDesc());
            switch (jlrMgr.getJlrMgrEnum().getAppType()) {
                case jlrMgrEnum.block:
                    jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seekBlock);
                    break;
                case jlrMgrEnum.roommate_value:
                    
                        
                            jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek_Roommate);
                            break;
                   
                    
                    
                default:
                    switch (lookupBean.getLookupType()) {
                        case -32:
                        case -12:
                            jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek_Ftl);
                            break;
                        default:
                            jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_seek);
                            break;
                    }
                    
                    break;
            }

        }

    }
    
    
    public void pg1_add(){
        pg1_edit(new vendorBean());
    }
    public void pg1_edit(){
        pg1_edit(getVendorBean());
    }
    private void pg1_edit(vendorBean x){
        jlrMgr.setVendorBean(x);
        switch(jlrMgr.getJlrMgrEnum().getAppType()){
            case jlrMgrEnum.block:
                jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_EditBlock);
                break;
            default:
                switch (jlrMgr.getJlrMgrEnum().getAppType()) {
                        case jlrMgrEnum.roommate_value:
                            jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_Edit_roommate);
                            break;
                        default:
                            jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg1_Edit);
                            break;
                }
                break;
        }
        
    }
   
   
    public void pg1_next(){
        if (getVendorBean() == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return;
        }
        jlrMgr.setVendorBean(vendorBean);
        int i;
        switch(jlrMgr.getJlrMgrEnum().getAppType()){
            case jlrMgrEnum.block:
                //  i = vendorBean.getVendorType();
                //if (i == -12 || i == -31 || i== -32){
                //      jlrMgr.setCenterHtml(eNumPageNav.flt_pg0_viewFiightsBlock);
                //} else {
                    jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupListBlock);
                //}
                break;
            case jlrMgrEnum.reservation_id:
                 i = vendorBean.getVendorType();
                if (i == -12 || i == -31 || i== -32){
                      jlrMgr.setCenterHtml(eNumPageNav.flt_pg0_viewFiights);
                } else {
                    jlrMgr.setCenterHtml(eNumPageNav.maintenance_lookupList);
                }
                break;
           
                
            case jlrMgrEnum.roommate_value:
                jlrMgr.roommdateEdit();   
    
                break;
        }
        
      
       
        
    }
    public void pg1_back(){
        switch(jlrMgr.getJlrMgrEnum().getAppType()){
            case jlrMgrEnum.block:
                jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg0_typeBlock);
                break;
                
            default:
                switch (jlrMgr.getJlrMgrEnum().getAppType()) {
                    case jlrMgrEnum.roommate_value:
                        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg0_type_Roomate);
                        break;
                    default:
                        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg0_type);
                        break;
                }
               
                break;
        }
       
    }
    /**
     * @return the lookupBean
     */
    public lookupBean getLookupBean() {
        //if (lookupBean == null ) setLookupBean(jlrMgr.getLookup());
        if(lookupBean == null) lookupBean = new lookupBean();
        return lookupBean;
    }

    /**
     * @return the vendorListModel
     */
    public vendorListModel getVendorListModel() {
        int i = jlrMgr.getVendorType().getLookupType();
        if (vendorListModel == null || i != lookupType){
            int x = jlrMgr.getJlrMgrBean().getVendorId();
            if (x != 0)vendorBean = (new vendorObj(jlrMgr.getEMailKey())).getVendorBean(x, jlrMgr.getObj());
            lookupType = i;
            vendorListModel = new vendorListModel((new vendorObj(jlrMgr.getEMailKey())).getVendorList(lookupType, jlrMgr.getObj()));
            
        }
        if (vendorListModel == null) vendorListModel = new vendorListModel();
        return vendorListModel;
    }

    /**
     * @param vendorBean the vendorBean to set
     */
    public void setVendorBean(vendorBean vendorBean) {
        this.vendorBean = vendorBean;
    }

    /**
     * @param lookupBean the lookupBean to set
     */
    public void setLookupBean(lookupBean lookupBean) {
        this.lookupBean = lookupBean;
    }

    /**
     * @param jlrMgr the jlrMgr to set
     */
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }

    /**
     * @return the vendorBean
     */
    public vendorBean getVendorBean() {
        return vendorBean;
    }
}
