import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	public NodeComparator(){
		
	}
	
	@Override
	public int compare(Node n, Node n1) {

		if(n.cost - n1.cost > 0){
			return 1;
			
		}else if (n.cost - n1.cost <0) {
			return -1;
		}
		
		return 0;
	}

}
