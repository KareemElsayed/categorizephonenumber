package com.jumiatest.categorizephonenumber.Service;

import com.jumiatest.categorizephonenumber.domain.CountryDetail;
import com.jumiatest.categorizephonenumber.domain.Customer;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import com.jumiatest.categorizephonenumber.domain.State;
import com.jumiatest.categorizephonenumber.domain.mapper.CustomerMapper;
import com.jumiatest.categorizephonenumber.domain.mapper.CustomerMapperImpl;
import com.jumiatest.categorizephonenumber.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;


class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        CustomerMapper customerMapper = new CustomerMapperImpl();
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    void getAllCustomers() {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setName("Customer1");
        customer1.setPhone("(258) 847651504");

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setName("Customer2");
        customer2.setPhone("(258) 846565883");

        when(customerRepository.findAll())
                .thenReturn(Arrays.asList(customer1, customer2));

        //when
        List<CustomerDetail> customerDetails = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDetails.size());

    }

    @Test
    void getAllNumbersByCountry() {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setName("Customer1");
        customer1.setPhone("(212) 847651504");

        String code1 = CountryDetail.getCode(customer1.getPhone());
        String name = CountryDetail.getCountryName(customer1.getPhone());

        when(customerRepository.findCustomerByPhoneContaining(code1)).thenReturn(Arrays.asList(customer1));

        List<CustomerDetail> customerDetails = customerService.getAllNumbersByCountry(name);
        assertEquals(1, customerDetails.size());
        assertEquals(name, customerDetails.get(0).getCountryName());

    }

    @Test
    void getAllNumbersByState() {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setName("Customer1");
        customer1.setPhone("(212) 847651504");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1));

        List<CustomerDetail> customerDetails = customerService.getAllNumbersByState(State.VALID.toString());
        assertEquals(1, customerDetails.size());
        assertEquals(State.VALID, customerDetails.get(0).getState());
    }


}