package com.jumiatest.categorizephonenumber.domain.mapper;

import com.jumiatest.categorizephonenumber.domain.CountryDetail;
import com.jumiatest.categorizephonenumber.domain.Customer;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDetail customerToCustomerDetail(Customer customer) {
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setName(customer.getName());
        customerDetail.setNumber(customer.getPhone());
        customerDetail.setState(customerDetail.validatePhoneNumber(customer.getPhone()));
        customerDetail.setCountryCode(CountryDetail.getCode(customer.getPhone()));
        customerDetail.setCountryName(CountryDetail.getCountryName(customer.getPhone()));
        return customerDetail;
    }
}
