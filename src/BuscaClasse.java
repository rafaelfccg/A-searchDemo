import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BuscaClasse {
	
	int size;
	int[][] espaco;
	int weight;
	
	Node start;
	
	Node destiny;
	
	Heuristica h;
	
	projetoSI paintDelegate;
	
	final int[] dx		= {1,1,1,0,-1,-1,-1,0};
	final int[] dy		= {1,0,-1,-1,-1,0,1,1};
	final double[] dc 	= {1.4,1,1.4,1,1.4,1,1.4,1};
	
	BuscaClasse (int[][] espaco) {
		this.espaco = espaco;
		
	}
	
	void paintCaminho(Node p){
		paintDelegate.b[p.x][p.y].setBackground(Color.green); ;
	}
	void paintExplorado(Node p){
		paintDelegate.b[p.x][p.y].setBackground(Color.darkGray); ;
	}
	void paintFronteira(Node p){
		paintDelegate.b[p.x][p.y].setBackground(Color.blue); ;
	}
	
	public void aEstrela(double weight){
		
		Comparator<Node> comp = new NodeComparator();
		PriorityQueue<Node> fronteira = new PriorityQueue<Node>(11, comp);
		start.cost = weight*h.distance(start.x, start.y);
		fronteira.add(start);
		int size = espaco.length;
		Node finished = new Node();
		while(!fronteira.isEmpty()){
			Node top = fronteira.poll();
			if(top.x == destiny.x && top.y==destiny.y){
				finished = top;
				//end
				break;
			}
			paintExplorado(top);
			for(int i =0; i<8;i++){
				int nx = top.x +dx[i];
				int ny = top.y +dy[i];
				if( nx< size && nx > 0 &&
					ny < size && ny > 0 &&
					espaco[nx][ny] != 3){
					Node newNode = new Node();
					newNode.x = nx;
					newNode.y = ny;
					newNode.realCost =  top.realCost + dc[i];
					newNode.cost = newNode.realCost + weight*h.distance(nx, ny);
					newNode.parent = top;
					fronteira.add(newNode);
					paintFronteira(newNode);
				}
			}
		}
		//finished = finished.parent;
		while(finished.x != start.x || finished.y != start.y){
			paintCaminho(finished);
			finished = finished.parent;			
		}
		paintCaminho(finished);
		
	}

}
