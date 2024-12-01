package com.example.weatherapp.controller;

import com.example.weatherapp.dto.WeatherResponse;
import com.example.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping("/city/{id}")
    public ResponseEntity<Mono<WeatherResponse>> getWeather(@RequestParam Long id){
        return ResponseEntity.ok(weatherService.getWeather(id));
    }
}
