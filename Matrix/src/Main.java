import java.io.*;
public class Main {
    private static class Matrix{
        int xAxis;
        int yAxis;
        char[][] matrix;
        Matrix(int xAxis, int yAxis, char[][]matrix){
            this.xAxis = xAxis;
            this.yAxis = yAxis;
            this.matrix = matrix;
        }
    }

    /*Print out the new M*N matrix (in row major form) with each position(except the ones with the mines) indicating how many adjacent mines are there. E.g.

    **100332001*100
    *10022101*101110
    */

    private static void positions(Matrix matrix){
        StringBuilder answer = new StringBuilder();
        for(int y = 0; y < matrix.yAxis; y++){
            for (int x = 0; x < matrix.xAxis; x++){
                if(matrix.matrix[x][y] == '*'){
                    answer.append('*');
                    continue;
                }
                int minesCount = 0;
                int left = x - 1;
                int right = x + 1;
                //Left
                if(x > 0){
                    if (matrix.matrix[left][y] == '*'){
                        minesCount++;
                    }
                }else {
                    left = 0;
                }
                //Right
                if (x < (matrix.xAxis-1)){
                    if (matrix.matrix[right][y] == '*'){
                        minesCount++;
                    }
                }else{
                    right = matrix.xAxis - 1;
                }

                //Top
                if (y < matrix.yAxis-1){
                    for (int ind = left; ind < right+1; ind++) {
                        if (matrix.matrix[ind][y+1] == '*'){
                            minesCount++;
                        }
                    }
                }

                //Bottom
                if (y-1 >= 0){
                    for (int ind = left; ind < right+1; ind++) {
                        if (matrix.matrix[ind][y-1] == '*'){
                            minesCount++;
                        }
                    }
                }
                answer.append(minesCount);
            }
        }
        System.out.println(answer);
    }

    /*
    Your program should accept as its first argument a path to a filename. Each line in this file contains M,N, a semicolon and the M*N matrix in row major form. E.g.

    3,5;**.........*...
    4,4;*........*......
    */
    private static Matrix readInput(String input){
        String nm = input.split(";")[0];
        String symbols = input.split(";")[1];
        int yAxis = Integer.parseInt(nm.split(",")[0]);
        int xAxis = Integer.parseInt(nm.split(",")[1]);
        char[][] matrix = new char[xAxis][yAxis];
        int ind = 0;
        for (int i = 0; i < yAxis; i++) {
            for (int j = 0; j < xAxis; j++) {
                matrix[j][i] = symbols.charAt(ind);
                ind++;
            }
        }
        return new Matrix(xAxis, yAxis, matrix);
    }
    public static void main (String[] args) throws IOException {
        //File file = new File(args[0]);
        //BufferedReader buffer = new BufferedReader(new FileReader(file));
        //String line;
        //String nonEmptyLine;
        /*while ((line = buffer.readLine()) != null) {
            line = line.trim();
            nonEmptyLine = line.replaceAll("\\s+","");
            Matrix matrix = readInput(nonEmptyLine);
            positions(matrix);
        }*/

        //System.out.println(nonEmptyLine);
        String line = "3,5;**.........*...";
        line = line.trim();
        String nonEmptyLine = line.replaceAll("\\s+","");
        Matrix matrix = readInput(nonEmptyLine);
        positions(matrix);
        line = "4,4;*........*......";
        line = line.trim();
        nonEmptyLine = line.replaceAll("\\s+","");
        matrix = readInput(nonEmptyLine);
        positions(matrix);
    }
}