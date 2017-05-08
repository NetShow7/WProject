/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wproject;

import controller.ctrl;
import model.management;
import view.adduser;
import view.mainw;

/**
 *
 * @author DM3-1-20
 */
public class WProject {

    management mng;
    mainw mw;
    adduser au;
    ctrl ctrl;
    
    public static void main(String[] args) {
        WProject main = new WProject();
        main.start();
    }
    private void start(){
        mng = new management();
        mw = new mainw();
        au = new adduser();
        ctrl = new ctrl(mw, au, mng);
        mw.setVisible(true);
    }
}
