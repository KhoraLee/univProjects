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
 * Created by JFormDesigner on Thu Oct 15 01:37:34 KST 2020
 */

package konkuk.sylee.prj01.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import konkuk.sylee.prj01.Main;
import konkuk.sylee.prj01.Utils;

/**
 * @author unknown
 */
public class MainUI extends JPanel {
  public MainUI() {
    initComponents();
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - unknown
    jTab = new JTabbedPane();
    tab01 = new JPanel();
    img01_1 = new JLabel();
    img01_2 = new JLabel();
    caption01_1 = new JLabel();
    caption01_2 = new JLabel();
    slider1 = new JSlider();
    caption01_slider = new JLabel();
    tab02 = new JPanel();
    img01_3 = new JLabel();
    img01_4 = new JLabel();
    caption01_3 = new JLabel();
    caption01_4 = new JLabel();
    slider3 = new JSlider();
    caption01_slider2 = new JLabel();
    tab03 = new JPanel();
    img03_1 = new JLabel();
    img03_2 = new JLabel();
    button1 = new JButton();
    button2 = new JButton();


    //======== jTab ========
    {
      jTab.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.BOLD, 12));
      jTab.setMaximumSize(new Dimension(536, 365));
      jTab.setBackground(Color.white);

      //======== tab01 ========
      {
        tab01.setBackground(Color.white);

        //---- img01_1 ----
        img01_1.setText("text");

        //---- img01_2 ----
        img01_2.setText("text");

        //---- caption01_1 ----
        caption01_1.setText("Original Image");

        //---- caption01_2 ----
        caption01_2.setText("Recon Image");

        //---- slider1 ----
        slider1.setBackground(Color.white);

        //---- caption01_slider ----
        caption01_slider.setText("Use slider to change size of cut off area");

        GroupLayout tab01Layout = new GroupLayout(tab01);
        tab01.setLayout(tab01Layout);
        tab01Layout.setHorizontalGroup(
            tab01Layout.createParallelGroup()
                .addGroup(tab01Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tab01Layout.createParallelGroup()
                        .addGroup(tab01Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(caption01_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(img01_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                        .addComponent(caption01_slider, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(tab01Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(img01_2, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(caption01_2, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(slider1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addContainerGap(5, Short.MAX_VALUE))
        );
        tab01Layout.setVerticalGroup(
            tab01Layout.createParallelGroup()
                .addGroup(tab01Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tab01Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(img01_1, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                        .addComponent(img01_2, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(tab01Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(caption01_1)
                        .addComponent(caption01_2))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(tab01Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(slider1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(caption01_slider))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
      }
      jTab.addTab("#1", tab01);

      //======== tab02 ========
      {
        tab02.setBackground(Color.white);

        //---- img01_3 ----
        img01_3.setText("text");

        //---- img01_4 ----
        img01_4.setText("text");

        //---- caption01_3 ----
        caption01_3.setText("Image with Low Frequency Components");

        //---- caption01_4 ----
        caption01_4.setText("Image with High Frequency Components");

        //---- slider3 ----
        slider3.setBackground(Color.white);

        //---- caption01_slider2 ----
        caption01_slider2.setText("Use slider to change size of cut off area");

        GroupLayout tab02Layout = new GroupLayout(tab02);
        tab02.setLayout(tab02Layout);
        tab02Layout.setHorizontalGroup(
            tab02Layout.createParallelGroup()
                .addGroup(tab02Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tab02Layout.createParallelGroup()
                        .addGroup(tab02Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(caption01_3, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(img01_3, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                        .addComponent(caption01_slider2, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(tab02Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(img01_4, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(caption01_4, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addComponent(slider3, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                    .addContainerGap(5, Short.MAX_VALUE))
        );
        tab02Layout.setVerticalGroup(
            tab02Layout.createParallelGroup()
                .addGroup(tab02Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tab02Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(img01_3, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                        .addComponent(img01_4, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(tab02Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(caption01_3)
                        .addComponent(caption01_4))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(tab02Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(slider3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(caption01_slider2))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
      }
      jTab.addTab("#2", tab02);

      //======== tab03 ========
      {
        tab03.setBackground(Color.white);

        //---- img03_1 ----
        img03_1.setText("Equation 1");

        //---- img03_2 ----
        img03_2.setText("Equation 2");

        //---- button1 ----
        button1.setText("Show Each Term as Image #1");

        //---- button2 ----
        button2.setText("Show Each Term as Image #2");

        GroupLayout tab03Layout = new GroupLayout(tab03);
        tab03.setLayout(tab03Layout);
        tab03Layout.setHorizontalGroup(
            tab03Layout.createParallelGroup()
                .addGroup(tab03Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(tab03Layout.createParallelGroup()
                        .addComponent(img03_1)
                        .addComponent(img03_2)
                        .addComponent(button1)
                        .addComponent(button2))
                    .addContainerGap(335, Short.MAX_VALUE))
        );
        tab03Layout.setVerticalGroup(
            tab03Layout.createParallelGroup()
                .addGroup(tab03Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(img03_1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(img03_2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button2)
                    .addGap(116, 116, 116))
        );
      }
      jTab.addTab("#3", tab03);
    }

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup()
            .addComponent(jTab, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup()
            .addComponent(jTab, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    //Load image from file
    imgload();

    //Initialize Tab03
    {
      img03_1.setText("");
      img03_2.setText("");
      img03_1.setIcon(ps03_e[0]);
      img03_2.setIcon(ps03_e[0]);
      button1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JFrame frame = new JFrame();
          frame.add(new UI_3_1());
          frame.setSize(570, 595);
          frame.setTitle("Figure #1");
          frame.setVisible(true);
          frame.setResizable(false);
        }
      });
      button2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JFrame frame = new JFrame();
          frame.add(new UI_3_2());
          frame.setSize(570, 595);
          frame.setTitle("Figure #2");
          frame.setVisible(true);
          frame.setResizable(false);
        }
      });
    }

    //BoundedRangeModel for Sliders
    BoundedRangeModel brm = slider3.getModel();
    brm.setMinimum(0);
    brm.setMaximum(9);
    brm.setValue(9);

    //Initialize Tab02
    {
      caption01_3.setHorizontalAlignment(JLabel.CENTER);
      caption01_4.setHorizontalAlignment(JLabel.CENTER);
      caption01_slider.setHorizontalAlignment(JLabel.RIGHT);
      slider3.setModel(brm);
      slider3.setMajorTickSpacing(1);
      slider3.setPaintTicks(true);
      img01_3.setText("");
      img01_3.setIcon(ps02_l[9]);
      img01_4.setText("");
      img01_4.setIcon(ps02_h[9]);
      slider3.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          int i = slider3.getValue();
          img01_3.setIcon(ps02_l[i]);
          img01_4.setIcon(ps02_h[i]);
          int n = (int)Math.pow(2,i);
          caption01_3.setText("Low Freq Image ("+n+"-by-"+n+" Upper Left)");
          caption01_4.setText("High Freq Image ("+n+"-by-"+n+" Upper Left)");
        }
      });
    }
    //Initialize Tab01
    {
      caption01_1.setHorizontalAlignment(JLabel.CENTER);
      caption01_2.setHorizontalAlignment(JLabel.CENTER);
      caption01_slider2.setHorizontalAlignment(JLabel.RIGHT);
      slider1.setModel(brm);
      slider1.setMajorTickSpacing(1);
      slider1.setPaintTicks(true);
      img01_1.setText("");
      img01_1.setIcon(ps01[9]);
      img01_2.setText("");
      img01_2.setIcon(ps01[9]);
      caption01_2.setText("Reconstructed Image (512-by-512 Upper Left)");
      slider1.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          int i = slider1.getValue();
          img01_2.setIcon(ps01[i]);
          int n = (int)Math.pow(2,i);
          caption01_2.setText("Reconstructed Image ("+n+"-by-"+n+" Upper Left)");
        }
      });
    }
  }

  private void imgload() {
    for (int i=0;i<10;i++){
      ps01[i] = new ImageIcon(Utils.loadImage("res\\ps01\\"+(int)Math.pow(2,i)+"recon.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    }
    for (int i=0;i<10;i++){
      ps02_h[i] = new ImageIcon(Utils.loadImage("res\\ps02\\high\\"+(int)Math.pow(2,i)+"recon.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    }
    for (int i=0;i<10;i++){
      ps02_l[i] = new ImageIcon(Utils.loadImage("res\\ps02\\low\\"+(int)Math.pow(2,i)+"recon.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    }

    ps03[0] = new ImageIcon(Utils.loadImage("res\\ps03-1\\t1.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[1] = new ImageIcon(Utils.loadImage("res\\ps03-1\\t2.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[2] = new ImageIcon(Utils.loadImage("res\\ps03-1\\t3.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[3] = new ImageIcon(Utils.loadImage("res\\ps03-1\\t4.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[4] = new ImageIcon(Utils.loadImage("res\\ps03-2\\t1.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[5] = new ImageIcon(Utils.loadImage("res\\ps03-2\\t2.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[6] = new ImageIcon(Utils.loadImage("res\\ps03-2\\t3.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));
    ps03[7] = new ImageIcon(Utils.loadImage("res\\ps03-2\\t4.bmp").getScaledInstance(256,256,Image.SCALE_DEFAULT));

    URL p_e1 = MainUI.class.getResource("e1.png");
    URL p_e2 = MainUI.class.getResource("e2.png");

    ps03_e[0] = new ImageIcon(Utils.loadImage(p_e1));
    ps03_e[1] = new ImageIcon(Utils.loadImage(p_e2));
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - unknown
  private JTabbedPane jTab;
  private JPanel tab01;
  private JLabel img01_1;
  private JLabel img01_2;
  private JLabel caption01_1;
  private JLabel caption01_2;
  private JSlider slider1;
  private JLabel caption01_slider;
  private JPanel tab02;
  private JLabel img01_3;
  private JLabel img01_4;
  private JLabel caption01_3;
  private JLabel caption01_4;
  private JSlider slider3;
  private JLabel caption01_slider2;
  private JPanel tab03;
  private JLabel img03_1;
  private JLabel img03_2;
  private JButton button1;
  private JButton button2;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  private ImageIcon[] ps01 = new ImageIcon[10];
  private ImageIcon[] ps02_h = new ImageIcon[10];
  private ImageIcon[] ps02_l = new ImageIcon[10];
  static ImageIcon[] ps03 = new ImageIcon[8];
  private ImageIcon[] ps03_e = new ImageIcon[2];

}
