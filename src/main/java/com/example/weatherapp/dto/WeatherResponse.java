package com.example.weatherapp.dto;

import com.example.weatherapp.model.Current;
import com.example.weatherapp.model.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Location location;
    private Current current;
}
