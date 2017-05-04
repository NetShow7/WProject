/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.management;
import view.adduser;
import view.mainw;

/**
 *
 * @author DM3-1-20
 */
public abstract class ctrl implements ActionListener {

    private mainw mw;
    private adduser au;
    private management mng = new management();
    
    public ctrl(mainw mw, adduser au, management mng){
        this.mw = mw;
        this.au = au;
        this.mng = mng;
        //Listeners
       this.mw.jMenuItem1.addActionListener(this);
       this.au.jButton1.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==mw.jMenuItem1) {
            au.setVisible(true);
        }else if (e.getSource() == au.jButton1) {
            
        }
    }
    
    
}
