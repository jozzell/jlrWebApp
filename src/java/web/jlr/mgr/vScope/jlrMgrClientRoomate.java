/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.vScope;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jlRoomsCommon._beans.clientRmBean;
import jlRoomsCommon._beans.clientRmMateBean;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon.customer.db.custObj;
import jlRoomsCommon.customerRoom.db.clientRoommateObj;
import org.primefaces.context.RequestContext;
import web.jlr.mgr.Model.clientRmModel;
import web.jlr.mgr.eNum.eNumPageNav;
import web.jlr.mgr.jlrMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class jlrMgrClientRoomate implements Serializable {

    @ManagedProperty("#{jlrMgr}")
    private jlrMgr jlrMgr = null;

    @ManagedProperty("#{clientRmBean}")
    private clientRmBean clientRmBean;
    private clientRmModel clientRmModel;
    private String seek, name;
    private int roommatePick, pick, id = 0, roommateid = 0;
    private List<custBean> customerList;
    private List<clientRmMateBean> list;

    /**
     * @return the clientRmBean
     */
    public clientRmModel getclientRmModel() {
        if (clientRmModel == null) {
            clientRmModel
                    = new clientRmModel(new clientRoommateObj(jlrMgr.getEMailKey()).getCutomerRoommateGroupList(
                                    jlrMgr.getClientBean().getCustId(),
                                    jlrMgr.getSponsorId(),
                                    jlrMgr.getObj()));
        }
        return clientRmModel;
    }

    public void done() {
        jlrMgr.setCenterHtml(eNumPageNav.rm_roommate);
    }

    public void rmNameSave() {

    }

    public void removeRm() {
        if (pick == 0) {
            RequestContext.getCurrentInstance().execute("PF('dialogWidget2').show()");
            return;
        } else {
            RequestContext.getCurrentInstance().execute("PF('dialogWidget2').hide()");
        }
        (new clientRoommateObj(jlrMgr.getEMailKey())).removeRoommdate(pick, jlrMgr.getObj());
        jlrMgr.foward();
    }

    public void rmPicked() {
        if (roommatePick == 0) {
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
            return;
        } else {
            RequestContext.getCurrentInstance().execute("PF('dialogWidget').hide()");
        }

        try {
            (new clientRoommateObj(jlrMgr.getEMailKey())).saveClientRmCustomer(
                    roommateid,
                    jlrMgr.getSponsorId(),
                    roommatePick,
                    jlrMgr.getObj());
            genList();
            jlrMgr.foward();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void newRmMate() {
        jlrMgr.setCenterHtml(eNumPageNav.rm_add_customer);
    }

    public void rmSeek() {
        this.customerList = (new custObj(jlrMgr.getEMailKey())).custSearch(seek.trim(), jlrMgr.getObj());
    }

    public void add() {
        jlrMgr.setRoommateId(0);
        jlrMgr.setCenterHtml(eNumPageNav.vendor_vendorPg0_type_Roomate);

    }

    public void edit() {
        if (clientRmBean == null) {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').show()");
            
        } else {
            RequestContext.getCurrentInstance().execute("PF('dialogWidgetbasicDialogBlockMsg').hide()");
            jlrMgr.setRoommateId(clientRmBean.getClientRmID());
            jlrMgr.setCenterHtml(eNumPageNav.rm_roommate_view);
        }
    }

    public clientRmBean getClientRmBean() {
        return clientRmBean;
    }

    /**
     * @param clientRmBean the clientRmBean to set
     */
    public void setClientRmBean(clientRmBean clientRmBean) {
        this.clientRmBean = clientRmBean;
    }

    /**
     * @param jlrMgr the jlrMgr to set
     */
    public void setJlrMgr(jlrMgr jlrMgr) {
        this.jlrMgr = jlrMgr;
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

    /**
     * @return the roommatePick
     */
    public int getRoommatePick() {
        return roommatePick;
    }

    /**
     * @param roommatePick the roommatePick to set
     */
    public void setRoommatePick(int roommatePick) {
        this.roommatePick = roommatePick;
    }

    /**
     * @return the customerList
     */
    public List<custBean> getCustomerList() {
        if (customerList == null) {
            customerList = new ArrayList<custBean>();
        }
        return customerList;
    }

    /**
     * @return the pick
     */
    public int getPick() {
        return pick;
    }

    /**
     * @param pick the pick to set
     */
    public void setPick(int pick) {
        this.pick = pick;
    }

    /**
     * @return the list
     */
    private void genList() {
        roommateid = jlrMgr.getRoommateId();
        if (roommateid == 0) {
            roommateid = genNewRmMateList();
            jlrMgr.setRoommateId(roommateid);
        }

        list = (new clientRoommateObj(jlrMgr.getEMailKey())).getRoommateList(roommateid, jlrMgr.getObj());
    }

    public List<clientRmMateBean> getList() {
        if (list == null) {
            genList();
        }
        return list;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    private void genName() {
        name = jlrMgr.getClientBean().getDisplayFullName() + " Roommate Group ";
    }

    public void setName(String name) {
        if (name == null || id == 0) {
            genName();
        }
        this.name = name;
    }

    public int genNewRmMateList() {
        String str = jlrMgr.getClientBean().getDisplayFullName();
        clientRmBean = new clientRmBean();
        clientRmBean.setDesc(str + " Roommate Group");
        clientRmBean.setWho(str);
        clientRmBean.setDbTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime()));
        clientRmBean.setSponsorId(jlrMgr.getSponsorId());
        clientRmBean.setVendorId(jlrMgr.getJlrMgrBean().getVendorId());
        clientRmBean.setLookupId(jlrMgr.getJlrMgrBean().getVendorType());
        clientRoommateObj clientRoommateObj = new clientRoommateObj(jlrMgr.getEMailKey());
        int i = clientRoommateObj.saveClientRm(clientRmBean, jlrMgr.getObj());
        clientRoommateObj.saveClientRmCustomer(i, jlrMgr.getSponsorId(), jlrMgr.getClientBean().getCustId(), jlrMgr.getObj());
        return i;
    }

}
/*
 <pou:dialog  id="basicDialogEditClient"  
                 position="60,160" resizable="false" header="Client Maintenance" widgetVar="dialogWidgetEditClient" >  
        <pou:outputPanel id="vendor_panel" style="margin-bottom:10px; font-size:10px" >  
            <ui:include src="/jlRoomsWebApp/client/ClientListV2Pg01_EditCustomer_Roommate.xhtml" />
        </pou:outputPanel>
    </pou:dialog> 
*/
