package ohjaus;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * <h1>Liike</h1> 
 * Liike luokka määrittelee, millä tavalla liikkeeseen
 * käytettäviä moottoreita, eli renkaita, käytetään. 
 * <p>
 * Tämän ohjelman metodeja
 * käytetään KaukoOhjain ohjelmalla robotin liikuttamiseen.
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-12-13
 * 
 */

public class Liike {

	private RegulatedMotor moottoriA;
	private RegulatedMotor moottoriD;

	public Liike() {
		moottoriA = new EV3LargeRegulatedMotor(MotorPort.A);
		moottoriD = new EV3LargeRegulatedMotor(MotorPort.D);
		moottoriA.synchronizeWith(new RegulatedMotor[] { moottoriD });
	}

	/**
	 * Metodi eteen määrittelee, millä tavalla moottorit toimivat kun robottia
	 * halutaan liikuttaa eteenpäin. Molempia moottoreita pyöritetään eteenpäin.
	 */

	public void eteen() {
		this.moottoriD.forward();
		this.moottoriA.forward();

	}

	/**
	 * Metodi peruuta määrittelee, millä tavalla moottorit toimivat kun robottia
	 * halutaan liikuttaa taaksepäin. Molempia moottoreita pyöritetään
	 * taaksepäin.
	 */

	public void peruuta() {
		this.moottoriD.backward();
		this.moottoriA.backward();
	}

	/**
	 * Metodi kaannyVasen määrittelee, millä tavalla moottorit toimivat kun
	 * robottia halutaan kääntää vasemmalle paikallaan. Vasemmalla oleva
	 * moottori pyörii taaksepäin ja oikea moottori pyörii eteenpäin.
	 */

	public void kaannyVasen() {
		this.moottoriD.forward();
		this.moottoriA.backward();
	}

	/**
	 * Metodi kaannyOikea määrittelee, millä tavalla moottorit toimivat kun
	 * robottia halutaan kääntää oikealle paikallaan. Vasemmalla oleva moottori
	 * pyörii eteenpäin ja oikea moottori pyörii taaksepäin.
	 */

	public void kaannyOikea() {
		this.moottoriA.forward();
		this.moottoriD.backward();
	}

	/**
	 * Metodi vasemmalle määrittelee, millä tavalla moottorit toimivat kun
	 * robottia halutaan kääntää väsemmalle. Oikeanpuoleinen moottori pyörii
	 * eteenpäin ja vasemmanpuoleinen moottori ei pyöri ollenkaan.
	 */

	public void vasemmalle() {
		this.moottoriD.forward();
		this.moottoriA.stop();
	}

	/**
	 * Metodi oikealle määrittelee, millä tavalla moottorit toimivat kun
	 * robottia halutaan kääntää oikealle. Vasemmanpuoleinen moottori pyörii
	 * eteenpäin ja oikeanpuoleinen moottori ei pyöri ollenkaan.
	 */

	public void oikealle() {
		this.moottoriD.stop();
		this.moottoriA.forward();
	}

	/**
	 * Metodi stop pysäyttää molemmat moottorit.
	 */

	public void stop() {
		this.moottoriA.startSynchronization();
		this.moottoriA.stop();
		this.moottoriD.stop();
		this.moottoriA.endSynchronization();
	}

	/**
	 * Metodi taakse on robotin peruuttamista varten. Sitä käytetään samalla,
	 * kun robotin koura avataan, jotta värisensori ei heti voi havaita vihreää
	 * väriä ja sulkea kouraa uudestaan.
	 */

	public void taakse() {
		this.moottoriA.startSynchronization();
		this.moottoriA.rotate(-400);
		this.moottoriD.rotate(-400);
		this.moottoriA.endSynchronization();
	}

	/**
	 * Metodi pakoon määrittelee, mitä tapahtuu kun kosketussensori on pohjassa,
	 * jolloin robotti on luultavasti ajettu seinään tai muuhun esteeseen.
	 * Moottorit laitetaan pyörimään tavallista nopeammin, 800 astetta/sekuntti,
	 * jonka jälkeen niitä pyöritetään synkronoidusti taaksepäin 720 astetta.
	 * <p>
	 * Tämän jälkeen moottoreiden nopeus palautetaan takaisin normaaliin
	 * pyörimisnopeuteen, 360 astetta/sekuntti.
	 */

	public void pakoon() {
		moottoriA.setSpeed(800);
		moottoriD.setSpeed(800);
		moottoriA.startSynchronization();
		moottoriA.rotate(-720);
		moottoriD.rotate(-720);
		moottoriA.endSynchronization();
		moottoriA.setSpeed(360);
		moottoriD.setSpeed(360);

	}

}
