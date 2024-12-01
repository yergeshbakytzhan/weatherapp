package com.example.weatherapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Current {
    private double tempC;
    private double windMph;
    private String condition;
}
