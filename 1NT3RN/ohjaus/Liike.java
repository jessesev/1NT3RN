package ohjaus;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * <h1>Liike</h1> 
 * Liike luokka m��rittelee, mill� tavalla liikkeeseen
 * k�ytett�vi� moottoreita, eli renkaita, k�ytet��n. 
 * <p>
 * T�m�n ohjelman metodeja
 * k�ytet��n KaukoOhjain ohjelmalla robotin liikuttamiseen.
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
	 * Metodi eteen m��rittelee, mill� tavalla moottorit toimivat kun robottia
	 * halutaan liikuttaa eteenp�in. Molempia moottoreita py�ritet��n eteenp�in.
	 */

	public void eteen() {
		this.moottoriD.forward();
		this.moottoriA.forward();

	}

	/**
	 * Metodi peruuta m��rittelee, mill� tavalla moottorit toimivat kun robottia
	 * halutaan liikuttaa taaksep�in. Molempia moottoreita py�ritet��n
	 * taaksep�in.
	 */

	public void peruuta() {
		this.moottoriD.backward();
		this.moottoriA.backward();
	}

	/**
	 * Metodi kaannyVasen m��rittelee, mill� tavalla moottorit toimivat kun
	 * robottia halutaan k��nt�� vasemmalle paikallaan. Vasemmalla oleva
	 * moottori py�rii taaksep�in ja oikea moottori py�rii eteenp�in.
	 */

	public void kaannyVasen() {
		this.moottoriD.forward();
		this.moottoriA.backward();
	}

	/**
	 * Metodi kaannyOikea m��rittelee, mill� tavalla moottorit toimivat kun
	 * robottia halutaan k��nt�� oikealle paikallaan. Vasemmalla oleva moottori
	 * py�rii eteenp�in ja oikea moottori py�rii taaksep�in.
	 */

	public void kaannyOikea() {
		this.moottoriA.forward();
		this.moottoriD.backward();
	}

	/**
	 * Metodi vasemmalle m��rittelee, mill� tavalla moottorit toimivat kun
	 * robottia halutaan k��nt�� v�semmalle. Oikeanpuoleinen moottori py�rii
	 * eteenp�in ja vasemmanpuoleinen moottori ei py�ri ollenkaan.
	 */

	public void vasemmalle() {
		this.moottoriD.forward();
		this.moottoriA.stop();
	}

	/**
	 * Metodi oikealle m��rittelee, mill� tavalla moottorit toimivat kun
	 * robottia halutaan k��nt�� oikealle. Vasemmanpuoleinen moottori py�rii
	 * eteenp�in ja oikeanpuoleinen moottori ei py�ri ollenkaan.
	 */

	public void oikealle() {
		this.moottoriD.stop();
		this.moottoriA.forward();
	}

	/**
	 * Metodi stop pys�ytt�� molemmat moottorit.
	 */

	public void stop() {
		this.moottoriA.startSynchronization();
		this.moottoriA.stop();
		this.moottoriD.stop();
		this.moottoriA.endSynchronization();
	}

	/**
	 * Metodi taakse on robotin peruuttamista varten. Sit� k�ytet��n samalla,
	 * kun robotin koura avataan, jotta v�risensori ei heti voi havaita vihre��
	 * v�ri� ja sulkea kouraa uudestaan.
	 */

	public void taakse() {
		this.moottoriA.startSynchronization();
		this.moottoriA.rotate(-400);
		this.moottoriD.rotate(-400);
		this.moottoriA.endSynchronization();
	}

	/**
	 * Metodi pakoon m��rittelee, mit� tapahtuu kun kosketussensori on pohjassa,
	 * jolloin robotti on luultavasti ajettu sein��n tai muuhun esteeseen.
	 * Moottorit laitetaan py�rim��n tavallista nopeammin, 800 astetta/sekuntti,
	 * jonka j�lkeen niit� py�ritet��n synkronoidusti taaksep�in 720 astetta.
	 * <p>
	 * T�m�n j�lkeen moottoreiden nopeus palautetaan takaisin normaaliin
	 * py�rimisnopeuteen, 360 astetta/sekuntti.
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
