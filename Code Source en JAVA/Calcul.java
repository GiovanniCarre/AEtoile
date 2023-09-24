
public class Calcul implements Runnable {

	public void run() {
		while (true) {
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Main.PathViewController < Main.path.size() - 1) {
				Main.pathView.add(Main.path.get(Main.PathViewController));
				Main.PathViewController++;
			}

		}
	}

}
