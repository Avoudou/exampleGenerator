package mainExampleGen;

import java.io.IOException;

import definitions.BoardData;
import definitions.Coordinates;

public class Launcher {

	public static void main(String[] args) throws IOException {
//		String path = "C:/Users/Main/Documents/GoTrainingExamples/ImageComponents";
//		Coordinates kgsStartCoordinates = new Coordinates(17, 19);
//		BoardData kgsBoardData = new BoardData(path, kgsStartCoordinates, 49, "EmptyKgs.png");
//		GoBoardExampleGenerator generator = new GoBoardExampleGenerator(kgsBoardData, "BlackStone.png",
//				"WhiteStone.png", path, path);

//		generator.generateExamples(1);
		
		
		String path = "C:/Users/Main/Documents/GoTrainingExamples/ImageComponents";
		Coordinates kgsStartCoordinates = new Coordinates(9, 21);
		BoardData realBoardData = new BoardData(path, kgsStartCoordinates, 137,146,"EmptyReal.png");
		realBoardData.setMatchingStoneImageDimention(138);
		GoBoardExampleGenerator generator = new GoBoardExampleGenerator(realBoardData, "RealBStone.png",
				"RealWStone.png", path, path);

		generator.generateExamples(10);

	}
}
