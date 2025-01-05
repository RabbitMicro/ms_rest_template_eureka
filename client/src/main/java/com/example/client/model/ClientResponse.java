package com.example.client.model;

import java.util.List;

import com.example.client.entities.CarDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private Long id;
    private String nom;
    private Float age;
    private List<CarDto> cars;
}
