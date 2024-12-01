package com.example.weatherapp.service.impl;

import com.example.weatherapp.dto.WeatherResponse;
import com.example.weatherapp.exception.WeatherApiException;
import com.example.weatherapp.repository.CityRepository;
import com.example.weatherapp.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final CityRepository cityRepository;

    private final WebClient.Builder webClientBuilder;

    private final ObjectMapper objectMapper;

    @Override
    @Cacheable(value = "weather", key = "#cityName")
    public Mono<WeatherResponse> getWeather(Long id) {

        var city = cityRepository.findById(id);


        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("key", apiKey)
                .queryParam("q", city.block().getName())
                .toUriString();

        return webClientBuilder.baseUrl(url)
                .build()
                .get()
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(errorResponse -> {
                            String errorMessage = "Error calling Weather API: " + clientResponse.statusCode();
                            log.error(errorMessage);
                            return Mono.error(new WeatherApiException(errorMessage));
                        }))
                .bodyToMono(String.class)
                .map(this::mapToWeatherResponse)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error("WebClient error: " + e.getMessage(), e);
                    return Mono.error(new WeatherApiException("WebClient error: " + e.getMessage(), e));
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("General error: " + e.getMessage(), e);
                    return Mono.error(new WeatherApiException("General error: " + e.getMessage(), e));
                });
    }

    private WeatherResponse mapToWeatherResponse(String json) {
        try {
            return objectMapper.readValue(json, WeatherResponse.class);
        } catch (Exception e) {
            log.error("Failed to map JSON to WeatherResponse", e);
            throw new RuntimeException("Failed to map JSON to WeatherResponse", e);
        }
    }
}
