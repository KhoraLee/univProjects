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

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Utils {

  /**
   * To Calculate Log_base (x)
   * @param x
   * @param base
   * @return
   */
  public static double baseLog(double x, double base) {
    return Math.log(x) / Math.log(base);
  }

  /**
   * Load Image from file and return BufferedImage
   * @param path path to Image
   * @return BufferedImage of input Image
   */
  public static BufferedImage loadImage(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }

  /**
   * Load Image from file and return BufferedImage
   * @param path path to Image
   * @return BufferedImage of input Image
   */
  public static BufferedImage loadImage(URL path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }

  public static void dirChk(String path){
    File dir = new File(path);
    if(!dir.exists()){
      dir.mkdir();
    }
  }

  /**
   * Make a file that contain matrix
   * @param filename
   * @param A
   * @throws IOException
   */
  public static void matrix2file(String filename, Matrix A) throws IOException {
    BufferedWriter outputWriter = null;
    outputWriter = new BufferedWriter(new FileWriter(filename));
    for (int i = 0; i < A.rowCount(); i++) {
      for (int j = 0; j < A.columnCount(); j++) {
        outputWriter.write(String.format("%3.2f ",A.get(i,j)));
      }
      outputWriter.newLine();
    }
    outputWriter.flush();
    outputWriter.close();
  }

  //Matrix Things Below...

  public static Matrix cutTopLeft(Matrix A, int k) {
    Matrix result = new Matrix(A.rowCount(), A.columnCount());
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < k; j++) {
        result.set(i, j, A.get(i, j));
      }
    }
    return result;
  }

  public static Matrix[] cuthalf(Matrix A) {
    int row = A.rowCount()/2;
    int col = A.columnCount();
    Matrix top = new Matrix(row,col);
    Matrix bottom = new Matrix(row,col);
    for (int i = 0; i <row; i++) {
      for (int j = 0; j < col; j++) {
        top.set(i,j,A.get(i,j));
        bottom.set(i,j,A.get(i+row,j));
      }
    }
    return new Matrix[]{top,bottom};
  }
}
