package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MLSService {

    @Autowired
    RestTemplate restTemplate;

    protected MLSResponse[] getMLSResponse() {

        MLSResponse data[] = restTemplate.getForObject(
                "https://major-league-soccer-standings.p.rapidapi.com/", MLSResponse[].class);

        return data;
    }
}
