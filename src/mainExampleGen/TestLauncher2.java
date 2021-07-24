package mainExampleGen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestLauncher2 {

	public static void main(String[] args) throws IOException {
		String path = "C:/Users/Main/Documents/GoTrainingExamples/ImageComponents";
		Coordinates kgsStartCoordinates = new Coordinates(15, 55);
		BoardData kgsBoardData = new BoardData(path, kgsStartCoordinates, 30,30, "EmptyKgs.png");
		BufferedImage boardImage = ImageIO.read(new File(path, "EmptyKgs.png"));

		BufferedImage blackStoneImage = ImageIO.read(new File(path, "BlackStone.png"));

		BufferedImage combined = new BufferedImage(boardImage.getWidth(), boardImage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		Graphics g = combined.getGraphics();

		g.drawImage(boardImage, 0, 0, null);

		g.drawImage(blackStoneImage, 100, 100, null);
		
		ImageIO.write(combined, "PNG", new File(path, "example" + ".png"));
	}

}
