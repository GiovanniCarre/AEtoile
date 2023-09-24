import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	public void paintComponent(Graphics g) {
		Main.mouseX = MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x;
		Main.mouseY = MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y;

		for (int x = 0; x < Main.Sx; x++) {
			for (int y = 0; y < Main.Sy; y++) {
				if (Main.map[x][y]) {
					g.setColor(Color.black);
				} else
					g.setColor(Color.LIGHT_GRAY);
				g.fillRect(getWidth() / Main.Sx * x, getHeight() / Main.Sy * y, getWidth() / Main.Sx,
						getHeight() / Main.Sy);
				g.setColor(Color.BLACK);
				g.drawRect(getWidth() / Main.Sx * x, getHeight() / Main.Sy * y, getWidth() / Main.Sx,
						getHeight() / Main.Sy);
			}
		}

		g.setColor(Color.red);
		g.fillOval(getWidth() / Main.Sx * Main.PosXP, getHeight() / Main.Sy * Main.PosYP, getWidth() / Main.Sx,
				getHeight() / Main.Sy);
		g.setColor(Color.green);
		g.fillOval(getWidth() / Main.Sx * Main.FX, getHeight() / Main.Sy * Main.FY, getWidth() / Main.Sx,
				getHeight() / Main.Sy);
		/*
		 * for (int i = 0; i < Main.closelist.size(); i++) {
		 * 
		 * g.setColor(Color.BLACK);
		 * g.drawString(String.valueOf(Main.closelist.get(i).Quality),
		 * Main.closelist.get(i).x * getWidth() / Main.Sx, Main.closelist.get(i).y *
		 * getHeight() / Main.Sy + (getHeight() / Main.Sy / 2)); g.setColor(Color.red);
		 * g.fillOval(getWidth() / Main.Sx * Main.closelist.get(i).x, getHeight() /
		 * Main.Sy * Main.closelist.get(i).y, getWidth() / Main.Sx, getHeight() /
		 * Main.Sy); } for (int i = 0; i < Main.openlist.size(); i++) {
		 * 
		 * g.setColor(Color.green); g.fillRect(getWidth() / Main.Sx *
		 * Main.openlist.get(i).x, getHeight() / Main.Sy * Main.openlist.get(i).y,
		 * getWidth() / Main.Sx, getHeight() / Main.Sy);
		 * 
		 * }
		 */
		for (int i = 0; i < Main.pathView.size(); i++) {

			g.setColor(Color.blue);
			g.fillRect(getWidth() / Main.Sx * Main.pathView.get(i)[0], getHeight() / Main.Sy * Main.pathView.get(i)[1],
					getWidth() / Main.Sx, getHeight() / Main.Sy);

		}
	}

}
