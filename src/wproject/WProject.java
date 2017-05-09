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
import view.show;

/**
 *
 * @author DM3-1-20
 */
public class WProject {

    management mng;
    mainw mw;
    adduser au;
    show sh;
    ctrl ctrl;

    public static void main(String[] args) {
        WProject main = new WProject();
        main.start();
    }

    private void start() {
        mng = new management();
        mw = new mainw();
        au = new adduser();
        sh = new show();
        ctrl = new ctrl(mw, au, sh, mng);
        mw.setVisible(true);
    }
}
