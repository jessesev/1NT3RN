package ohjaus;

import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;
import lejos.utility.Delay;

/**
 * <h1> Kauko-ohjain </h1>
 * KaukoOhjain luokka k‰ytt‰‰ Liike-luokan, Crash-luokan, IPALukija-luokan sek‰ Koura-luokan metodeja hyˆdykseen,
 * jotta kauko-ohjainta voidaan k‰ytt‰‰ robotin ohjaamiseen.
 * <p>
 * KaukoOhjain ohjelma saa IPALukijalta tiedon mit‰ kauko-ohjaimen nappulaa on painettu ja t‰m‰ ohjelma m‰‰rittelee, mit‰ tehd‰‰n
 * kun mit‰kin n‰pp‰int‰ painetaan. Liikkeeseen kuuluvat n‰pp‰imet liikuttavat robottia m‰‰ritetyll‰ tavalla, kunnes napist‰
 * p‰‰stet‰‰n irti, jolloin moottorit pys‰htyv‰t.
 * <p>
 * KaukoOhjain luokalla on 2 omaa boolean muuttujaa, toisella lopetetaan ohjaimen k‰yttˆ ja toisella testataan onko koura auki.
 * Ohjelma myˆs lukee Crash luokkaa, jotta robotin osuessa sein‰‰n tai muuhun esineeseen, se osaa peruuttaa itsekseen pois.
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-12-13
 *
 */

public class KaukoOhjain {
	private IPALukija lukija;
	private Liike moottorit;
	private Koura koura;
	private boolean lopetus;
	private Crash bandicoot;
	private boolean testi;

	public KaukoOhjain(IPALukija ipa, Liike moottorit, Koura koura, Crash crash) {
		this.lukija = ipa;
		this.moottorit = moottorit;
		this.lopetus = false;
		this.koura = koura;
		this.bandicoot = crash;
		this.testi = true;
	}

	/**
	 * Metodi kayta m‰‰ritt‰‰, kuinka kauko-ohjainta k‰ytet‰‰n. IPALukija lukee kahta eri kanavaa, jotta vain 4 perusn‰pp‰int‰
	 * tarvitsee k‰ytt‰‰. K‰ytˆss‰mme on 2 ohjainta, jotka on liitetty yhteen ja ne ovat eri kanavilla.
	 * T‰ll‰ v‰ltet‰‰n vaikeiden n‰pp‰inyhdistelmien tarve, sill‰ niit‰ on hieman hankalampaa k‰ytt‰‰.
	 * <p>
	 * Liikkumiseen tarkoitetut n‰pp‰imet m‰‰ritell‰‰n, sek‰ m‰‰ritell‰‰n ett‰ moottorit ovat muuten pys‰hdyksiss‰, ellei jotain
	 * nappulaa paineta.
	 * Jos jokin nappula on pohjassa, k‰ytet‰‰n sille m‰‰ritetty‰ metodia, siihen asti, ett‰ nappulasta p‰‰stet‰‰n irti.
	 * <p>
	 * Kosketussensorin ollessa pohjassa k‰ytet‰‰n Liike-luokan pakoon metodia.
	 */
	
	public void kayta() {
		while (!lopetus) {
			LCD.drawString("Ohjaa", 2, 6);
			Delay.msDelay(500);
			LCD.clear();
			lukija.getKomento2();
			this.bandicoot.testi();

			if (this.bandicoot.testi() == true) {
				this.moottorit.pakoon();

			}
			if (Button.ESCAPE.isDown()) {
				break;
			}

			if (lukija.getKomento2() != 0) {
				switch (lukija.getKomento2()) {
				case 1:
					while (true) {
						moottorit.oikealle();
						if (this.lukija.testi() == false) {
							moottorit.stop();
							break;
						}
					}
					break;
				case 2:
					while (true) {
						moottorit.kaannyOikea();
						if (this.lukija.testi() == false) {
							moottorit.stop();
							break;
						}
					}
					break;
				case 3:
					koura.kiinni();
					break;

				case 4:
					koura.auki();
					moottorit.taakse();
					Delay.msDelay(2000);
					this.testi = true;
					break;
				}
			}
			switch (lukija.getKomento()) {

			case 1:
				while (true) {
					moottorit.vasemmalle();
					if (this.lukija.testi() == false) {
						moottorit.stop();
						break;
					}
				}
				break;

			case 2:
				while (true) {
					moottorit.kaannyVasen();
					if (this.lukija.testi() == false) {
						moottorit.stop();
						break;
					}

				}
				break;
			case 3:
				while (true) {
					moottorit.eteen();
					if (this.lukija.testi() == false) {
						moottorit.stop();
						break;
					}
				}
				break;
			case 4:
				while (true) {
					moottorit.peruuta();
					if (this.lukija.testi() == false) {
						moottorit.stop();
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * Metodi lopeta muuttaa lopetus booleanin arvoksi true, jolloin kayta metodin while-loop pys‰htyy, sammuttaen t‰m‰n ohjelman.
	 */
	
	public void lopeta() {
		this.lopetus = true;
	}
	
	/**
	 * Metodi testi muuttaa testi booleanin arvoksi false, kertoen ett‰ robotin koura on kiinni.
	 */

	public void testi() {
		this.testi = false;
	}

	/**
	 * Metodi getTesti kertoo onko robotin koura kiinni vai auki.
	 * @return Palauttaa boolean testi arvon joko true tai false.
	 */
	
	public boolean getTesti() {
		return this.testi;
	}
}
