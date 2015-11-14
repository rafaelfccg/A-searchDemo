
public class EuclidianDistance extends Heuristica {
	
	public EuclidianDistance(int x, int y){
		super(x,y);
	}
	
	public double distance(int x, int y){
		return Math.sqrt((x - destinyX)*(x - destinyX) + (y - destinyY)*(y - destinyY));
		
	}

}
