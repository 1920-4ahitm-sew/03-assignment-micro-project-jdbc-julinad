package at.htl.firedept.model;

public class Firetruck {
    private String type;
    private String licensePlate;
    private int numSeats;


    public Firetruck() {
    }

    public Firetruck(String type, String licensePlate, int numSeats) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.numSeats = numSeats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }
}
