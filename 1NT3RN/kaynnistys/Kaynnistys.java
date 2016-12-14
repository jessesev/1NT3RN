package kaynnistys;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import ohjaus.Crash;
import ohjaus.IPALukija;
import ohjaus.KaukoOhjain;
import ohjaus.Koura;
import ohjaus.Liike;
import ohjaus.VariNosto;
import ohjaus.VariSensori;

/**
 * <h1> K‰ynnistys </h1>
 * K‰ynnistys-ohjelma k‰ynnist‰‰ robotin muut ohjelmat.
 * <p>
 * 
 *@author MegaVeli
 *@version 1.0
 *@since 2016-12-13
 *
 */
public class Kaynnistys {
	private IPALukija lukija;
	private Liike moottorit;
	private Koura koura;
	private KaukoOhjain ohjain;
	private EV3IRSensor irSensor;
	private VariSensori variSensori;
	private VariNosto variNosto;
	private Crash bandicoot;


	public Kaynnistys() {
		this.irSensor = new EV3IRSensor(SensorPort.S1);
		this.lukija = new IPALukija(irSensor);
		this.moottorit = new Liike();
		this.koura = new Koura();
		this.bandicoot = new Crash();
		this.ohjain = new KaukoOhjain(lukija, moottorit, koura, bandicoot);
		this.variSensori = new VariSensori();
		this.variNosto = new VariNosto(variSensori, koura, ohjain);
	}

	/**
	 * T‰ll‰ metodilla k‰ynnistet‰‰n kaikki muut ohjelmat.
	 */
	public void aloita() {
		lukija.start();
		variSensori.start();
		variNosto.start();
		bandicoot.start();
		ohjain.kayta();
	}

	/**
	 * T‰m‰ metodi muuttaa muiden luokkien lopetus muuttujat True-arvoon, jolloin ne saadaan sammutettua.
	 */
	public void lopeta() {
		this.lukija.lopeta();
		this.variSensori.lopetus();
		this.variNosto.lopetus();
		this.bandicoot.lopeta();
		this.ohjain.testi();
		this.ohjain.lopeta();
	}
}












