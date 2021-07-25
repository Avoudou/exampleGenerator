package definitions;

public class BoardData {

	private Coordinates startCoordinates;

	private int distanceUnitX;
	private int distanceUnitY;
	private int matchingStoneImageDimention;
	public int getMatchingStoneImageDimention() {
		return matchingStoneImageDimention;
	}

	public void setMatchingStoneImageDimention(int matchingStoneImageDimention) {
		this.matchingStoneImageDimention = matchingStoneImageDimention;
	}

	private String imagePath;
	private String imageName;

	public BoardData(Coordinates startCoordinates, int distanceUnitX, int distanceUnitY,
			int matchingStoneImageDimention, String imagePath, String imageName) {
		super();
		this.startCoordinates = startCoordinates;
		this.distanceUnitX = distanceUnitX;
		this.distanceUnitY = distanceUnitY;
		this.matchingStoneImageDimention = matchingStoneImageDimention;
		this.imagePath = imagePath;
		this.imageName = imageName;
	}

	public BoardData(String imagePath, Coordinates startCoordinates, int distanceUnitX,int distanceUnitY,String imageName) {
		super();
		this.imagePath = imagePath;
		this.startCoordinates = startCoordinates;
		this.distanceUnitX = distanceUnitX;
		this.distanceUnitY= distanceUnitY;
		this.imageName= imageName;
		this.matchingStoneImageDimention=distanceUnitX;
	}

	public int getDistanceUnitY() {
		return distanceUnitY;
	}

	public String getImagePath() {
		return imagePath;
	}

	public Coordinates getStartCoordinates() {
		return startCoordinates;
	}

	public int getDistanceUnitX() {
		return distanceUnitX;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
