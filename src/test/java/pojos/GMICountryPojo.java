package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMICountryPojo {
    private int id;
    private String name;
    private Object states;

    public GMICountryPojo() {
    }

    public GMICountryPojo(int id, String name, Object states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getStates() {
        return states;
    }

    public void setStates(Object states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "GMICountryPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states=" + states +
                '}';
    }

}
