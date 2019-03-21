
package algo.com.fono.data;

public class Device {

    private int _id;
    private String deviceName;
    private String brand;
    private String technology;
    private String gprs;
    private String edge;
    private String announced;
    private String status;
    private String dimensions;
    private String weight;
    private String sim;
    private String type;
    private String size;
    private String resolution;
    private String displayC;
    private String cardSlot;
    private String alertTypes;
    private String loudspeaker;
    private String soundC;
    private String wlan;
    private String bluetooth;
    private String gps;
    private String radio;
    private String usb;
    private String messaging;
    private String browser;
    private String featuresC;
    private String batteryC;
    private String colors;
    private String sensors;
    private String cpu;
    private String internal;
    private String os;
    private String speed;
    private String networkC;
    private String chipset;
    private String features;
    private String gpu;
    private String multitouch;
    private String price;
    private String single;
    private String _2gBands;
    private String _35mmJack;
    private String _3gBands;
    private String _4gBands;

    public Device(String deviceName, String brand, String technology, int id) {
        this.deviceName = deviceName;
        this.brand = brand;
        this.technology = technology;
        this._id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getBrand() {
        return brand;
    }

    public Device(int i) {
        this._id = i;
    }

    public int getId() {
        return _id;
    }
}