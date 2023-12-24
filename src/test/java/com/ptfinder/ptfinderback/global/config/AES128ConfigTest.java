package com.ptfinder.ptfinderback.global.config;

import com.tryworkout.backend.global.config.AES128Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AES128ConfigTest {

    @Autowired
    AES128Config aes128Config;

    @Test
    @DisplayName("Aes128 암호화 테스트")
    void aes128Test(){
        String text = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0LmVtYWlsMUBkb21haW4uY29tIiwiaWF0IjoxNzAwODI1OTY3LCJleHAiOjE3MDEwODUxNjd9.g_wDHYy8C6gRVFjvVQGIBH4GHNXP-lymxqWmhaT5HpQ3xLTDnU92NiIR-M9IEnY154jY2Yj_gJ76dxfsQ2X-vw";
        String enc = aes128Config.encryptAes(text);
        String dec = aes128Config.decryptAes(enc);
        System.out.println("enc = " + enc);
        System.out.println("dec = " + dec);

        assertThat(dec).isEqualTo(text);
    }

}