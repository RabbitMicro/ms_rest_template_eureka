package com.example.client.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.client.entities.CarDto;
import com.example.client.entities.Client;
import com.example.client.model.ClientResponse;
import com.example.client.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<ClientResponse> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientResponse> clientResponses = clients.stream().map(client -> {
            CarDto[] cars = restTemplate.getForObject("http://SERVICE-CAR/api/car/client/" + client.getId(), CarDto[].class);
            return ClientResponse.builder()
                    .id(client.getId())
                    .nom(client.getNom())
                    .age(client.getAge())
                    .cars(Arrays.asList(cars)) // Convert the array to a list
                    .build();
        }).toList();
        return clientResponses;
    }

    public ClientResponse findById(Long id) throws Exception {
        Client client = clientRepository.findById(id).orElseThrow(() -> new Exception("Invalid Client ID"));
    
        CarDto[] cars = restTemplate.getForObject("http://SERVICE-CAR/api/car/client/" + id, CarDto[].class);
    
        return ClientResponse.builder()
                .id(client.getId())
                .nom(client.getNom())
                .age(client.getAge())
                .cars(Arrays.asList(cars)) // Convert the array to a list
                .build();
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }
}
