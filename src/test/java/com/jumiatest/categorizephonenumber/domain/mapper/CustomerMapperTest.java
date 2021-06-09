package com.jumiatest.categorizephonenumber.domain.mapper;

import com.jumiatest.categorizephonenumber.domain.Customer;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerMapperTest {

    public static final String NAME = "Test";

    public static final String PHONE_NUMBER = "(212) 691933626";

    CustomerMapper customerMapper = new CustomerMapperImpl();

    @Test
    public void customerToCustomerDetail() {

        //given
        Customer customer = new Customer();
        customer.setName(NAME);
        customer.setPhone(PHONE_NUMBER);

        //when
        CustomerDetail customerDetail = customerMapper.customerToCustomerDetail(customer);

        //then
        assertEquals(NAME, customerDetail.getName());
        assertEquals(PHONE_NUMBER, customerDetail.getNumber());
        assertEquals("(212)", customerDetail.getCountryCode());
        assertEquals("Morocco", customerDetail.getCountryName());
        assertEquals("VALID", customerDetail.getState().toString());

    }

}