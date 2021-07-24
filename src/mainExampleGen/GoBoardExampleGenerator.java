package mainExampleGen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GoBoardExampleGenerator {

	private BoardData boardData;

	private String stonePath;
	private String blackStoneImageName;
	private String whiteStoneImageName;
	private String savePath;

	private double haveStoneProbability = 0.5;

	public GoBoardExampleGenerator(BoardData boardData, String blackStoneImageName, String whiteStoneImageName,
			String stonePath, String savePath) {
		super();
		this.boardData = boardData;
		this.blackStoneImageName = blackStoneImageName;
		this.whiteStoneImageName = whiteStoneImageName;
		this.stonePath = stonePath;
		this.savePath = savePath;
	}

	public void generateExamples(int numberOfExamples) throws IOException {

		File stoneCoordinatesData = new File(savePath + "stoneCoordinates.txt");
		FileWriter myWriter = new FileWriter(savePath + "stoneCoordinates.txt");

		for (int i = 0; i < numberOfExamples; i++) {
			System.out.println("example count " + i);
			// load source image for board
			BufferedImage boardImage = ImageIO.read(new File(boardData.getImagePath(), boardData.getImageName()));

			BufferedImage blackStoneImage = ImageIO.read(new File(stonePath, blackStoneImageName));
			BufferedImage whiteStoneImage = ImageIO.read(new File(stonePath, whiteStoneImageName));

			Image resizeB = blackStoneImage.getScaledInstance(138, 138, Image.SCALE_SMOOTH);
			blackStoneImage = ImageProcessingUtils.convertToBufferedImage(resizeB);

			Image resizeW = whiteStoneImage.getScaledInstance(138, 138, Image.SCALE_SMOOTH);
			whiteStoneImage = ImageProcessingUtils.convertToBufferedImage(resizeW);

			// example image initialization
			BufferedImage combined = new BufferedImage(boardImage.getWidth(), boardImage.getHeight(),
					BufferedImage.TYPE_INT_ARGB);

			Graphics g = combined.getGraphics();

			g.drawImage(boardImage, 0, 0, null);

			BoardRepresentation randomBoardRep = generateRandomBoardRepresentation();

			for (int k = 0; k < randomBoardRep.getBoard().length; k++) {
				for (int j = 0; j < randomBoardRep.getBoard().length; j++) {
					// System.out.println(" attempting to add stone to image");
					if (randomBoardRep.getBoard()[k][j] != 0) {
						// System.out.println("adding stone to image");
						BufferedImage currentStoneImage = (randomBoardRep.getBoard()[k][j] == 1) ? blackStoneImage
								: whiteStoneImage;
						// BufferedImage currentStoneImage = blackStoneImage;
						g.drawImage(currentStoneImage,
								boardData.getStartCoordinates().getX() + ((boardData.getDistanceUnitX() * k)),
								boardData.getStartCoordinates().getY() + (boardData.getDistanceUnitY() * j), null);

					}

				}

			}
			Image newResizedImage = combined.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			BufferedImage result = ImageProcessingUtils.convertToBufferedImage(newResizedImage);
			g.dispose();

			ImageIO.write(combined, "PNG", new File(savePath, "example" + i + ".png"));
			ImageIO.write(result, "PNG", new File(savePath, "example" + i + "Sized.png"));
		}
	}

	public BoardRepresentation generateRandomBoardRepresentation() {

		BoardRepresentation boardRep = new BoardRepresentation();

		int colorIndicator = 2;
		for (int i = 0; i < boardRep.getBoard().length; i++) {
			for (int j = 0; j < boardRep.getBoard().length; j++) {

				if (Math.random() <= haveStoneProbability) {

					boardRep.getBoard()[i][j] = (colorIndicator % 2) + 1;

					colorIndicator++;

				}

			}
		}

		return boardRep;
	}

}
