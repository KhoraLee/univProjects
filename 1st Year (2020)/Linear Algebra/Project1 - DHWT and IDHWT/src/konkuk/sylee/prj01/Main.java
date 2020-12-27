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

package konkuk.sylee.prj01;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import konkuk.sylee.prj01.UI.MainUI;


public class Main {

  private static JLabel status = new JLabel("Checking Resources");
  private static JPanel panel = new JPanel();

  public static void main(String[] args) {
    //Loading UI for resource loading
    {
      JFrame loading = new JFrame();
      panel.add(status);
      loading.add(panel);
      loading.setSize(200, 80);
      loading.setLocationRelativeTo(null);
      loading.setVisible(true);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      chkRes();
      loading.setVisible(false);
    }
    initUI(); // Open Main UI
  }

  //To Initialize UI Components
  public static void initUI() {
    JFrame frame = new JFrame();
    frame.add(new MainUI());
    frame.setSize(580, 435);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setTitle("Linear Algebra Project 1 - 202011339");
    frame.setResizable(false);
    //Halting program when close event
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("Exiting Program");
        System.exit(0);
      }
    });
  }

  private static void chkRes() {
    System.out.println("Starting Resource Check");
    boolean isOkay =true;
    String[] resList = new String[45];
    System.arraycopy(new String[]{"res",
        "res\\ps01", "res\\ps02", "res\\ps02\\low", "res\\ps02\\high", "res\\ps03-1",
        "res\\ps03-2"}, 0, resList, 0, 7);
    for(int i = 0; i<10;i++){
      int n = (int)Math.pow(2,i);
      resList[7 + i] = "res\\ps01\\"+n+"recon.bmp";
      resList[17 + i] = "res\\ps02\\low\\"+n+"recon.bmp";
      resList[27 + i] = "res\\ps02\\high\\"+n+"recon.bmp";
    }
    for(int i = 0; i<4;i++){
      resList[37 + i] = "res\\ps03-1\\st"+(i+1)+".bmp";
      resList[41 + i] = "res\\ps03-2\\st"+(i+1)+".bmp";
    }
    for(String path : resList){
      File f = new File(path);
      if(!f.exists()){
        System.out.println(f.toString());
        isOkay = false;
        System.err.println("Some Files are Missing.\nRebuild Resources");
        status.setText("Rebuilding Resources");
        break;
      }
    }
    if (!isOkay){
      createRes(resList);
    }
    status.setText("Resource Rebuild Complete");
    System.out.println("Resource Check Finished");
  }

  private static void createRes(String[] resList) {
    for(int i=0;i<7;i++){
      Utils.dirChk(resList[i]);
    }
    ps01();
    ps02();
    ps03_1();
    ps03_2();
  }

  public static void ps01() {
    URL i_path = Main.class.getResource("image_24bit.bmp");
    Image img = new Image(Utils.loadImage(i_path));
    Matrix img_Matrix = new Matrix(img.img2matrix());
    int n = img.getHeight();
    int t = (int) Utils.baseLog(n, 2);
    System.out.println("n: " + n + "\nt: " + t);
    Matrix haar = Matrix.haar(t).normalize();
    Matrix haar_t = haar.transpose();
    Matrix B = Matrix.product(haar_t, img_Matrix, haar);

    for (int i = 0; i < t + 1; i++) {
      int nn = (int) Math.pow(2, t - i);
      Matrix B_hat = Utils.cutTopLeft(B, nn);
      Matrix A_hat = Matrix.product(haar, B_hat, haar_t);
      Image reconstructed = new Image(A_hat.toArray());
      reconstructed.saveImage("res\\ps01", nn + "recon", "bmp");
    }
  }

  public static void ps02() {
    //for High
    {
      URL i_path = Main.class.getResource("high.bmp");
      Image high = new Image(Utils.loadImage(i_path));
      Matrix img_Matrix = new Matrix(high.img2matrix());
      int n = high.getHeight();
      int t = (int) Utils.baseLog(n, 2);
//    System.out.println("n: " + n + "\nt: " + t);
      Matrix haar = Matrix.haar(t).normalize();
      Matrix haar_t = haar.transpose();
      Matrix B = Matrix.product(haar_t, img_Matrix, haar);

      for (int i = 0; i < t + 1; i++) {
        int nn = (int) Math.pow(2, t - i);
        Matrix B_hat = Utils.cutTopLeft(B, nn);
        Matrix A_hat = Matrix.product(haar, B_hat, haar_t);
        Image reconstructed = new Image(A_hat.toArray());
        reconstructed.saveImage("res\\ps02\\high", nn + "recon", "bmp");
      }
    }
    //for Low
    {
      URL i_path = Main.class.getResource("low.bmp");
      Image Low = new Image(Utils.loadImage(i_path));
      Matrix img_Matrix = new Matrix(Low.img2matrix());
      int n = Low.getHeight();
      int t = (int) Utils.baseLog(n, 2);
//    System.out.println("n: " + n + "\nt: " + t);
      Matrix haar = Matrix.haar(t).normalize();
      Matrix haar_t = haar.transpose();
      Matrix B = Matrix.product(haar_t, img_Matrix, haar);

      for (int i = 0; i < t + 1; i++) {
        int nn = (int) Math.pow(2, t - i);
        Matrix B_hat = Utils.cutTopLeft(B, nn);
        Matrix A_hat = Matrix.product(haar, B_hat, haar_t);
        Image reconstructed = new Image(A_hat.toArray());
        reconstructed.saveImage("res\\ps02\\low", nn + "recon", "bmp");
      }
    }
  }

  public static void ps03_1() {
    URL i_path = Main.class.getResource("image_24bit.bmp");
    Image img = new Image(Utils.loadImage(i_path));
    Matrix img_Matrix = new Matrix(img.img2matrix());
    int n = img.getHeight();
    int t = (int) Utils.baseLog(n, 2);
    Matrix haar = Matrix.haar(t).normalize();
    Matrix haar_t = haar.transpose();
    Matrix haar_l = Utils.cuthalf(haar_t)[0];
    Matrix haar_h = Utils.cuthalf(haar_t)[1];

    Matrix e1 = Matrix.product(haar_l.transpose(), haar_l, img_Matrix, haar_l.transpose(), haar_l);
    Matrix e2 = Matrix.product(haar_l.transpose(), haar_l, img_Matrix, haar_h.transpose(), haar_h);
    Matrix e3 = Matrix.product(haar_h.transpose(), haar_h, img_Matrix, haar_l.transpose(), haar_l);
    Matrix e4 = Matrix.product(haar_h.transpose(), haar_h, img_Matrix, haar_h.transpose(), haar_h);

    Image ie1 = new Image(e1.toArray());
    ie1.saveImage("res\\ps03-1", "t1", "bmp");
    Image ie2 = new Image(e2.toArray());
    ie2.saveImage("res\\ps03-1", "t2", "bmp");
    Image ie3 = new Image(e3.toArray());
    ie3.saveImage("res\\ps03-1", "t3", "bmp");
    Image ie4 = new Image(e4.toArray());
    ie4.saveImage("res\\ps03-1", "t4", "bmp");
  }

  public static void ps03_2() {
    URL i_path = Main.class.getResource("image_24bit.bmp");
    Image img = new Image(Utils.loadImage(i_path));
    Matrix img_Matrix = new Matrix(img.img2matrix());
    int n = img.getHeight();
    int t = (int) Utils.baseLog(n, 2);
    Matrix haar = Matrix.haar(t).normalize();
    Matrix haar_t = haar.transpose();
    Matrix haar_l = Utils.cuthalf(haar_t)[0];
    Matrix haar_ll = Utils.cuthalf(haar_l)[0];
    Matrix haar_lh = Utils.cuthalf(haar_l)[1];

    Matrix e1 = Matrix
        .product(haar_ll.transpose(), haar_ll, img_Matrix, haar_ll.transpose(), haar_ll);
    Matrix e2 = Matrix
        .product(haar_ll.transpose(), haar_ll, img_Matrix, haar_lh.transpose(), haar_lh);
    Matrix e3 = Matrix
        .product(haar_lh.transpose(), haar_lh, img_Matrix, haar_ll.transpose(), haar_ll);
    Matrix e4 = Matrix
        .product(haar_lh.transpose(), haar_lh, img_Matrix, haar_lh.transpose(), haar_lh);

    Image ie1 = new Image(e1.toArray());
    ie1.saveImage("res\\ps03-2", "t1", "bmp");
    Image ie2 = new Image(e2.toArray());
    ie2.saveImage("res\\ps03-2", "t2", "bmp");
    Image ie3 = new Image(e3.toArray());
    ie3.saveImage("res\\ps03-2", "t3", "bmp");
    Image ie4 = new Image(e4.toArray());
    ie4.saveImage("res\\ps03-2", "t4", "bmp");
  }
}