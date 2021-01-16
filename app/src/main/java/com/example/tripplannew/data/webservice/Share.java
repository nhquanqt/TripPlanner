package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Share {
    @Json(name = "idTrip")
    private String idTrip;
    @Json(name = "idUser")
    private String idUser;

    public  Share(String idTrip, String idUser){
        this.idTrip = idTrip;
        this.idUser = idUser;
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
}
