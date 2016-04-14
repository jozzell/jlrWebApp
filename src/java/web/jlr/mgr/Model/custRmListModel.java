/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.custRmBean;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Lloyd
 */
public class custRmListModel extends ListDataModel<custRmBean> implements SelectableDataModel<custRmBean>, Serializable {
   
    public custRmListModel(){
        super();
    }
    public custRmListModel(List<custRmBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(custRmBean t) {
        return t.getCustRoomId();
    }

    @Override
    public custRmBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<custRmBean> list = (List<custRmBean>) getWrappedData();  
        for(int i = 0; i < list.size();i++){
             if (list.get(i).getCustRoomId() == id){
                 
                 return list.get(i);
             }
         }
        return null;
    }
    
}
