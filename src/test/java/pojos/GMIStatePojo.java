package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GMIStatePojo {

    private int id;
    private String name;
    private Object tpcountry;

    public GMIStatePojo() {
    }

    public GMIStatePojo(int id, String name, Object tpcountry) {
        this.id = id;
        this.name = name;
        this.tpcountry = tpcountry;
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

    public Object getTpcountry() {
        return tpcountry;
    }

    public void setTpcountry(Object tpcountry) {
        this.tpcountry = tpcountry;
    }

    @Override
    public String toString() {
        return "GMIStatePojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tpcountry=" + tpcountry +
                '}';
    }
}
