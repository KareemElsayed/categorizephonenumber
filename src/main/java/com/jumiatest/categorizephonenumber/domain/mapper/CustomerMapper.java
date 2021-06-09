package com.jumiatest.categorizephonenumber.domain.mapper;

import com.jumiatest.categorizephonenumber.domain.Customer;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;

public interface CustomerMapper {

    CustomerDetail customerToCustomerDetail(Customer customer);
}
