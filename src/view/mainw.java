/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.ImageIcon;

/**
 *
 * @author DM3-1-20
 */
public class mainw extends javax.swing.JFrame {

    /**
     * Creates new form mainw
     */
    public mainw() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel(new ImageIcon("img\\fly.png"));
        mwmenubar = new javax.swing.JMenuBar();
        usermenu = new javax.swing.JMenu();
        adduser = new javax.swing.JMenuItem();
        showusers = new javax.swing.JMenuItem();
        deleteuser = new javax.swing.JMenuItem();
        searchuser = new javax.swing.JMenuItem();
        flightmenu = new javax.swing.JMenu();
        resmenu = new javax.swing.JMenu();
        checkres = new javax.swing.JMenuItem();
        showres = new javax.swing.JMenuItem();
        newres = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Skydancer manager");
        setIconImage(new ImageIcon("img\\icon.jpg").getImage());

        usermenu.setText("Users");

        adduser.setText("Add new user");
        adduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adduserActionPerformed(evt);
            }
        });
        usermenu.add(adduser);

        showusers.setText("Show all users");
        usermenu.add(showusers);

        deleteuser.setText("Delete a user");
        usermenu.add(deleteuser);

        searchuser.setText("Search user");
        usermenu.add(searchuser);

        mwmenubar.add(usermenu);

        flightmenu.setText("Flights");
        mwmenubar.add(flightmenu);

        resmenu.setText("Reservations");

        checkres.setText("Check reservation");
        resmenu.add(checkres);

        showres.setText("Show all reservations");
        resmenu.add(showres);

        newres.setText("Add new reservation");
        resmenu.add(newres);

        mwmenubar.add(resmenu);

        setJMenuBar(mwmenubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adduserActionPerformed
        
    }//GEN-LAST:event_adduserActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem adduser;
    public javax.swing.JMenuItem checkres;
    public javax.swing.JMenuItem deleteuser;
    private javax.swing.JMenu flightmenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar mwmenubar;
    public javax.swing.JMenuItem newres;
    public javax.swing.JMenu resmenu;
    public javax.swing.JMenuItem searchuser;
    public javax.swing.JMenuItem showres;
    public javax.swing.JMenuItem showusers;
    private javax.swing.JMenu usermenu;
    // End of variables declaration//GEN-END:variables
}
