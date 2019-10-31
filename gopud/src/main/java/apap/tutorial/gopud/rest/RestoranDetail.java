package apap.tutorial.gopud.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestoranDetail {
    private String status;

    @JsonProperty("restaurant-license")
    private Integer restaurantLicense;

    @JsonProperty("valid-until")
    private Date validUntil;

    public Date getValidUntil(){ return validUntil;}

    public void setValidUntil(Date validUntil) { this.validUntil = validUntil;}

    public Integer getRestaurantLicense(Integer restaurantLicense){ return restaurantLicense;}

    public void setRestaurantLicense(Integer restaurantLicense){
        this.restaurantLicense = restaurantLicense;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() { return status;}
}
