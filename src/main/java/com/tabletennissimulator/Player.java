package com.tabletennissimulator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * An abstract class giving a set of universal details about a player
 */
public abstract class Player {

    @Expose
    protected int id;

    /**
     * The player's first name
     */
    @Expose
    @SerializedName("first_name")
    public String firstName;

    /**
     * The player's last name
     */
    @Expose
    @SerializedName("last_name")
    public String lastName;

    /**
     * Gets formatted information about this player
     * @return Formatted information about this player
     */
    public abstract String getPlayerInformation();
}
