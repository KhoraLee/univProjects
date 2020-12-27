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

import java.awt.Color;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Image {

  private final int width;
  private final int height;
  private final boolean gray = false;
  BufferedImage image = null;

  /**
   * @param image the BufferedImage object from File.
   */
  public Image(BufferedImage image) {
    this.image = image;
    width = this.image.getWidth();
    height = this.image.getHeight();
  }

  /**
   * @param A the 2D n-by-n GracyScale Image Array.
   */
  public Image(double[][] A) {
    this.image = matrix2img(A);
    width = this.image.getWidth();
    height = this.image.getHeight();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  /**
   * Make n*n 2D int array from image's pixels.
   *
   * @return matrix a 2D int array
   */
  public double[][] img2matrix() {
    double[][] matrix = new double[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int gray = image.getRGB(y, x) & 0xFF;
        matrix[y][x] = gray;
      }
    }
    return matrix;
  }

  private BufferedImage matrix2img(double[][] A) {
//    BufferedImage img = new BufferedImage(A.length, A[0].length, BufferedImage.TYPE_3BYTE_BGR);
//    for (int y = 0; y < A.length; y++) {
//      for (int x = 0; x < A[0].length; x++) {
//        int tmp = (int)Math.round(A[y][x]);
//        int gray = tmp >=0 ? tmp : 0;
//        int rgb = new Color(gray, gray, gray).getRGB();
//        img.setRGB(y, x, rgb);
//      }
//    }
//    return img;

    BufferedImage img = new BufferedImage(A.length, A[0].length, BufferedImage.TYPE_3BYTE_BGR);
    double low = 0;
    for (int y = 0; y < A.length; y++) {
      for (int x = 0; x < A[0].length; x++) {
        if(low>A[y][x]){
          low = A[y][x];
        }
      }
    }
    for (int y = 0; y < A.length; y++) {
      for (int x = 0; x < A[0].length; x++) {
        int tmp = (int)Math.round(A[y][x]);
        if(low<=0){
          tmp = (int)Math.round(A[y][x]-low);
        }
        int gray = tmp;//>0? tmp:0;
        int rgb = new Color(gray, gray, gray).getRGB();
        img.setRGB(y, x, rgb);
      }
    }
    return img;
  }

  /**
   * Saves the converted grayscale image. This method builds the save path from the provided file
   * name, file extension, and absolute path of the folder that you want to save the image in.
   *
   * @param path          the absolute path of the folder that you would like to save the image
   *                      inside.
   * @param imageName     the name you would like to save the image with.
   * @param imageFileType the image file extension, without the dot (.) preceding the image file
   *                      type.
   */
  public void saveImage(String path, String imageName, String imageFileType) {

    // Save or throw exception
    try {
      System.out.println("Saving image to "
          + path.concat("\\").concat(imageName).concat(".")
          .concat(imageFileType)); // save path displayed to user

      ImageIO.write(image,
          imageFileType,
          new File(path.concat("\\").concat(imageName).concat(".").concat(imageFileType)));

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Image saved.");
  }

  public BufferedImage toBufferedImage() {
    return this.image;
  }

}
