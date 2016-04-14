/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lmeans
 */
@ManagedBean
@RequestScoped
public class jlrRequestScope implements Serializable {

    private List<String> images;

    /**
     * @return the images
     */
    public List<String> getImages() {
        if (images == null) {
            images = new ArrayList<String>();
           
            //images.add("/jlr/resources/images/mac.jpg");
            images.add("/jlr/resources/images/hotelsV2.jpg");
            images.add("/jlr/resources/images/carrentelV2.jpg");
            
            images.add("/jlr//resources/images/airlineV2.jpg");
            images.add("/jlr//resources/images/amtrackV2.jpg");
            images.add("/jlr//resources/images/cruise.jpg");
        }
        return images;
    }
}
