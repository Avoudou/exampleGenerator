package mainExampleGen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import utils.RandomCollection;

public class TestLauncher2 {

	public static void main(String[] args) throws IOException {
//
//		File stoneCoordinatesData = new File("C:/Users/Main/Documents/GoTrainingExamples/ImageComponents", "stoneCoordinates.txt");
//		 //System.out.println(stoneCoordinatesData.createNewFile());
//		FileWriter myWriter = new FileWriter(stoneCoordinatesData.getPath());
//		myWriter.write("line1");
//		myWriter.write(System.getProperty( "line.separator" ));
//		myWriter.write("line 2");
//		myWriter.close();
		
		//System.out.println(Random.nextGaussian());
		

		
		int numberOfStones = ThreadLocalRandom.current().nextInt(50,
				100 + 1);
		System.out.println(numberOfStones);
}
	
	public static void printArray(int[][] array) {
		for(int i = 0; i<array.length; i++)
		{
		    for(int j = 0; j<array[0].length; j++)
		    {
		        System.out.print(array[i][j]);
		        System.out.print(" ");
		    }
		    System.out.println();
		}
	}

}
