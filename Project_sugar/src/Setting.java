public class Setting implements Runnable {

	public Thread t;
	boolean suspended = false;
	boolean start = false;

	public void run() {
		while (start) {
			try {
				synchronized (this) {
					while (suspended) {
						wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {

		if (t == null) {
			t = new Thread(this);
			t.start();
		}
		start = true;
	}

	void suspend() {
		suspended = true;
	}

	synchronized void resume() {
		suspended = false;
		notify();
	}
}