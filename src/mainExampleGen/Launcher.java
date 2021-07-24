package mainExampleGen;

import java.io.IOException;

public class Launcher {

	public static void main(String[] args) throws IOException {
//		String path = "C:/Users/Main/Documents/GoTrainingExamples/ImageComponents";
//		Coordinates kgsStartCoordinates = new Coordinates(17, 19);
//		BoardData kgsBoardData = new BoardData(path, kgsStartCoordinates, 49, "EmptyKgs.png");
//		GoBoardExampleGenerator generator = new GoBoardExampleGenerator(kgsBoardData, "BlackStone.png",
//				"WhiteStone.png", path, path);

//		generator.generateExamples(1);
		
		
		String path = "C:/Users/Main/Documents/GoTrainingExamples/ImageComponents";
		Coordinates kgsStartCoordinates = new Coordinates(21, 9);
		BoardData kgsBoardData = new BoardData(path, kgsStartCoordinates, 147,137 ,"EmptyReal.png");
		GoBoardExampleGenerator generator = new GoBoardExampleGenerator(kgsBoardData, "RealBStone.png",
				"RealWStone.png", path, path);

		generator.generateExamples(10);

	}
}
