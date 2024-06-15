package com.example.carrentalexam.controllers;

import com.example.carrentalexam.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarsController.class)
public class CarsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    void createNewCarAction_Success() throws Exception {
        doNothing().when(carService).createNewCar(anyString(), anyString(), anyString(), anyInt(), anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/createNewCarAction")
                        .param("frameNumber", "XYZ123")
                        .param("brand", "Toyota")
                        .param("model", "Corolla")
                        .param("monthlyPrice", "299")
                        .param("registrationNumber", "AB12345")
                        .param("status", "AVAILABLE")
                        .param("employeeUserId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/createNewCar?employeeUserId=1&message=Car+has+been+created"));

        verify(carService).createNewCar("XYZ123", "Toyota", "Corolla", 299, "AB12345", "AVAILABLE");
    }
    @Test
    void createNewCarAction_Exception() throws Exception {
        doThrow(new RuntimeException("Creation failed")).when(carService).createNewCar(anyString(), anyString(), anyString(), anyInt(), anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/createNewCarAction")
                        .param("frameNumber", "XYZ123")
                        .param("brand", "Toyota")
                        .param("model", "Corolla")
                        .param("monthlyPrice", "299")
                        .param("registrationNumber", "AB12345")
                        .param("status", "AVAILABLE")
                        .param("employeeUserId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/createNewCar?employeeUserId=1&message=Something+went+wrong.+Please+try+agian."));

        verify(carService).createNewCar("XYZ123", "Toyota", "Corolla", 299, "AB12345", "AVAILABLE");
    }

}