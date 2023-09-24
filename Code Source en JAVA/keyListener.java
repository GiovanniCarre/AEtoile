

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListener implements KeyListener{

	public void keyPressed(KeyEvent e) {
		if (e.getExtendedKeyCode() == 90)
			Main.CheminCourt();
		else if (e.getExtendedKeyCode() == 69) {
			Main.pathView.clear();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
