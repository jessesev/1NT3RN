package ohjaus;

/**
 * <h1> V‰rinosto </h1>
 * Ohjelma sulkee robotin kouran sek‰ nostaa mukin ilmaan, jos v‰risensori on havainnut vihre‰n v‰rin.
 * VariNosto on Threadin alaluokka, jotta se olisi jatkuvasti p‰‰ll‰.
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-12-13
 *
 */


public class VariNosto extends Thread {

	private Koura koura;
	private VariSensori variSensori;
	private boolean lopetus;
	private KaukoOhjain ohjain;

	public VariNosto(VariSensori seppo, Koura skorpioni, KaukoOhjain ohjain) {

		this.variSensori = seppo;
		this.koura = skorpioni;
		this.lopetus = false;
		this.ohjain = ohjain;
	}
	
	/**
	 * Metodilla run m‰‰ritell‰‰n mit‰ Threadin run komento tekee.
	 * Metodi tarkastaa KaukoOhjain luokan testi boolean arvoa. Arvon ollessa true, alkaa ohjelma lukea v‰risensoria
	 * ja kun v‰risensori on havainnut vihre‰n v‰rin, osaa ohjelma sulkea robotin kourat sek‰ nostaa mukin.
	 */

	public void run() {
		while (!lopetus) {
			ohjain.getTesti();

			if (this.ohjain.getTesti() == true) {
				while (this.ohjain.getTesti() == true) {
					this.variSensori.vari();
					if (this.variSensori.vari() == true) {
						this.koura.kiinni();
						this.ohjain.testi();
						break;
					}
				}
			}
		}
	}
	
	
	/**
	 * Lopetus metodi lopettaa run metodissa m‰‰ritetyn while-loopin pyˆritt‰misen.
	 */

	public void lopetus() {
		this.lopetus = true;
	}
}
