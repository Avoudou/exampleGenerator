package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessingUtils {
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	public static BufferedImage convertToBufferedImage(Image img) {

		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics2D = bi.createGraphics();
		graphics2D.drawImage(img, 0, 0, null);
		graphics2D.dispose();

		return bi;
	}

	public static BufferedImage resizeImage(BufferedImage image, int width, int height) throws IOException {
		Image resizeB = image.getScaledInstance(138, 138, Image.SCALE_SMOOTH);
		image = ImageProcessingUtils.convertToBufferedImage(resizeB);
		return image;
	}

}
