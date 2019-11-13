/*	Name: Swapnil Daxini
	ID: V00861672
	Assignment 6
	Program Name: ImageManipulate
	Program Description: This program transforms an image provided by the user in various ways (reflection,rotation,ASCII form,scale) and creates a new image
	in the process
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.lang.Math.*;

public class ImageManipulate {
	/**
	 * The main method uses the args array to determine and call
	 * the correct image manipulation method. Your program should be run
	 * the following way: java ImageManipulate <inputFile> <outputFile> <operation>.
	 * Index 0 of the args array contains the name of the input file, index 1 
	 * the name of the output file, and index 2 the name of the operation.
	 * Possible operation names: invert, scale, mirrorVertical, mirrorHorizontal, 
	 * and rotate.
	 *
	 * @param args The array of Strings 
	 */
	public static void main(String[] args) {
		if(args.length<3){
			System.out.println("Invalid program execution, please pass 3 parameters");
		} else if (args.length==3){
			String inputFileName=args[0];
			String outputFileName=args[1];
			String operation=args[2];
			int [][] arr= readGrayscaleImage(inputFileName);
			
			switch(operation){
				case "invert":
					int [][] result=invert(arr);
					writeGrayscaleImage(outputFileName, result);
					break;
				case "normalMirror":
					int [][] result1=normalMirror(arr);
					writeGrayscaleImage(outputFileName, result1);
					break;
				case "floorMirror":
					int [][] result2=floorMirror(arr);
					writeGrayscaleImage(outputFileName, result2);
					break;
				case "makeAscii":
					makeAscii(arr, outputFileName);
					break;
				case "rotate":
					int[][] result3=rotate(arr);
					writeGrayscaleImage(outputFileName, result3);
					break;
			}
		} else {
			String inputFileName=args[0];
			String outputFileName=args[1];
			String operation=args[2];
			String percentage= args[3];
			double num = Double.parseDouble(percentage); //change string to double
			int [][] arr= readGrayscaleImage(inputFileName);
			
			if(operation.equals("scale")){
				int[][] result=scale(arr, num);
				writeGrayscaleImage(outputFileName, result);
			}
		}
	}	

    /** THIS METHOD MAY BE CALLED, BUT MUST NOT BE MODIFIED!
     * This method reads an image file and returns a 2D array
	 * of integers. Each value in the array is a representation
	 * of the corresponding pixel's grayscale value.
	 *
	 * @param filename The name of the image file
	 * @return A 2D array of integers.
	 */
    public static int[][] readGrayscaleImage(String filename) {
        int[][] result = null;
        try {
            File imageFile = new File(filename);
            BufferedImage image = ImageIO.read(imageFile);
            int height = image.getHeight();
            int width  = image.getWidth();
            result = new int[height][width];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    result[y][x] = rgb & 0xff;
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Problems reading file named " + filename);
            System.err.println("Please ensure the image file is saved in the same directory as your java file.");
            System.exit(1);
        }
        return result;
    }


    /** THIS METHOD MAY BE CALLED, BUT MUST NOT BE MODIFIED!
     * This method reads a 2D array of integers and creates
	 * a grayscale image. Each pixel's grayscale value is
	 * based on the corresponding value in the 2D array.
	 *
	 * @param filename The name of the image file to create
	 * @param array The 2D array of integers
	 */
    public static void writeGrayscaleImage(String filename, int[][] array) {
        int width = array[0].length;
        int height = array.length;

      	try {
            BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
					int rgb = array[y][x];
					rgb |= rgb << 8;
					rgb |= rgb << 16;
					image.setRGB(x, y, rgb);
                }
            }
            File imageFile = new File(filename);
            ImageIO.write(image, "jpg", imageFile);
        }
        catch (IOException ioe) {
            System.err.println("Problems writing file named " + filename);
            System.exit(1);
        }
    }
	
	// This method invert the grayscale value of the each pixel in the image to changes its color
	public static int[][] invert(int[][] arr){
		int[][] result= new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				arr[i][j]= 255-arr[i][j];
			}
		}
		return arr;
	}
	
	//This method reflects the image on a vertical line
	public static int[][] normalMirror (int [][] arr){
		int [][] result= new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[0].length; j++){
				result[i][j]= arr[i][arr[i].length-1-j];
			}
		}
		return result;
	}
	
	//This method reflects the image on a horizontal line
	public static int[][] floorMirror(int[][] arr){
		int [][] result= new int[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[0].length; j++){
				result[i][j]= arr[arr.length-1-i][j];
			}
		}
		return result;
	}
	
	//This method changes the image to ASCII art written in a text file using PrintStream
	public static void makeAscii(int[][] arr, String outname){
		String[][] result=new String[arr.length][arr[0].length];
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				if (arr[i][j]>=0 && arr[i][j]<=20){
					result[i][j]="M";
				} else if (arr[i][j]>=21 && arr[i][j]<=40){
					result[i][j]="L";
				} else if (arr[i][j]>=41 && arr[i][j]<=60){
					result[i][j]="I";
				} else if (arr[i][j]>=61 && arr[i][j]<=80){
					result[i][j]="o";
				} else if (arr[i][j]>=81 && arr[i][j]<=100){
					result[i][j]="|";
				} else if (arr[i][j]>=101 && arr[i][j]<=120){
					result[i][j]="=";
				} else if (arr[i][j]>=121 && arr[i][j]<=140){
					result[i][j]="*";
				} else if (arr[i][j]>=141 && arr[i][j]<=160){
					result[i][j]=":";
				} else if (arr[i][j]>=161 && arr[i][j]<=180){
					result[i][j]="-";
				} else if (arr[i][j]>=181 && arr[i][j]<=200){
					result[i][j]=",";
				} else if (arr[i][j]>=201 && arr[i][j]<=220){
					result[i][j]=".";
				} else
					result[i][j]=" ";
			}
		}
		File outputFile= null;
		PrintStream printer= null;
		try{
			outputFile= new File(outname);
			printer= new PrintStream(outputFile);
		} catch (FileNotFoundException x){
			System.out.println("Error while creating file!");
		}
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				printer.print(result[i][j]);
			}
			printer.println();
		}
	} 
	
	//This method rotates the image 90 degrees clockwise
	public static int[][] rotate(int[][] arr){
		int[][] result= new int[arr[0].length][arr.length];
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				result[j][arr.length-i-1]=arr[i][j];
			}
		}
		return result;
	}
	
	//This method scales the image by a number given by the user
	public static int[][] scale(int[][] arr, double percentage){
		int rL= Math.round((long) (percentage*arr.length)); //variable for new row length
		int cL= Math.round((long) (percentage*arr[0].length)); //variable for new columm length
		int[][] result= new int[rL][cL];
		for(int i=0; i<result.length; i++){
				for(int j=0; j<result[i].length; j++){
				double buffer1=i/(percentage);
				double buffer2=j/(percentage);
				result[i][j]=arr[(int) (buffer1)][(int) (buffer2)];
				}
			}
		return result;
	}	
}






























