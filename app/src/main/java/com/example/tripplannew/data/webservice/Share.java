package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Share {
    @Json(name = "idTrip")
    private String idTrip;
    @Json(name = "idUser")
    private String idUser;
    @Json(name = "userName")
    private String userName;

    public  Share(String idTrip, String idUser, String userName){
        this.idTrip = idTrip;
        this.idUser = idUser;
        this.userName = userName;
    }


    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
