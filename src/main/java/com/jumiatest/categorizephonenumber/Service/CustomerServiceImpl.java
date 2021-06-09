package com.jumiatest.categorizephonenumber.Service;

import com.jumiatest.categorizephonenumber.domain.CountryDetail;
import com.jumiatest.categorizephonenumber.domain.Customer;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import com.jumiatest.categorizephonenumber.domain.mapper.CustomerMapper;
import com.jumiatest.categorizephonenumber.exception.CustomersNotFoundException;
import com.jumiatest.categorizephonenumber.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;


    private CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDetail> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDetail customerDetail = customerMapper.customerToCustomerDetail(customer);
                    return customerDetail;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDetail> getAllNumbersByCountry(String countryName) {

        String countryCode = CountryDetail.getCountryCodeFromName(countryName);
        return customerRepository.findCustomerByPhoneContaining(countryCode)
                .stream()
                .map(customer -> {
                    CustomerDetail customerDetail = customerMapper.customerToCustomerDetail(customer);
                    return customerDetail;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDetail> getAllNumbersByState(String state) {
        List<CustomerDetail> customerDetailList = customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDetail customerDetail = customerMapper.customerToCustomerDetail(customer);
                    return customerDetail;
                }).collect(Collectors.toList());

        List<CustomerDetail> customerDetailListResult = customerDetailList.stream()
                .filter(customerDetail -> customerDetail.getState().toString().equals(state.toUpperCase()))
                .collect(Collectors.toList());
        return customerDetailListResult;
    }

    @Override
    public List<CustomerDetail> getAllNumbersByCountryAndState(String countryName, String state) {
        String countryCode = CountryDetail.getCountryCodeFromName(countryName);
        if (countryCode == null) {
            throw new CustomersNotFoundException("Country And State Not Found " + countryName + " " + state);
        }
        List<CustomerDetail> customerDetailList = customerRepository.findCustomerByPhoneContaining(countryCode)
                .stream()
                .map(customer -> {
                    CustomerDetail customerDetail = customerMapper.customerToCustomerDetail(customer);
                    return customerDetail;
                }).collect(Collectors.toList());
        List<CustomerDetail> customerDetailListResult = customerDetailList
                .stream()
                .filter(customerDetail -> customerDetail.getState().toString().equals(state.toUpperCase()))
                .collect(Collectors.toList());
        return customerDetailListResult;
    }

    @Override
    public Page<CustomerDetail> getAllCustomersPaged(Pageable pageable) {
        Page<Customer> customerDetailPage = customerRepository.findAll(pageable);
        List<Customer> customers = customerDetailPage.getContent();
        List<CustomerDetail> customerDetails= customers
                .stream()
                .map(customer -> customerMapper.customerToCustomerDetail(customer))
                .collect(Collectors.toList());
        Page<CustomerDetail> detailPage = new PageImpl<>(customerDetails);
        return detailPage;
    }

}
