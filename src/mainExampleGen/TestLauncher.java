package mainExampleGen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestLauncher {
	
	public static void main(String[] args) throws IOException {
		String path = "C:/Users/Main/Documents/GoTrainingExamples/ImageComponents";

		// load source images
		BufferedImage image = ImageIO.read(new File(path, "EmptyReal.png"));
		BufferedImage overlay = ImageIO.read(new File(path, "RealWStone.png"));
		
		
		Image resizeW = overlay.getScaledInstance(138, 138, Image.SCALE_SMOOTH);
		overlay = ImageProcessingUtils.convertToBufferedImage(resizeW);


		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 21, 9, null);
//		g.drawImage(overlay, 70, 55, null);
//		g.drawImage(overlay, 125, 55, null);

		g.dispose();

//		Image newResizedImage = combined.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
//		BufferedImage result = ImageProcessingUtils.convertToBufferedImage(newResizedImage);

		ImageIO.write(combined, "PNG", new File(path, "result.png"));

	}

}
