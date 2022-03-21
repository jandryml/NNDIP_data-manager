package cz.edu.upce.fei.datamanager.views.dashboard.legacy;

/**
 * Simple DTO class for the inbox list to demonstrate complex object data
 */
public class ServiceHealthLegacy {

    private Status status;

    private String city;

    private int input;

    private int output;

    private String theme;

    enum Status {
        EXCELLENT, OK, FAILING;
    }

    public ServiceHealthLegacy() {

    }

    public ServiceHealthLegacy(Status status, String city, int input, int output) {
        this.status = status;
        this.city = city;
        this.input = input;
        this.output = output;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

}
