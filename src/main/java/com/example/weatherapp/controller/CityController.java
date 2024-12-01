package com.example.weatherapp.controller;

import com.example.weatherapp.dto.CityRequest;
import com.example.weatherapp.dto.CityResponse;
import com.example.weatherapp.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<Flux<CityResponse>> getAll(){
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<CityResponse>> getById(@RequestParam Long id){
        return ResponseEntity.ok(cityService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<CityResponse>> save(@Valid @RequestBody CityRequest cityRequest){
        return ResponseEntity.ok(cityService.save(cityRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<CityResponse>> update(@RequestParam Long id, @Valid @RequestBody CityRequest cityRequest){
        return ResponseEntity.ok(cityService.update(id, cityRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> delete(@RequestParam Long id){
        return ResponseEntity.ok(cityService.delete(id));
    }
}
