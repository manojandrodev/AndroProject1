package com.example.home.wakemethere;

public class AlarmListDataProvider {
    private String alarm_name_Resource;
    private int id;
    private String name;
    private String latitude;
    private String longitude;
    private String NELatitude;
    private String NELongitude;
    private String SWLatitude;
    private String SWLongitude;
    private String address;
    private boolean isEnabled;
    private boolean isHour;

    public AlarmListDataProvider(String alarm_name_Resource, int id, String name, String latitude, String longitude, String neLatitude, String neLongitude, String swLatitude, String swLongitude, String address, boolean isEnabled, boolean isHour) {
        this.alarm_name_Resource = alarm_name_Resource;
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        NELatitude = neLatitude;
        NELongitude = neLongitude;
        SWLatitude = swLatitude;
        SWLongitude = swLongitude;
        this.address = address;
        this.isEnabled = isEnabled;
        this.isHour = isHour;
    }


    public String getAlarm_name_Resource() {
        return this.alarm_name_Resource;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNELatitude() {
        return NELatitude;
    }

    public String getNELongitude() {
        return NELongitude;
    }

    public String getSWLatitude() {
        return SWLatitude;
    }

    public String getSWLongitude() {
        return SWLongitude;
    }

    public String getAddress() {
        return address;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isHour() {
        return isHour;
    }
}
