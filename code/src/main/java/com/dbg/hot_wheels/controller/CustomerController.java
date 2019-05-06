package com.dbg.hot_wheels.controller;


import com.dbg.hot_wheels.entity.Car;
import com.dbg.hot_wheels.entity.Customer;
import com.dbg.hot_wheels.entity.TelNumber;
import com.dbg.hot_wheels.service.CustomerService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/tel")
    public Collection<TelNumber> getTel(@RequestParam("id") Long id)
    {
        return customerService.getTelNumber(id);
    }

    @GetMapping("/car")
    public Collection<Car> getCars(@RequestParam("id") Long id){
        return customerService.getCars(id);
    }

    @GetMapping("/customer")
    public Collection<Customer> getCustomer()
    {
        System.out.println("getting customers");
        return customerService.getCustomers();
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        return customerService.createCustomer(customer);
    }

    @PostMapping("/deleteCustomer")
    public JSONObject deleteCustomer(@RequestBody Customer customer){
        return customerService.deleteCustomer(customer);
    }

}
