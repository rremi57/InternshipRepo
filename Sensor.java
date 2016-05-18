
import java.util.*;

/**
 * 
 */
public class Sensor extends System {

    /**
     * Default constructor
     */
    public Sensor() {
    }

    /**
     * 
     */
    public String TEMP_SENSOR_ADDR[ ];

    /**
     * 
     */
    public String WEIGHT_SENSOR_ADDR[ ];

    /**
     * 
     */
    public String PAYMENT_HW_ADDR[ ];

    /**
     * 
     */
    public String ACCELEROMETER_SENSOR_ADDR[];



    /**
     * @param int 
     * @return
     */
    public double getData(void int) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public abstract void reorder();

    /**
     * @return
     */
    public abstract void errorMessage();

    /**
     * @param String String 
     * @return
     */
    public abstract boolean validateLogin(void String String);

    /**
     * @param String String 
     * @return
     */
    public abstract void setLoginDetails(void String String);

    /**
     * @return
     */
    public abstract void listenConnect();

    /**
     * @return
     */
    public abstract double getConfig();

    /**
     * @param String 
     * @return
     */
    public abstract void setConfig(void String);

}