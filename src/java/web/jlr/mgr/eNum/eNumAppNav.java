/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.jlr.mgr.eNum;

/**
 *
 * @author Lloyd
 */
public enum eNumAppNav {
    login("./index_center.xhtml",null),
    brwUnlimited("index_center.xhtml","menuTop_Event.xhtml"),
    app("./index_centerBlock.xhtml","menuTop.xhtml");
    private final String xhtml,menu;
    private eNumAppNav(String xhtml,String menu){
        this.xhtml = xhtml;
        this.menu = menu;
    }
 public boolean getRenderMenu(){
     if (this.menu == null) return false;
     return true;
 }
    /**
     * @return the xhtml
     */
    public String getXhtml() {
        return xhtml;
    }

    /**
     * @return the menu
     */
    public String getMenu() {
        if (menu == null) return "";
        return menu;
    }
}
