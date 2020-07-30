package com.tabletennissimulator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class Player {

    @Expose
    protected int id;

    @Expose
    @SerializedName("first_name")
    public String firstName;

    @Expose
    @SerializedName("last_name")
    public String lastName;

    public abstract String getPlayerInformation();
}
