package ohjaus;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * <h1>Infrapuna-anturin lukija</h1> IPALukija luokka lukee infrapuna-anturia
 * sek‰ kertoo eteenp‰in, onko jotain kauko-ohjaimen nappia painettu. IPALukija
 * on Threadin alaluokka, jotta se olisi jatkuvasti p‰‰ll‰.
 * <p>
 * Luokalla on 2 boolean muuttujaa, toinen jolla ohjelma lopetetaan ja toinen,
 * jolla testataan onko jokin nappula painettuna.
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-13-12
 * 
 */

public class IPALukija extends Thread {
	private EV3IRSensor ipa;
	private int komento;
	private int komento2;
	private boolean lopetus;
	private boolean testi;

	public IPALukija(EV3IRSensor sensor) {
		this.ipa = sensor;
		this.lopetus = false;
		this.testi = false;
	}

	/**
	 * Metodilla run m‰‰ritell‰‰n mit‰ Threadin run komento tekee.
	 * Infrapuna-anturin lukija seuraa kahta kanavaa ja mit‰ nappulaa
	 * kauko-ohjaimella on painettu. Boolean testi saa arvon true jos jokin
	 * nappula on painettuna.
	 */

	public void run() {
		while (!lopetus) {
			this.komento = this.ipa.getRemoteCommand(3);
			this.komento2 = this.ipa.getRemoteCommand(2);
			this.testi = false;

			if (this.komento != 0) {
				this.testi = true;
				LCD.drawString("Painoit: " + this.komento, 2, 2);
				Delay.msDelay(500);
			} else if (this.komento2 != 0) {
				this.testi = true;
			}

		}
	}

	/**
	 * Lopeta metodilla muutetaan booleanin lopetus arvoksi true, jolloin run
	 * metodissa m‰‰ritelty while-loop lakkaa toimimasta, sammuttaen ohjelman.
	 */

	public void lopeta() {
		this.lopetus = true;
	}

	/**
	 * Metodilla getKomento saadaan luettua mit‰ kauko-ohjaimen nappulaa on
	 * painettu. Jokaisella nappulalla on oma Integer arvonsa.
	 * 
	 * @return Palauttaa Integer numeron painetusta painikkeesta.
	 */

	public int getKomento() {
		return this.komento;
	}

	/**
	 * Testi metodilla tarkastetaan onko jokin painike alhaalla. Boolean testi saa arvon true mik‰li jokin nappula on
	 * painettuna. Muuten sen arvo on false.
	 * @return Palauttaa booleanin testi arvon true tai false.
	 */
	
	public boolean testi() {
		return this.testi;
	}

	/**
	 * Metodilla getKomento2 saadaan luettua mit‰ kauko-ohjaimen nappulaa on
	 * painettu kanavalla 3. Jokaisella nappulalla on oma Integer arvonsa.
	 * 
	 * @return Palauttaa Integer numeron painetusta painikkeesta.
	 */
	public int getKomento2() {
		return this.komento2;
	}
}
