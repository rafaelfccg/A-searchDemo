import java.util.Comparator;
import java.util.PriorityQueue;

public class BuscaClasse {
	
	int size;
	int[][] espaco;
	int weight;
	
	int startX;
	int startY;
	
	int destinyX;
	int destinyY;
	
	Heuristica h;
	//ClasseInterface delegate
	
	BuscaClasse (int[][] espaco) {
		
		
	}
	
	void paint(){
		//delegate.paint(espaco);
	}
	
	
	public void aEstrela(){
		
		Comparator<Node> comp = new NodeComparator();
		PriorityQueue<Node> fronteira = new PriorityQueue<Node>(11, comp);
		
		
	}

}
