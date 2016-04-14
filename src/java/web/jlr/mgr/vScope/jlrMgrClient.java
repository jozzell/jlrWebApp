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
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon.customer.db.custObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.custListModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class jlrMgrClient implements Serializable{
    
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    
    @ManagedProperty("#{custBean}")
    private custBean custBean;
    public jlrMgrClient(){
        
    }
    private String seek;
    
    private custListModel custListModel;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
        
    }
    public custListModel getCustListModel(){
        if (jlrMgr.getSeek() != null && custListModel == null){
            System.err.println("gen getCustListModel");
            this.setSeek(jlrMgr.getSeek());
            searchPg0();
        } else if (custListModel == null){
            List<custBean> l = new ArrayList<custBean>();
            l.add(new custBean());
            custListModel = new custListModel(l);
        }
        return custListModel;
    }
    public void addPg0(){
        this.jlrMgr.setClientBean(new custBean(),eNumPageNav.customer_custPg01_Seek);
        jlrMgr.setCenterHtml(eNumPageNav.customer_custPg01_Add);
    }
    public void editPg0(){
        if (custBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            this.jlrMgr.setClientBean(custBean,eNumPageNav.customer_custPg01_Seek);
            jlrMgr.setCenterHtml(eNumPageNav.customer_custPg01_Add);
        }
    }
    
    
    public void saveClient(){
        
    }
    public void cancel(){
        
    }
    public void nextPg0(){
        if (custBean == null){
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
        } else {
            jlrMgr.clientOptions(custBean);
        }
    }
    public void searchPg0(){
        if (seek == null) seek = "";
        seek = seek.trim();
        jlrMgr.setSeek(seek);
        
        custListModel =  new custListModel( (new custObj(jlrMgr.getEMailKey())).custSearch(getSeek(), jlrMgr.getObj()));
    }
    public void setCustBean(custBean custBean) {
        this.custBean = custBean;
    }

    /**
     * @return the custBean
     */
    public custBean getCustBean() {
        if (custBean == null) custBean = new custBean();
        return custBean;
    }

    /**
     * @return the seek
     */
    public String getSeek() {
        return seek;
    }
    /**
     * @param seek the seek to set
     */
    public void setSeek(String seek) {
        this.seek = seek;
    }
}
