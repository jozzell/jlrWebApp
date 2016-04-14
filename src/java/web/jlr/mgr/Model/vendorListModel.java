/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.vendorBean;
import org.primefaces.model.SelectableDataModel;



/**
 *
 * @author lmeans
 */
public class vendorListModel extends ListDataModel<vendorBean> implements SelectableDataModel<vendorBean>, Serializable {
  
    public vendorListModel(){
        super();
    }
    public vendorListModel(List<vendorBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(vendorBean t) {
        return t.getVendorId();
    }

    @Override
    public vendorBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<vendorBean> list = (List<vendorBean>) getWrappedData();  
        if (list == null) return null;
        for (vendorBean list1 : list) {
            if (list1.getVendorId() == id) {
                return list1;
            }
        }
        return null;
    }
    
}
