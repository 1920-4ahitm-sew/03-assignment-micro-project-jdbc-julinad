package at.htl.firedept.model;

public class Firetruck {
    private String type;
    private String licensePlate;
    private String numSeats;


    public Firetruck() {
    }

    public Firetruck(String type, String licensePlate, String numSeats) {
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

    public String getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(String numSeats) {
        this.numSeats = numSeats;
    }
}
