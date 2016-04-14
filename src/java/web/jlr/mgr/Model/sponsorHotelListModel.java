/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.sponsorHotelBean;

import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class sponsorHotelListModel extends ListDataModel<sponsorHotelBean> implements SelectableDataModel<sponsorHotelBean>, Serializable {
   public  final String modelName = "sponsor-Hotel-List-Model";
    public sponsorHotelListModel(){
        super();
    }
    public sponsorHotelListModel(List<sponsorHotelBean> list){
        super(list);
        
    }
    @Override
    public Object getRowKey(sponsorHotelBean t) {
        return t.getSponsor_hotel_id();
    }

    @Override
    public sponsorHotelBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<sponsorHotelBean> list = (List<sponsorHotelBean>) getWrappedData();  
       for (sponsorHotelBean list1 : list) {
           if (list1.getSponsor_hotel_id() == id) {
               return list1;
           }
       }
        return null;
    }
    
}
