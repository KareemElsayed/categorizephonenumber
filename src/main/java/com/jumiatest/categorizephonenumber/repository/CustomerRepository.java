package com.jumiatest.categorizephonenumber.repository;

import com.jumiatest.categorizephonenumber.domain.Customer;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByPhoneContaining(String countryCode);

    Page<Customer> findAll(Pageable pageable);

}
