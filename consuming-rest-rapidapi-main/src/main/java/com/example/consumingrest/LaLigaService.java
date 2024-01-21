package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LaLigaService {

    @Autowired
    RestTemplate restTemplate;

    protected LaLigaResponse[] getLaLigaResponse() {
        /** si necessitem afegir patrams:
         *  String url = "https://laliga-standings.p.rapidapi.com/";
         *
         *         UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
         *                 .queryParam("param1", "valor1")
         *                 .queryParam("param2", "valor2");
         *
         *         LaLigaResponse data[] = restTemplate.getForObject(
         *                 builder.toUriString(), LaLigaResponse[].class);
         */

        LaLigaResponse data[] = restTemplate.getForObject(
                "https://laliga-standings.p.rapidapi.com/", LaLigaResponse[].class);

        return data;
    }
}
