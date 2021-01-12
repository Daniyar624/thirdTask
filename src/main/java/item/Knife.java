package item;

import java.util.Objects;

public class Knife {
    private int id;
    private String name;
    private String type;
    private String handy;
    private String origin;
    private VisualParameters visualParameters;
    private String value;

    public Knife(){

    }

    public Knife(int id, String name, String type, String handy, String origin, VisualParameters visualParameters, String value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.handy = handy;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.value = value;
    }

    public Knife(VisualParameters visualParameters){
        this.visualParameters = visualParameters;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "SteelArms{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", handy='" + handy + '\'' +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knife knife = (Knife) o;
        return id == knife.id &&
                Objects.equals(name, knife.name) &&
                Objects.equals(type, knife.type) &&
                Objects.equals(handy, knife.handy) &&
                Objects.equals(origin, knife.origin) &&
                Objects.equals(visualParameters, knife.visualParameters) &&
                Objects.equals(value, knife.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, handy, origin, visualParameters, value);
    }
}
