package com.example.client.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String matricule;
    private Long client_id;
}
