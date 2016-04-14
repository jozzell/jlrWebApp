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
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon._jlroot.serialsCompanyObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class jlrCompanyInfo  implements Serializable{
    private vendorBean companyBean;
    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }

    /**
     * @return the companyBean
     */
    public vendorBean getCompanyBean() {
        if(companyBean == null){
            int i = jlrMgr.getCompanyBean().getVendorId();
            companyBean = (new serialsCompanyObj()).getCompanyBean(i, jlrMgr.getObjRoot(), true);
        }
        return companyBean;
    }
    public void companyBeanCancelEdit(){
        jlrMgr.setCenterHtml(eNumPageNav.eventMgrEvent);
    }
    public void companyBeanSaveEdit(){
        String str = companyBean.getVendorName();
        if ( str == null|| str.trim().length() == 0){
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
        } else {
            (new serialsCompanyObj()).updateCompanyBean(companyBean, jlrMgr.getObjRoot());
            jlrMgr.setCompany();
            jlrMgr.setCenterHtml(eNumPageNav.eventMgrEvent);
        }
        //if (getCompanyBean().)
    }

    
}
