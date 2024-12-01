package com.example.weatherapp.dto;

import com.example.weatherapp.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
    private Long id;
    private String name;
    private String code;

    public static CityResponse toDto(City city){
        return new CityResponse(city.getId(), city.getName(), city.getCode());
    }
}
