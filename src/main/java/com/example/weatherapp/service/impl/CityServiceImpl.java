package com.example.weatherapp.service.impl;

import com.example.weatherapp.dto.CityRequest;
import com.example.weatherapp.dto.CityResponse;
import com.example.weatherapp.exception.NotFoundException;
import com.example.weatherapp.repository.CityRepository;
import com.example.weatherapp.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;


    @Override
    public Flux<CityResponse> getAll() {
        return cityRepository.findAll().map(CityResponse::toDto);
    }

    @Override
    public Mono<CityResponse> getById(Long id) {
        return cityRepository.findById(id)
                .map(CityResponse::toDto)
                .switchIfEmpty(Mono.error(()->{
                    log.error("City not found with id: "+id);
                    return new NotFoundException("City not found with id: "+id);
                }));
    }

    @Override
    public Mono<CityResponse> save(CityRequest cityRequest) {
        return cityRepository.save(CityRequest.toModel(cityRequest)).map(CityResponse::toDto);
    }

    @Override
    public Mono<CityResponse> update(Long id, CityRequest cityRequest) {
        return cityRepository.findById(id)
                .flatMap(city->{
                    city.setName(cityRequest.getName());
                    city.setCode(cityRequest.getCode());

                    return cityRepository.save(city).map(CityResponse::toDto);
                })
                .switchIfEmpty(Mono.error(()->{
                    log.error("City not found with id: "+id);
                    return new NotFoundException("City not found with id: "+id);
                }));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return cityRepository.deleteById(id);
    }
}
