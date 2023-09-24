import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main implements Runnable {
	static int Sx = 15, Sy = 10, mouseX = 0, mouseY = 0;
	static boolean map[][];
	static Panel p = new Panel();
	static ArrayList<Node> openlist = new ArrayList<>();
	static ArrayList<Node> closelist = new ArrayList<>();
	static int PathViewController = 0;
	static int PosXP = 1, PosYP = 5, FX = 14, FY = 4;
	static ArrayList<Integer[]> path = new ArrayList<>();
	static ArrayList<Integer[]> pathView = new ArrayList<>();// animation blue
	static JFrame f = new JFrame();

	public static void main(String[] args) {

		lireFichierConfig();
		map = new boolean[Sx][Sy];

		f.setVisible(true);
		f.addMouseListener(new mouseListener());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);

		f.addKeyListener(new keyListener());
		f.setContentPane(p);
		for (int x = 0; x < Sx; x++) {
			for (int y = 0; y < Sy; y++) {
				Random r = new Random();
				if (r.nextInt(5) == 2)
					map[x][y] = true;
				else
					map[x][y] = false;

			}
		}

		Trame();

	}

	private static void lireFichierConfig() {
		String nomFichier = "config.txt"; // Remplacez ceci par le chemin de votre fichier

		try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
			String ligne;

			ligne = br.readLine();
			ligne = br.readLine();

			PosXP = Integer.parseInt(ligne);

			ligne = br.readLine();
			ligne = br.readLine();

			PosYP = Integer.parseInt(ligne);

			ligne = br.readLine();
			ligne = br.readLine();

			FX = Integer.parseInt(ligne);

			ligne = br.readLine();
			ligne = br.readLine();

			FY = Integer.parseInt(ligne);

			ligne = br.readLine();
			ligne = br.readLine();

			Sx = Integer.parseInt(ligne);

			ligne = br.readLine();
			ligne = br.readLine();

			Sy = Integer.parseInt(ligne);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Le fichier config.txt n'existe pas.");
			e.printStackTrace();
		}
	}

	private static void Trame() {
		new Thread(new Calcul()).start();
		new Thread(new Main()).start();
		while (true) {
			if (mouseListener.leftclick) {
				try {
					map[mouseX / (f.getContentPane().getWidth() / Sx)][mouseY
							/ (f.getContentPane().getHeight() / Sy)] = true;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			if (mouseListener.rightclick) {
				try {
					map[mouseX / (f.getContentPane().getWidth() / Sx)][mouseY
							/ (f.getContentPane().getHeight() / Sy)] = false;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.repaint();
		}
	}

	public static void CheminCourt() {
		closelist.clear();
		openlist.clear();
		path.clear();
		pathView.clear();

		PathViewController = 0;

		if (map[FX][FY])
			map[FX][FY] = false;
		if (map[PosXP][PosYP])
			map[PosXP][PosYP] = false;
		long debut = System.currentTimeMillis();
		Node node[][] = new Node[Sx][Sy];

		for (int x = 0; x < Sx; x++) {
			for (int y = 0; y < Sy; y++) {
				if (!map[x][y]) {
					node[x][y] = new Node(x, y, -5, -5);// -1 = no parents

				}
			}
		}

		openlist.add(new Node(PosXP, PosYP, -5, -5));
		while (openlist.size() > 0) {

			Node n = NodeLowQuality(openlist);
			if (n.x > 0) {
				if (!map[n.x - 1][n.y]) {
					if (!openlist.contains(node[n.x - 1][n.y]) && !closelist.contains(node[n.x - 1][n.y])) {
						InitializeNode(n, node[n.x - 1][n.y]);
						openlist.add(node[n.x - 1][n.y]);

					}
				}

			}
			if (n.x < Sx - 1) {
				if (!map[n.x + 1][n.y]) {
					if (!openlist.contains(node[n.x + 1][n.y]) && !closelist.contains(node[n.x + 1][n.y])) {
						InitializeNode(n, node[n.x + 1][n.y]);
						openlist.add(node[n.x + 1][n.y]);

					}
				}

			}
			if (n.y > 0) {
				if (!map[n.x][n.y - 1]) {
					if (!openlist.contains(node[n.x][n.y - 1]) && !closelist.contains(node[n.x][n.y - 1])) {
						InitializeNode(n, node[n.x][n.y - 1]);
						openlist.add(node[n.x][n.y - 1]);

					}
				}

			}
			if (n.y < Sy - 1) {
				if (!map[n.x][n.y + 1]) {
					if (!openlist.contains(node[n.x][n.y + 1]) && !closelist.contains(node[n.x][n.y + 1])) {
						InitializeNode(n, node[n.x][n.y + 1]);
						openlist.add(node[n.x][n.y + 1]);

					}
				}

			}
			if (n.x == FX && n.y == FY) {

				while (!(n.x == PosXP) || !(n.y == PosYP)) {
					Integer[] tab = { Integer.valueOf(n.x), Integer.valueOf(n.y) };
					path.add(tab);
					n = node[n.ParentsX][n.ParentsY];
				}
				Collections.reverse(path);

				break;
			}

			openlist.remove(n);
			closelist.add(n);

		}

		long tpsTotal = System.currentTimeMillis() - debut;
		// System.out.println("Temps : " + tpsTotal + " ms");

	}

	public void run() {
		CheminCourt();

	}

	private static Node NodeLowQuality(ArrayList<Node> openlist2) {
		int Vi = 0;
		float vmin = 1000;
		for (int i = 0; i < openlist2.size(); i++) {
			if (openlist2.get(i).Quality < vmin) {
				vmin = openlist.get(i).Quality;
				Vi = i;
			}
		}
		return openlist2.get(Vi);
	}

	private static void InitializeNode(Node parent, Node child) {

		child.ParentsX = parent.x;
		child.ParentsY = parent.y;

		child.Quality = Math.abs(child.x - FX + (child.y - FY));

	}

}
