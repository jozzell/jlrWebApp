package web.jlr.mgr.wkFlow;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author Lloyd
 */
@ManagedBean
@RequestScoped
public class jlrFlow_Home extends jlrFlow_ {
    private DefaultDiagramModel model;
     
    //@PostConstruct
    public DiagramModel genHome(){
        if(model == null){
           getHome(); 
        }
        return model;
    }
    
    private void getHome() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
       
        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);
        
        Element 
                cPayment = new Element("Payment Wizard" ,"10em","0em"),
                      cRes = new Element("Reservation Wizard"  ,"25em","0em"),
               cRoommate= new Element("Roommate Wizard" ,"40em","0em"),
                
                cClient = new Element("Client Mgmt","25em","10em"),
                
                jlRooms = new Element("Event","25em","20em"),
                list = new Element("Event List","5em","9em"),
                
                Block  = new Element("Reserve Block Mgmt","40em","27em"),
                bWizard  = new Element("Block Wizard","40em","37em"), 
                //vPayment = new Element("Payment Wizard","80em","40em"),
                vPayment = new Element("Payment Wizard" ,"10em","37em"),
                vendor = new Element("Vendor Block Mgmt","10em","27em");
        
        cPayment.setStyleClass("ui-diagram-element");
        vPayment.setStyleClass("ui-diagram-element");
        bWizard.setStyleClass("ui-diagram-element");
        cRes.setStyleClass("ui-diagram-element");
        cRoommate.setStyleClass("ui-diagram-element");
        cClient.setStyleClass("ui-diagram-success");
        jlRooms.setStyleClass("ui-diagram-fail");
        list.setStyleClass("ui-diagram-fail2");
        vendor.setStyleClass("ui-diagram-success");
        Block.setStyleClass("ui-diagram-success");
        
        list.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        
        
        
        cPayment.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        bWizard.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        vPayment.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        cRes.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        cRoommate.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
        cClient.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        cClient.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_LEFT));
        cClient.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_RIGHT));
        cClient.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
        
        
        jlRooms.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        jlRooms.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_LEFT));
        jlRooms.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM_RIGHT));
        jlRooms.addEndPoint(new DotEndPoint(EndPointAnchor.TOP_LEFT));
        
        vendor.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        vendor.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        //vendor.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
        
        //vendor.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
        Block.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
        Block.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        //Block.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        
        
        model.addElement(list);
        model.addElement(cPayment);
        model.addElement(cRes);
        model.addElement(cRoommate);
        model.addElement(cClient);
        model.addElement(jlRooms);
         model.addElement(vendor);
        
         model.addElement(Block);
         model.addElement(vPayment);
         model.addElement(bWizard);
         
         model.connect(createConnection(list.getEndPoints().get(0), jlRooms.getEndPoints().get(3), null));
        model.connect(createConnection(cClient.getEndPoints().get(0), cRes.getEndPoints().get(0), null));
        model.connect(createConnection(cClient.getEndPoints().get(1), cPayment.getEndPoints().get(0), null));
        
        
        model.connect(createConnection(cClient.getEndPoints().get(2), cRoommate.getEndPoints().get(0), null));
        
        model.connect(createConnection(jlRooms.getEndPoints().get(0), cClient.getEndPoints().get(3), null));
        model.connect(createConnection(jlRooms.getEndPoints().get(1), vendor.getEndPoints().get(0),null));
        model.connect(createConnection(jlRooms.getEndPoints().get(2), Block.getEndPoints().get(0), null));
        model.connect(createConnection(vendor.getEndPoints().get(1), vPayment.getEndPoints().get(0), null));
        model.connect(createConnection(Block.getEndPoints().get(1), bWizard.getEndPoints().get(0), null));
        
        //model.connect(createConnection(vendor.getEndPoints().get(2), vendor.getEndPoints().get(2), "Vendor Maintenance"));
        //model.connect(createConnection(cClient.getEndPoints().get(4), cClient.getEndPoints().get(4), "Client Maintenance"));
    }
   
   
}
