package com.example.weatherapp.service;

import com.example.weatherapp.dto.WeatherResponse;
import reactor.core.publisher.Mono;

public interface WeatherService {
    Mono<WeatherResponse> getWeather(Long id);
}
