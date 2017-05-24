/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wproject;

import controller.ctrl;
import model.management;
import view.adduser;
import view.DeleteUser;
import view.mainw;
import view.SearchUser;
import view.ShowUsers;
import view.addres;

/**
 *
 * @author DM3-1-20
 */
public class WProject {

    management mng;
    mainw mw;
    adduser au;
    ShowUsers sh;
    SearchUser se;
    DeleteUser del;
    ctrl ctrl;
    addres ar;

    public static void main(String[] args) {
        WProject main = new WProject();
        main.start();
    }

    private void start() {
        mng = new management();
        mw = new mainw();
        au = new adduser();
        sh = new ShowUsers();
        se = new SearchUser();
        del = new DeleteUser();
        ar = new addres();
        ctrl = new ctrl(mw, au, sh,se,del, mng,ar);
        mw.setVisible(true);
    }
}
