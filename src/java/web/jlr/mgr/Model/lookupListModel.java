/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.lookupBean;

import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class lookupListModel extends ListDataModel<lookupBean> implements SelectableDataModel<lookupBean>, Serializable {
   
    public lookupListModel(){
        super();
    }
    public lookupListModel(List<lookupBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(lookupBean t) {
        return t.getLookupId();
    }

    
    @Override
    public lookupBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<lookupBean> list = (List<lookupBean>) getWrappedData();  
        if (list == null) return null;
        for(int i = 0; i < list.size();i++){
             if (list.get(i).getLookupId() == id){
                
                 return list.get(i);
             }
         }
        return null;
    }
    
}
