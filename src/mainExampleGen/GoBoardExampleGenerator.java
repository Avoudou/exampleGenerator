package mainExampleGen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import definitions.BoardData;
import definitions.BoardRepresentation;
import definitions.Range;
import utils.ImageProcessingUtils;
import utils.RandomCollection;

public class GoBoardExampleGenerator {

	//private GoBoardExampleGeneratorData data = new GoBoardExampleGeneratorData();
	
	private BoardData boardData;
	private String stonePath;
	private String blackStoneImageName;
	private String whiteStoneImageName;
	private String savePath;
	
	private final int trainingImageDimention = 80;
	private final double haveStoneProbability = 0.5;
	private final RandomCollection<Range> rangeCollection = createWeightedRanges();

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

		File stoneCoordinatesData = new File(savePath, "stoneCoordinates.txt");
		FileWriter myWriter = new FileWriter(stoneCoordinatesData.getPath());

		for (int i = 0; i < numberOfExamples; i++) {
			System.out.println("example count " + i);

			// load source image for board
			BufferedImage boardImage = ImageIO.read(new File(boardData.getImagePath(), boardData.getImageName()));
			int stoneImageDim = boardData.getMatchingStoneImageDimention();
			BufferedImage blackStoneImage = ImageProcessingUtils
					.resizeImage(ImageIO.read(new File(stonePath, blackStoneImageName)), stoneImageDim, stoneImageDim);
			BufferedImage whiteStoneImage = ImageProcessingUtils
					.resizeImage(ImageIO.read(new File(stonePath,whiteStoneImageName)), stoneImageDim, stoneImageDim);

			// example image initialization
			BufferedImage combined = new BufferedImage(boardImage.getWidth(), boardImage.getHeight(),
					BufferedImage.TYPE_INT_ARGB);

			Graphics g = combined.getGraphics();

			g.drawImage(boardImage, 0, 0, null);

			// BoardRepresentation randomBoardRep = generateRandomBoardRepresentation();
			BoardRepresentation randomBoardRep = generateWeightedRandomBoardRepresentation();
			saveExampleCoordinates(randomBoardRep, myWriter);

			for (int k = 0; k < randomBoardRep.getBoard().length; k++) {
				for (int j = 0; j < randomBoardRep.getBoard().length; j++) {
					if (randomBoardRep.getBoard()[k][j] != 0) {
						BufferedImage currentStoneImage = (randomBoardRep.getBoard()[k][j] == 1) ? blackStoneImage
								: whiteStoneImage;
						g.drawImage(currentStoneImage,
								boardData.getStartCoordinates().getX() + ((boardData.getDistanceUnitX() * j)),
								boardData.getStartCoordinates().getY() + (boardData.getDistanceUnitY() * k), null);

					}

				}

			}
			Image newResizedImage = combined.getScaledInstance(trainingImageDimention, trainingImageDimention,
					Image.SCALE_SMOOTH);
			BufferedImage result = ImageProcessingUtils.convertToBufferedImage(newResizedImage);
			g.dispose();

			ImageIO.write(combined, "PNG", new File(savePath, "example" + i + ".png"));
			ImageIO.write(result, "PNG", new File(savePath, "example" + i + "Sized.png"));

		}
		myWriter.close();
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

	public BoardRepresentation generateWeightedRandomBoardRepresentation() {

		BoardRepresentation boardRep = new BoardRepresentation();

		Range noOfStonesRange = rangeCollection.next();
		int numberOfStones = ThreadLocalRandom.current().nextInt(noOfStonesRange.getMinValue(),
				noOfStonesRange.getMaxValue() + 1);
		int colorIndicator = 2;

		while (numberOfStones > 0) {

			int x = (int) (Math.random() * boardRep.getBoard()[0].length);
			int y = (int) (Math.random() * boardRep.getBoard().length);
			if (boardRep.getBoard()[y][x] == 0) {
				boardRep.getBoard()[y][x] = (colorIndicator % 2) + 1;
				colorIndicator++;
				numberOfStones--;
			}
		}

		return boardRep;
	}

	public void saveExampleCoordinates(BoardRepresentation boardRep, FileWriter myWriter) throws IOException {
		for (int i = 0; i < boardRep.getBoard().length; i++) {
			for (int j = 0; j < boardRep.getBoard().length; j++) {
				myWriter.write("" + boardRep.getBoard()[i][j]);

			}
		}
		myWriter.write(System.getProperty("line.separator"));
	}

	private RandomCollection<Range> createWeightedRanges() {
		Range lowRange = new Range(1, 20);
		Range midRange = new Range(21, 130);
		Range highRange = new Range(131, 230);

		RandomCollection<Range> rangeCollection = new RandomCollection<>();
		rangeCollection.add(10, lowRange).add(45, midRange).add(45, highRange);

		return rangeCollection;
	}

}
