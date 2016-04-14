/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.Model;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import jlRoomsCommon._beans.blockBean;

import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lmeans
 */
public class blockListModel extends ListDataModel<blockBean> implements SelectableDataModel<blockBean>, Serializable {

    public  final String modelName = "block-List-Model";

    public blockListModel() {
    }

    public blockListModel(List<blockBean> list) {
        super(list);

    }

    @Override
    public Object getRowKey(blockBean t) {
        return t.getBlockId();
    }

    @Override
    public blockBean getRowData(String string) {
        long id = Long.parseLong(string);
        List<blockBean> list = (List<blockBean>) getWrappedData();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBlockId() == id) {
                
                return list.get(i);
            }
        }
        return null;
    }
}
