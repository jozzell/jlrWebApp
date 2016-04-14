/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.custBean;

import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class custListModel extends ListDataModel<custBean> implements SelectableDataModel<custBean>, Serializable {
    public custListModel(){
        super();
    }
    public custListModel(List<custBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(custBean t) {
        return t.getCustId();
    }

    @Override
    public custBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<custBean> list = (List<custBean>) getWrappedData();  
        if (list == null) return null;
        for (custBean list1 : list) {
            if (list1.getCustId() == id) {
                return list1;
            }
        }
        return null;
    }
    
}
