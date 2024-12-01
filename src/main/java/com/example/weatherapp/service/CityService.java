package com.example.weatherapp.service;

import com.example.weatherapp.dto.CityRequest;
import com.example.weatherapp.dto.CityResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {

    Flux<CityResponse> getAll();
    Mono<CityResponse> getById(Long id);
    Mono<CityResponse> save(CityRequest cityRequest);
    Mono<CityResponse> update(Long id, CityRequest cityRequest);
    Mono<Void> delete(Long id);
}
