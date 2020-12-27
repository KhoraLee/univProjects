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

/**
 * The Matrix class contains several methods for linear algebra operations
 * Note that the dimensions of a matrix cannot be changed after construction.
 */
public class Matrix {

  /*--- Fields ---*/

  //The values of the Matrix
  private final double[][] data;

  //Number of rows
  private final int M;

  //Number of columns
  private final int N;

  /*--- Constructors ---*/

  /**
   * Constructs a matrix with 2D double array
   *
   * @param matrix M-by-N 2D double array to form matrix
   */
  public Matrix(double[][] matrix) {
    this.data = matrix;
    this.M = matrix.length;
    this.N = matrix[0].length;
  }

  /**
   * Constructs a blank matrix the specified number of rows and columns. All the elements are
   * initially null
   *
   * @param M the number of rows in this matrix
   * @param N the number of columns in this matrix
   */
  public Matrix(int M, int N) {
    this.data = new double[M][N];
    this.M = M;
    this.N = N;
  }

  /*--- Static Methods ---*/

  /**
   * Returns the sum of the two matrix (A,B) -> C = A + B
   *
   * @param A matrix A
   * @param B matrix B
   * @return matrix C
   */
  public static Matrix sum(Matrix A, Matrix B) {
    //계산 조건 체크
    if (A.M != B.M || A.N != B.N) {
      throw new IllegalArgumentException(
          "A(" + A.M + "X" + A.N + ")와 B(" + B.M + "X" + B.N + ")의 행렬 크기 불일치");
    }
    Matrix res = new Matrix(A.M, A.N);
    for (int i = 0; i < A.M; i++) {
      for (int j = 0; j < A.N; j++) {
        res.data[i][j] = A.data[i][j] + B.data[i][j];
      }
    }
    return res;
  }

  /**
   * Returns the sum of the matrices
   *
   * @param matrices Matrix array to sum
   * @return Sum of all matrix handed to this method
   */
  public static Matrix sum(Matrix... matrices) {
    int total_matrix = matrices.length;
    //조건 체크
    if (total_matrix == 1) {
      throw new IllegalArgumentException("더하기 연산에 하나의 행렬이 전달됨.");
    }
    Matrix res = matrices[0];
    for (int i = 0; i < total_matrix - 1; i++) {
      res = sum(res, matrices[i + 1]);
    }
    return res;
  }

  /**
   * Returns the subtract of the two matrix (A,B) -> C = A - B
   *
   * @param A matrix A
   * @param B matrix B
   * @return matrix C
   */
  public static Matrix sub(Matrix A, Matrix B) {
    //계산 조건 체크
    if (A.M != B.M || A.N != B.N) {
      throw new IllegalArgumentException(
          "A(" + A.M + "X" + A.N + ")와 B(" + B.M + "X" + B.N + ")의 행렬 크기 불일치");
    }
    Matrix res = new Matrix(A.M, A.N);
    for (int i = 0; i < A.M; i++) {
      for (int j = 0; j < A.N; j++) {
        res.data[i][j] = A.data[i][j] - B.data[i][j];
      }
    }
    return res;
  }

  /**
   * Returns the product of the two matrix (A,B) -> C = AB
   *
   * @param A matrix A
   * @param B matrix B
   * @return matrix C
   */
  public static Matrix product(Matrix A, Matrix B) {
    //계산 조건 체크
    if (A.N != B.M) {
      throw new IllegalArgumentException(
          "A의 Col(" + A.N + ")와 B의 Col(" + B.M + ")의 불일치");
    }
    Matrix res = new Matrix(A.M, B.N);
    for (int i = 0; i < A.M; i++) {
      for (int j = 0; j < B.N; j++) {
        for (int k = 0; k < A.N; k++) {
          res.data[i][j] += A.data[i][k] * B.data[k][j];
        }
      }
    }
    return res;
  }

  /**
   * Returns the product of matrices
   *
   * @param matrices Matrices Matrix array to product
   * @return product of all matrix handed to this method
   */
  public static Matrix product(Matrix... matrices) {
    int total_matrix = matrices.length;
    //조건 체크
    if (total_matrix == 1) {
      throw new IllegalArgumentException("곱하기 연산에 하나의 행렬이 전달됨.");
    }
    Matrix res = matrices[0];
    for (int i = 0; i < total_matrix - 1; i++) {
      res = product(res, matrices[i + 1]);
    }
    return res;
  }

  /**
   * Multiplies the matrix by the specified factor
   *
   * @param A matrix to multiply
   * @param n the factor to multiply by
   * @return Matrix multiplied by factor
   */
  public static Matrix multiply(Matrix A, double n) {
    Matrix res = new Matrix(A.M, A.N);
    for (int i = 0; i < A.M; i++) {
      for (int j = 0; j < A.N; j++) {
        res.data[i][j] = A.data[i][j] * n;
      }
    }
    return res;
  }

  /**
   * Merge two matrix into one matrix horizontally
   * [A B]
   *
   * @param A matrix A
   * @param B matrix B
   * @return [A B]
   */
  public static Matrix two2one(Matrix A, Matrix B) {
    //계산 조건 체크
    if (A.M != B.M) {
      throw new IllegalArgumentException(
          "A(" + A.M + "X" + A.N + ")와 B(" + B.M + "X" + B.N + ")의 Row의 개수 불일치");
    }
    Matrix res = new Matrix(A.M, A.N + B.N);
    for (int i = 0; i < A.M; i++) {
      System.arraycopy(A.data[i], 0, res.data[i], 0, A.N);
      System.arraycopy(B.data[i], 0, res.data[i], A.N, B.N);
    }
    return res;
  }

  /**
   * Merge multiple matrices into one matrix horizontally
   * [A B ...]
   *
   * @param matrices array of matrices
   * @return merged matrix
   */
  public static Matrix mul2one(Matrix... matrices) {
    int total_matrix = matrices.length;
    //조건 체크
    if (total_matrix == 1) {
      throw new IllegalArgumentException("연산에 하나의 행렬이 전달됨.");
    }
    Matrix res = matrices[0];
    for (int i = 0; i < total_matrix - 1; i++) {
      res = two2one(res, matrices[i + 1]);
    }
    return res;
  }

  /**
   * Merge two matrix into one matrix vertically
   * ┏ A ┓
   * ┗ B ┛
   *
   * @param A matrix A
   * @param B matrix B
   * @return merged matrix
   */
  public static Matrix vtwo2one(Matrix A, Matrix B) {
    //계산 조건 체크
    if (A.N != B.N) {
      throw new IllegalArgumentException(
          "A(" + A.M + "X" + A.N + ")와 B(" + B.M + "X" + B.N + ")의 Column의 개수 불일치");
    }
    Matrix res = new Matrix(A.M + A.N, A.N);
    for (int i = 0; i < A.M; i++) {
      System.arraycopy(A.data[i], 0, res.data[i], 0, A.N);
    }
    for (int i = 0; i < B.M; i++) {
      System.arraycopy(B.data[i], 0, res.data[A.M + i], 0, B.N);
    }
    return res;
  }

  /**
   * Merge multiple matrices into one matrix vertically
   * ┏ A ┓
   * ┃ B ┃
   * ┗ C ┛
   *
   * @param matrices array of matrices
   * @return merged matrix
   */
  public static Matrix vmul2one(Matrix... matrices) {
    int total_matrix = matrices.length;
    //조건 체크
    if (total_matrix == 1) {
      throw new IllegalArgumentException("연산에 하나의 행렬이 전달됨.");
    }
    Matrix res = matrices[0];
    for (int i = 0; i < total_matrix - 1; i++) {
      res = vtwo2one(res, matrices[i + 1]);
    }
    return res;
  }

  /**
   * Returns a matrix equals to the transpose of matrix A.
   *
   * @param A matrix A
   * @return transposed matrix of A
   */
  public static Matrix transpose(Matrix A) {
    Matrix res = new Matrix(A.N, A.M);
    for (int i = 0; i < A.M; i++) {
      for (int j = 0; j < A.N; j++) {
        res.data[j][i] = A.data[i][j];
      }
    }
    return res;
  }

  /**
   * Returns a matrix that Column vectors are normalized
   *
   * @param A matrix A
   * @return normalized matrix A
   */
  public static Matrix normalize(Matrix A) {
    Matrix res = transpose(A);
    for (int i = 0; i < res.M; i++) {
      int sum = 0;
      for (int j = 0; j < res.N; j++) {
        sum += Math.pow(res.data[i][j], 2);
      }
      double t = Math.sqrt(sum);
      for (int j = 0; j < res.N; j++) {
        res.data[i][j] /= t;
      }
    }

    return transpose(res);
  }

  /**
   * Returns the result of the Kronecker Product of A and B ( A ⊗ B )
   *
   * @param A matrix A
   * @param B matrix B
   * @return A ⊗ B
   */
  public static Matrix kronecker(Matrix A, Matrix B) {
    Matrix res = new Matrix(A.M * B.M, A.N * B.N);
    for (int ia = 0; ia < A.M; ia++) {
      for (int ja = 0; ja < A.N; ja++) {
        // For each element of a, multiply it by all the elements of b.
        for (int ib = 0; ib < B.M; ib++) {
          for (int jb = 0; jb < B.N; jb++) {
            res.data[B.M * ia + ib][B.N * ja + jb] = A.data[ia][ja] * B.data[ib][jb];
          }
        }
      }
    }
    return res;
  }

  /**
   * Returns n-by-n identity matrix
   *
   * @param n size of the matrix
   * @return n-by-n identity matrix
   */
  public static Matrix identity(int n) {
    Matrix res = new Matrix(n, n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          res.data[i][j] = 1;
        }
      }
    }
    return res;
  }

  /**
   * Returns n-point Haar matrix
   *
   * @param t factor to make H_(2^t) Haar Matrix
   * @return H_(2 ^ t) Haar Matrix
   */
  public static Matrix haar(int t) {
    Matrix res = new Matrix(new double[][]{{1}});
    Matrix m1 = new Matrix(new double[][]{{1}, {1}});
    Matrix m2 = new Matrix(new double[][]{{1}, {-1}});

    for (int i = 0; i < t; i++) {
      int n = (int) Math.pow(2, i + 1);
      res = two2one(kronecker(res, m1), kronecker(identity(n / 2), m2));
    }

    return res;
  }

  /**
   * Prints matrix handed to this method
   *
   * @param A matrix to print
   */
  public static void printMatrix(Matrix A) {
    for (int i = 0; i < A.M; i++) {
      for (int j = 0; j < A.N; j++) {
        System.out.printf("% 4.4f ", A.data[i][j]);
      }
      System.out.println();
    }
  }

  /**
   * Compare two matrix
   * @param A
   * @param B
   * @return if A and B are Same this will return True, else this will return False
   */
  public static boolean compare(Matrix A, Matrix B){
    boolean res = true;
    for (int i = 0; i < A.rowCount(); i++) {
      for (int j = 0; j < A.columnCount(); j++) {
        if (!(A.get(i,j) == B.get(i,j))) {
          res =false;
          break;
        }
      }
    }
    return res;
  }




  /*---  Methods ---*/

  public double get(int row, int col) {
    if (row < 0 || row >= this.M || col < 0 || col >= this.N) {
      throw new IndexOutOfBoundsException("Row 또는 Column의 Index 범위를 벗어남");
    }
    return data[row][col];
  }

  public void set(int row, int col, double d) {
    if (row < 0 || row >= this.M || col < 0 || col >= this.N) {
      throw new IndexOutOfBoundsException("Row 또는 Column의 Index 범위를 벗어남");
    }
    this.data[row][col] = d;
  }

  public int rowCount() {
    return this.M;
  }

  public int columnCount() {
    return this.N;
  }

  public Matrix sum(Matrix B) {
    return sum(this, B);
  }

  public Matrix sub(Matrix B) {
    return sub(this, B);
  }

  public Matrix product(Matrix B) {
    return product(this, B);
  }

  public Matrix multiply(int m) {
    return multiply(this, m);
  }

  public Matrix two2one(Matrix B) {
    return two2one(this, B);
  }

  public Matrix vtwo2one(Matrix B) {
    return vtwo2one(this, B);
  }

  public Matrix normalize() {
    return normalize(this);
  }

  public Matrix transpose() {
    return transpose(this);
  }

  public double[][] toArray() {
    return this.data;
  }

}
