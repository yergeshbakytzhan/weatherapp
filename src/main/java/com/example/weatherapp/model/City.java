package com.example.weatherapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    private Long id;

    private String name;

    private String code;

    public City(String name, String code) {
    }
}
