package ohjaus;

import lejos.robotics.Color;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

/**
 * <h1> V‰risensori </h1>
 * Ohjelma k‰ynnist‰‰ v‰risensorin, jonka avulla k‰ytet‰‰n V‰riNosto luokkaa.
 * V‰risensori on Thread ohjelman alaluokka, jotta se voisi olla jatkuvasti p‰‰ll‰.
 * <p>
 * V‰risensorin havaitessa vihre‰n v‰rin, sen boolean arvo "testi" muuttuu true arvolle.
 * T‰ll‰ voidaan kertoa V‰riNosto luokalle, koska sit‰ voidaan k‰ytt‰‰.
 * 
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-12-13
 * 
 */


public class VariSensori extends Thread {

	private EV3ColorSensor cs;
	private boolean lopetus;
	private boolean vari;

	public VariSensori() {
		this.cs = new EV3ColorSensor(SensorPort.S2);
		this.lopetus = false;
		this.vari = false;
	}
	
	/**
	 * Metodilla run m‰‰ritell‰‰n mit‰ Threadin run komento tekee.
	 * Boolean vari saa arvon true, jos v‰risensori havaitsee vihre‰n v‰rin. Muuten arvo on false.
	 */

	public void run() {

		while (!lopetus) {
			this.cs.getColorID();
			this.vari = false;
			if (this.cs.getColorID() == Color.GREEN) {
				this.vari = true;

			}
		}

	}
	
	/**
	 * Metodi lopetus lopettaa run-metodissa m‰‰ritellyn while-loopin pyˆritt‰misen.
	 */

	public void lopetus() {
		this.lopetus = true;

	}

	/**
	 * T‰ll‰ metodilla tarkistetaan onko v‰risensorin havaitsema v‰ri vihre‰.
	 * @return palauttaa boolean vari arvon true tai false.
	 */
	
	public boolean vari() {
		return this.vari;
	}
}
