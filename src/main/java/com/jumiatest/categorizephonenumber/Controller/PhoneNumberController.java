package com.jumiatest.categorizephonenumber.Controller;

import com.jumiatest.categorizephonenumber.Service.CustomerService;
import com.jumiatest.categorizephonenumber.domain.CustomerDetail;
import com.jumiatest.categorizephonenumber.domain.Response;
import com.jumiatest.categorizephonenumber.exception.CustomersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
public class PhoneNumberController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDetail> getAll() {
        List<CustomerDetail> allCustomers = customerService.getAllCustomers();
        if (allCustomers.size() == 0)
            throw new CustomersNotFoundException("Customers Not Found!");
        return allCustomers;
    }

    @GetMapping("/customers/{country}")
    public List<CustomerDetail> getAllByCountry(@PathVariable String country) {
        List<CustomerDetail> allNumbersByCountry = customerService.getAllNumbersByCountry(country);
        if (allNumbersByCountry.size() == 0) {
            throw new CustomersNotFoundException("Country Not Found " + country);
        }
        return allNumbersByCountry;
    }


    @GetMapping("/customer/{state}")
    public List<CustomerDetail> getAllByState(@PathVariable String state) {
        List<CustomerDetail> allNumbersByState = customerService.getAllNumbersByState(state);
        if (allNumbersByState.size() == 0) {
            throw new CustomersNotFoundException("State Not Found " + state);
        }
        return allNumbersByState;
    }

    @GetMapping("/customers/{country}/{state}")
    public List<CustomerDetail> getAllByCountryAndState(@PathVariable String country, @PathVariable String state) {
        List<CustomerDetail> allNumbersByCountryAndState = customerService.getAllNumbersByCountryAndState(country, state);
        if (allNumbersByCountryAndState.size() == 0) {
            throw new CustomersNotFoundException("Country And State Not Found " + country + "  " + state);
        }
        return allNumbersByCountryAndState;
    }

    @GetMapping("/customers/pages")
    public Response getAllPaged(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<CustomerDetail> customers = null;

        customers =  customerService.getAllCustomersPaged(paging);

        Response response = new Response(customers.getContent(),customers.getTotalPages(),
                customers.getNumber(), customers.getSize());
       return response;
    }


}
