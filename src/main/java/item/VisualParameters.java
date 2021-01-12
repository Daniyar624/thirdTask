package item;

import java.util.Objects;

public class VisualParameters {
    private int length;
    private int width;
    private String material;
    private String handle;
    private boolean bloodstreamPresence;

    public VisualParameters(){

    }

    public VisualParameters(int length, int width, String material, String handle, boolean bloodstreamPresence) {
        this.length = length;
        this.width = width;
        this.material = material;
        this.handle = handle;
        this.bloodstreamPresence = bloodstreamPresence;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public boolean isBloodstreamPresence() {
        return bloodstreamPresence;
    }

    public void setBloodstreamPresence(boolean bloodstreamPresence) {
        this.bloodstreamPresence = bloodstreamPresence;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "length=" + length +
                ", width=" + width +
                ", material='" + material + '\'' +
                ", handle='" + handle + '\'' +
                ", bloodstreamPresence=" + bloodstreamPresence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return length == that.length &&
                width == that.width &&
                bloodstreamPresence == that.bloodstreamPresence &&
                Objects.equals(material, that.material) &&
                Objects.equals(handle, that.handle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, material, handle, bloodstreamPresence);
    }
}
