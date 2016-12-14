package ohjaus;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * <h1>Koura!
 * </h1> 
 * Koura luokka m‰‰ritt‰‰, kuinka robotin 2 keskikokoista moottoria k‰ytet‰‰n.
 * <p>
 * Robotin on tarkoitus pysty‰ ottamaan kahvikupista kiinni ja nostaa se ilmaan
 * pienen viiveen j‰lkeen. Robotin pit‰‰ myˆs pysty‰ laskemaan kahvikuppi, sek‰
 * pienen viiveen j‰lkeen p‰‰st‰‰ siit‰ irti.
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-12-13
 * 
 */

public class Koura {

	private RegulatedMotor moottoriC;
	private RegulatedMotor moottoriB;

	public Koura() {
		moottoriB = new EV3MediumRegulatedMotor(MotorPort.B);
		moottoriC = new EV3MediumRegulatedMotor(MotorPort.C);

	}

	/**
	 * Metodilla kiinni m‰‰ritell‰‰n kuinka moottorit pyˆriv‰t, kun kahvikupista halutaan ottaa kiinni ja nostaa se ilmaan.
	 * <p>
	 * Ensin kouraa liikuttava moottori k‰‰ntyy 90 astetta taaksep‰in, ottaen kahvikupista kiinni.
	 * <p>
	 * Sekunnin viiveen j‰lkeen nostosta vastaava moottori pyˆrii myˆs 90 astetta taaksep‰in, nostaen kupin ilmaan.
	 */
	
	public void kiinni() {
		moottoriC.rotate(-90);
		Delay.msDelay(1000);
		moottoriB.rotate(-90);
	}

	/**
	 * Metodilla auki m‰‰ritell‰‰n kuinka moottorit pyˆriv‰t, kun kahvikuppi halutaan laskea alas ja p‰‰st‰‰ siit‰ irti.
	 * <p>
	 * Ensin nostosta vastaavaa moottoria pyˆritet‰‰n 90 astetta eteenp‰in, laskien kupin alas.
	 * <p>
	 * Sekunnin viiveen j‰lkeen kouraa liikuttava moottorit pyˆrii 90 astetta eteenp‰in, p‰‰st‰en kupista irti.
	 */
	
	public void auki() {
		moottoriB.rotate(90);
		Delay.msDelay(1000);
		moottoriC.rotate(60);

	}

}
