package srent.senai.com.srent.models;

import java.util.Date;

public class VehicleRequest {

    private Long id;
    private VehicleType vehicleType;
    private Vehicle vehicle;
    private Date startDate;
    private Date endDate;
    private Integer passengerAmount;
    private String originAddress;
    private String destinyAddress;
    private Boolean includesDriver;

    public VehicleRequest() {
        // Standard Constructor
    }

    public VehicleRequest(Long id, VehicleType vehicleType, Vehicle vehicle, Date startDate, Date endDate, Integer passengerAmount, String originAddress, String destinyAddress, Boolean includesDriver) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.passengerAmount = passengerAmount;
        this.originAddress = originAddress;
        this.destinyAddress = destinyAddress;
        this.includesDriver = includesDriver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(Integer passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestinyAddress() {
        return destinyAddress;
    }

    public void setDestinyAddress(String destinyAddress) {
        this.destinyAddress = destinyAddress;
    }

    public Boolean getIncludesDriver() {
        return includesDriver;
    }

    public void setIncludesDriver(Boolean includesDriver) {
        this.includesDriver = includesDriver;
    }

    public Double calculatePrice() {
        if(getVehicle() == null)
            return 0d;
        //
        Long diff = Math.abs(endDate.getTime() - startDate.getTime());
        Long totalSeconds = diff / 1000;
        Long totalMinutes = totalSeconds / 60;
        Long totalHours = totalMinutes / 60;
        Long totalDays = totalHours / 24;
        //
        Double passengerMultiplier = 1d;
        passengerMultiplier += (getPassengerAmount() / 10d);
        //
        Double driverMultiplier = getIncludesDriver() ? 1.2 : 1;
        //
        return getVehicle().getPrice() * (totalDays + 1) * passengerMultiplier * driverMultiplier;
    }

}
