/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon.sponsor.db.sponsorObj;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@ViewScoped
public class jlrEvents_edit implements Serializable {

    
    private sponsorBean sponsorBean;

    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;

    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
    }

    
    /**
     * @return the sponsorBean
     */
    public sponsorBean getSponsorBean() {
        if (sponsorBean == null) {
            try {
                sponsorBean = jlrMgr.genSponsorBean();
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        return sponsorBean;
    }
    public void save(){
        jlrMgr.saveSponsorBean(sponsorBean);
        //return (new sponsorObj()).sponsorGetEvent(sponsor_id, getEMailKey(),this.getObj());
    }
    /**
     * @param sponsorBean the sponsorBean to set
     */
    public void setSponsorBean(sponsorBean sponsorBean) {
        this.sponsorBean = sponsorBean;
    }

    public List<lookupBean> getFlagStatusList() {

        List<lookupBean> flagStatusList = new ArrayList<lookupBean>();
        flagStatusList.add(new lookupBean("Active", 0));
        flagStatusList.add(new lookupBean("Non-Active", 1));

        return flagStatusList;
    }

}
