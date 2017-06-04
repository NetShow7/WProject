/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wproject;

import controller.ctrl;
import model.management;
import view.AddUser;
import view.DeleteUser;
import view.MainWindow;
import view.SearchUser;
import view.ShowUsers;
import view.AddRes;
import view.FlightWindow;
import view.Login;
import view.ShowRes;

/**
 *
 * @author DM3-1-20
 */
public class WProject {

    management mng;
    MainWindow mw;
    AddUser au;
    ShowUsers sh;
    SearchUser se;
    DeleteUser del;
    ctrl ctrl;
    AddRes ar;
    ShowRes sr;
    Login lg;
    FlightWindow fw;

    public static void main(String[] args) {
        WProject main = new WProject();
        main.start();
    }

    private void start() {
        mng = new management();
        mw = new MainWindow();
        au = new AddUser();
        sh = new ShowUsers();
        se = new SearchUser();
        del = new DeleteUser();
        ar = new AddRes();
        sr = new ShowRes();
        lg = new Login();
        fw = new FlightWindow();
        ctrl = new ctrl(mw, au, sh,se,del, mng,ar,sr,lg,fw);
        mw.setVisible(true);
    }
}
