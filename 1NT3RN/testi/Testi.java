package testi;

import kaynnistys.Kaynnistys;
import lejos.hardware.Button;

public class Testi {

	public static void main(String[] args) {
		Kaynnistys kaynnistys = new Kaynnistys();

		kaynnistys.aloita();
		if (Button.ESCAPE.isDown()) {
			kaynnistys.lopeta();
		}

	}
}
