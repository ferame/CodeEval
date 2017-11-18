// Sample code to read in test cases:
import java.io.*;
public class Main {
    private class Matrix{
      int m;
      int n;
      char[][] matrix;
      Matrix(int m, int n, char[][]matrix){
        this.m = m;
        this.n = n;
        this.matrix = matrix;
      }
    }

    /*Print out the new M*N matrix (in row major form) with each position(except the ones with the mines) indicating how many adjacent mines are there. E.g.

    **100332001*100
    *10022101*101110
    */
    private static void positions(int m, int n, int[][] matrix){
      String answer = "";
      for(int i = 0; i < m; i++){
        for (int j = 0; j < n; j++) {
          if (matrix[i][j] == '*') {
            answer += '*';
          }
          int minesCount = 0;
          //Left
          int k = j-1;
          int l = j+1;
          if (j > 0) {
            minesCount += (matrix[i][j-1] == '*') ? 1 : 0;
          }else{
            k = 0
          }
          //Right
          if (j < n-1) {
            minesCount += (matrix[i][j+1] == '*') ? 1 : 0;
          }else{
            l = 0;
          }
          //top
          if (i-1 >= 0) {
            for (int ind = k; ind < l; ind++) {
              minesCount += (matrix[i-1][ind] == '*') ? 1 : 0;
            }
          }
          //bottom
          if (i+1 < m) {
            for (int ind = k; ind < l; ind++) {
              minesCount += (matrix[i+1][ind] == '*') ? 1 : 0;
            }
          }
          answer += minesCount;
        }
      }
    }
    /*
    Your program should accept as its first argument a path to a filename. Each line in this file contains M,N, a semicolon and the M*N matrix in row major form. E.g.
    3,5;**.........*...
    4,4;*........*......
    */
    private static Matrix readInput(String input){
      String nm = nonEmptyLine.split(';')[0];
      String symbols = nonEmptyLine.split(';')[1];
      int m = Integer.parseInt(nm.split(',')[0]);
      int n = Integer.parseInt(nm.split(',')[1]);
      char[][] matrix = new char[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          matrix[i][j] = symbols.charAt[i*j + j];
        }
      }
      Matrix matrix = new Matrix(m, n, matrix);
      return matrix;
    }
    
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        String nonEmptyLine = "";
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            nonEmptyLine = line.replaceAll("\\s+","");
        }
        Matrix matrix = readInput(nonEmptyLine);
        positions(matrix);
    }
}

/*
CHALLENGE DESCRIPTION:

You will be given an M*N matrix. Each item in this matrix is either a '*' or a '.'. A '*' indicates a mine whereas a '.' does not. The objective of the challenge is to output a M*N matrix where each element contains a number (except the positions which actually contain a mine which will remain as '*') which indicates the number of mines adjacent to it. Notice that each position has at most 8 adjacent positions e.g. left, top left, top, top right, right, ...
