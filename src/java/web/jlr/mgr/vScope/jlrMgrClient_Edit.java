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
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon.customer.db.custObj;
import jlRoomsCommon.customerRoom.db.clientRoommateObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class jlrMgrClient_Edit  implements Serializable{
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    
    private custBean custBean;
    public jlrMgrClient_Edit(){
        
    }
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }
    /**
     * @return the custBean
     */
    public custBean getCustBean() {
        if (custBean == null){
            custBean = jlrMgr.getClientBean();
        }
        return custBean;
    }
    public custBean getNewCustBean(){
        if (custBean == null){
            custBean = new custBean();
        }
        return custBean;
    }

    /**
     * @param custBean the custBean to set
     */
    public void setCustBean(custBean custBean) {
        this.custBean = custBean;
    }
    public void saveRmMate(){
        if (!isValidCustInfor()) return;
        int i = saveCustInfo();
        (new clientRoommateObj(jlrMgr.getEMailKey())).
                saveClientRmCustomer(jlrMgr.getRoommateId(), jlrMgr.getSponsorId(), i, jlrMgr.getObj());
        cancelRmMate();
    }
    public void save(){
        if (!isValidCustInfor()) return;
        saveCustInfo();
        jlrMgr.setSeek(custBean.getLastName().trim());
        jlrMgr.setCenterHtml(jlrMgr.getDoneEditingClient());
    }
    private int saveCustInfo(){
        
        return (new custObj(jlrMgr.getEMailKey())).updateCustomer(custBean, jlrMgr.getObj(),jlrMgr.getObjRoot());
    }
    public void cancelRmMate(){
        jlrMgr.roommdateEdit();
    }
    public void cancel(){
        jlrMgr.setCenterHtml(jlrMgr.getDoneEditingClient());
    }
    private boolean isValidCustInfor(){
        
        if (this.getCustBean().getLastName() == null  || this.getCustBean().getFirstName() == null || 
                this.getCustBean().getLastName().trim().length() == 0 ||
                this.getCustBean().getFirstName().trim().length() == 0)
               {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
            return false;
        } else {
            
            return true;
        }
        
    }
   
}
