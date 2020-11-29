package item;

public class VisualParameters {
    private int length;
    private int width;
    private String material;
    private String handle;
    private boolean bloodstreamPresence;

    public VisualParameters(){

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



}
