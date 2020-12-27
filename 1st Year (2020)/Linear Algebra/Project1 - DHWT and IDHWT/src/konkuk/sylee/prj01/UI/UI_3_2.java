/*
 * Copyright (c) 2020 SeungyunLee
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of the copyright holders nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 */

/*
 * Created by JFormDesigner on Thu Oct 15 02:30:42 KST 2020
 */

package konkuk.sylee.prj01.UI;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import konkuk.sylee.prj01.Utils;

/**
 * @author unknown
 */
public class UI_3_2 extends JPanel {
  public UI_3_2() {
    initComponents();
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - unknown
    img01 = new JLabel();
    img02 = new JLabel();
    img03 = new JLabel();
    img04 = new JLabel();

    //---- img01 ----
    img01.setText("Term #1");

    //---- img02 ----
    img02.setText("Term #2");

    //---- img03 ----
    img03.setText("Term #3");

    //---- img04 ----
    img04.setText("Term #4");

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup()
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
              .addComponent(img01, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
              .addGap(18, 18, 18)
              .addComponent(img02, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
              .addComponent(img03, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
              .addGap(18, 18, 18)
              .addComponent(img04, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
          .addContainerGap(4, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup()
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(img01, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
            .addComponent(img02, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
          .addGap(18, 18, 18)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(img03, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
            .addComponent(img04, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
          .addContainerGap(9, Short.MAX_VALUE))
    );
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
    //Initialize Image
    {
      img01.setText("");
      img02.setText("");
      img03.setText("");
      img04.setText("");
      img01.setIcon(MainUI.ps03[4]);
      img02.setIcon(MainUI.ps03[5]);
      img03.setIcon(MainUI.ps03[6]);
      img04.setIcon(MainUI.ps03[7]);
    }
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - unknown
  private JLabel img01;
  private JLabel img02;
  private JLabel img03;
  private JLabel img04;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
