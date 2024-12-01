package com.example.weatherapp.dto;

import com.example.weatherapp.model.City;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Code cannot be blank")
    private String code;

    public static City toModel(CityRequest cityRequest){
        return new City(cityRequest.name, cityRequest.code);
    }
}
