package com.jumiatest.categorizephonenumber.Service;

import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CustomerService {

    List<CustomerDetail> getAllCustomers();

    List<CustomerDetail> getAllNumbersByCountry(String country);

    List<CustomerDetail> getAllNumbersByState(String state);

    List<CustomerDetail> getAllNumbersByCountryAndState(String country, String state);

    Page<CustomerDetail> getAllCustomersPaged(Pageable pageable);

}
