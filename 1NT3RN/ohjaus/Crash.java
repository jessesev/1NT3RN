package ohjaus;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

/**
 * <h1> Crash! </h1>
 * Crash luokka lukee kosketusanturia ja kertoo omilla boolean muuttujilla mik‰li robotti on osunut sein‰‰n
 * tai muuhun esteeseen. Crash luokka on Threadin alaluokka, jotta se voisi olla jatkuvasti p‰‰ll‰.
 * 
 * @author MegaVeli
 * @version 1.0
 * @since 2016-12-13
 */

public class Crash extends Thread{
    
    private EV3TouchSensor ts;
    private boolean testi;
    private boolean lopetus;
    
    public Crash() {
   	 this.ts = new EV3TouchSensor(SensorPort.S4);
   	 this.testi = false;
   	 this.lopetus = false;
    }
    
    /**
     * Metodilla run m‰‰ritell‰‰n mit‰ Threadin run metodi tekee. Metodin ollessa p‰‰ll‰, ker‰t‰‰n listaan
     * tietoja siit‰, onko koskestusanturi pohjassa vai ei. Kun kosketusanturi on pohjassa, boolean testi saa arvon true
     * kertoakseen ett‰ johonkin on nyt osuttu.
     */
    
    public void run() {


    	float[] sample = new float[ts.sampleSize()];
    	
    	while (!lopetus)  {
        	this.ts.fetchSample(sample, 0);
        	this.testi = false;
        	if (sample[0] == 1) {
            	this.testi = true;
           	 
            	LCD.clear();
        	}
    	}
	}
    
    /**
     * Metodilla testi tarkastetaan onko kosketusanturi pohjassa. Mik‰li on, voidaan siit‰ kertoa muille luokille.
     * @return Palauttaa booleanin testi arvon true tai false riippuen siit‰ onko kosketusanturi pohjassa vai ei.
     */
    
    public boolean testi() {
   	 return this.testi;
    }
    
    /**
     * Metodilla lopeta lopetetaan run-metodissa m‰‰ritelty while-loop. T‰ll‰ sammutetaan koko ohjelma.
     */
    
    public void lopeta() {
    	this.lopetus = true;
    }
}




