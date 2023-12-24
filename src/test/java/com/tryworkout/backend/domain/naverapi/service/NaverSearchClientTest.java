package com.tryworkout.backend.domain.naverapi.service;

import com.tryworkout.backend.domain.naverapi.dto.SearchLocalReq;
import com.tryworkout.backend.domain.naverapi.dto.SearchLocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NaverSearchClientTest {

    @Autowired
    NaverSearchClient naverSearchClient;

    @Test
    void searchLocalTest(){
        SearchLocalReq searchLocalReq = SearchLocalReq.builder()
                .query("신영빌 신동")
                .display(5)
                .start(5)
                .sort("random")
                .build();

        SearchLocalRes searchLocalRes = naverSearchClient.searchLocal(searchLocalReq);

        assertEquals(1, searchLocalRes.getItems().size());
        assertEquals("경기도 수원시 영통구 신동 355", searchLocalRes.getItems().get(0).getAddress());
        assertEquals("경기도 수원시 영통구 영통로 261", searchLocalRes.getItems().get(0).getRoadAddress());
    }
}