package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMICountryPostResponsePojo {

    public String name;
    public ArrayList<GMIStatePojo> states;

    public GMICountryPostResponsePojo() {
    }

    public GMICountryPostResponsePojo(String name, ArrayList<GMIStatePojo> states) {
        this.name = name;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GMIStatePojo> getStates() {
        return states;
    }

    public void setStates(ArrayList<GMIStatePojo> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "GMICountryPostResponsePojo{" +
                "name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
