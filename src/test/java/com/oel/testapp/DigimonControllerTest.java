package com.oel.testapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.oel.testapp.controller.DigimonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigimonControllerTest {

    @Autowired
    private DigimonController digimonController;

    @Test
    void getAll() {
        assertEquals("all", digimonController.getAll());
    }
}
