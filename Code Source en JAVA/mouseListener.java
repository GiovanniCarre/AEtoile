import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseListener implements MouseListener {

	public static boolean leftclick = false, rightclick = false;

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		}

	public void mousePressed(MouseEvent arg0) {
		
		if (arg0.getButton() == 1) {
			leftclick = true;
		}
		if (arg0.getButton() == 3) {
			rightclick = true;
		}

	}

	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getButton() == 1) {
			leftclick = false;
		}
		if (arg0.getButton() == 1) {
			rightclick = false;
		}
	
	}

}
