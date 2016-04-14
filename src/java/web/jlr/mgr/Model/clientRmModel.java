/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

/**
 *
 * @author lmeans
 */

import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.clientRmBean;


import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class clientRmModel   extends ListDataModel<clientRmBean> implements SelectableDataModel<clientRmBean>, Serializable {
   
    public clientRmModel(){
        super();
    }
    public clientRmModel(List<clientRmBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(clientRmBean t) {
        return t.getClientRmID();
    }

    @Override
    public clientRmBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<clientRmBean> list = (List<clientRmBean>) getWrappedData();  
        if (list == null) return null;
        for(int i = 0; i < list.size();i++){
             if (list.get(i).getClientRmID()== id){
                 
                 return list.get(i);
             }
         }
        return null;
    }
    
}
