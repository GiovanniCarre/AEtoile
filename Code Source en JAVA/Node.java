
public class Node {
	public int x,y;
	float Quality = 0;
	public int ParentsX = -5, ParentsY= - 5;
	
	public Node(int x,int y, int pX, int pY) {
		this.y = y;
		this.x = x;
		this.ParentsX = pX;
		this.ParentsY = pY;
	}
	
	

}
