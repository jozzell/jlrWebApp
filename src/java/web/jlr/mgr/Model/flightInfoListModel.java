/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.flightInfoBean;

import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class flightInfoListModel extends ListDataModel<flightInfoBean> implements SelectableDataModel<flightInfoBean>, Serializable {
    public flightInfoListModel(){
        super();
    }
    public flightInfoListModel(List<flightInfoBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(flightInfoBean t) {
        return t.getFltId();
    }

    @Override
    public flightInfoBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<flightInfoBean> list = (List<flightInfoBean>) getWrappedData();  
        for(int i = 0; i < list.size();i++){
             if (list.get(i).getFltId() == id){
                 
                 return list.get(i);
             }
         }
        return null;
    }
    
}
