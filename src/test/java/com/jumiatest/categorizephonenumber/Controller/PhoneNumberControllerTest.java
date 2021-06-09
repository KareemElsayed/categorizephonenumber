package com.jumiatest.categorizephonenumber.Controller;

import com.jumiatest.categorizephonenumber.Service.CustomerService;
import com.jumiatest.categorizephonenumber.domain.CountryDetail;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import com.jumiatest.categorizephonenumber.exception.CustomizeErrorExceptionHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PhoneNumberControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    PhoneNumberController phoneNumberController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(phoneNumberController)
                .setControllerAdvice(new CustomizeErrorExceptionHandler())
                .build();
    }

    @Test
    void getAll() throws Exception {
        //given
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setName("Customer1");
        customerDetail.setNumber("(212) 847651504");
        customerDetail.setState(customerDetail.validatePhoneNumber(customerDetail.getNumber()));
        customerDetail.setCountryCode(CountryDetail.getCode(customerDetail.getNumber()));
        customerDetail.setCountryName(CountryDetail.getCountryName(customerDetail.getNumber()));

        CustomerDetail customerDetail2 = new CustomerDetail();
        customerDetail2.setName("Customer2");
        customerDetail2.setNumber("(212) 63746283");
        customerDetail2.setState(customerDetail.validatePhoneNumber(customerDetail2.getNumber()));
        customerDetail2.setCountryCode(CountryDetail.getCode(customerDetail2.getNumber()));
        customerDetail2.setCountryName(CountryDetail.getCountryName(customerDetail2.getNumber()));


        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customerDetail, customerDetail2));

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAllByCountry() throws Exception {

        //given
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setName("Customer1");
        customerDetail.setNumber("(212) 847651504");
        customerDetail.setState(customerDetail.validatePhoneNumber(customerDetail.getNumber()));
        customerDetail.setCountryCode(CountryDetail.getCode(customerDetail.getNumber()));
        customerDetail.setCountryName(CountryDetail.getCountryName(customerDetail.getNumber()));

        when(customerService.getAllNumbersByCountry("Morocco")).thenReturn(Arrays.asList(customerDetail));

        mockMvc.perform(get("/customers/Morocco")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..countryName").value("Morocco"));

    }

    @Test
    void getAllByState() throws Exception {

        //given
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setName("Customer1");
        customerDetail.setNumber("(212) 847651504");
        customerDetail.setState(customerDetail.validatePhoneNumber(customerDetail.getNumber()));
        customerDetail.setCountryCode(CountryDetail.getCode(customerDetail.getNumber()));
        customerDetail.setCountryName(CountryDetail.getCountryName(customerDetail.getNumber()));

        when(customerService.getAllNumbersByState("VALID")).thenReturn(Arrays.asList(customerDetail));

        mockMvc.perform(get("/customer/VALID")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..state").value("VALID"));
    }

    @Test
    void getAllByCountryAndState() {
    }

    @Test
    void getAllPaged() {
    }
}