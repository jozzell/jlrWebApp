/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.sponsorBean;

import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class sponsorListModel extends ListDataModel<sponsorBean> implements SelectableDataModel<sponsorBean>, Serializable {
   public  final String modelName = "sponsor-List-Model";
    public sponsorListModel(){
        super();
    }
    public sponsorListModel(List<sponsorBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(sponsorBean t) {
        return t.getSponsorId();
    }

    @Override
    public sponsorBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<sponsorBean> list = (List<sponsorBean>) getWrappedData();  
       for (sponsorBean list1 : list) {
           if (list1.getSponsorId() == id) {
               return list1;
           }
       }
        return null;
    }
    
}
