package com.randomsturvs.collaboux;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class YoutubeTest {


    private static final String ACCESS_TOKEN = "646";
    @Test
    public void validateAccessToken(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(String.format("https://www.googleapis.com/youtube/v3?access_token=%s",ACCESS_TOKEN), YoutubeTest.class );
    }
}