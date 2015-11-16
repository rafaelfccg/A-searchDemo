
public class ManhattanDistance extends Heuristica{
	public ManhattanDistance(int x, int y){
		super(x,y);
	}
	
	public double distance(int x, int y){
		return Math.abs(x-destinyX) + Math.abs(y - destinyY);	
	}

}
