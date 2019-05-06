package com.dbg.hot_wheels.service;

import com.dbg.hot_wheels.entity.Car;
import com.dbg.hot_wheels.entity.Customer;
import com.dbg.hot_wheels.entity.TelNumber;
import com.dbg.hot_wheels.repository.CarRepository;
import com.dbg.hot_wheels.repository.CustomerRepository;

import com.dbg.hot_wheels.repository.TelNumberRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TelNumberRepository telNumberRepository;
    @Autowired
    CarRepository carRepository;

    /**
     * get a customer by it's name
     * @param name the name of this customer
     * @return the customer we need
     */
    public Customer getCustomer(String name){
        return customerRepository.findByName(name);
    }

    /**
     * get all customers in the database
     * @return a collection of customers
     */
    public Collection<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    /**
     * Create a new customer
     * @param customer customer submitted by jtable
     * @return the info of this customer
     */
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    /**
     * Get all telephone numbers of a customer
     * @param id the id of this customer
     * @return a collection of telephone numbers
     */
    public Collection<TelNumber> getTelNumber(Long id){
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            Customer customer = optionalCustomer.get();
            return customer.getNumbers();
        }
        catch (Exception e){
            System.out.println("getTelNumber not success");
            return null;
        }
    }

    /**
     * Get cars of a customer by it's id
     * @param id if of this customer
     * @return a collection of cars
     */
    public Collection<Car> getCars(Long id){
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            Customer customer = optionalCustomer.get();
            return customer.getCars();
        }
        catch (Exception e){
            System.out.println("getCars not success");
            return null;
        }
    }

    /**
     * Delete a customer from the database
     * @param customer the customer need to be deleted
     * @return the result JSON message
     */
    public JSONObject deleteCustomer(Customer customer){
        JSONObject result = new JSONObject();
        try {
            customerRepository.delete(customer);
            result.put("Result", "OK");
            return result;
        }
        catch (Exception e){
            result.put("Result", "ERROR");
            result.put("Message", e.getMessage());
            return result;
        }
    }

    /**
     * update a customer's profile
     * @param customer the customer submitted by the jtable
     * @return the result JSON message
     */
//    public JSONObject updateCustomer(Customer customer){
//        JSONObject result = new JSONObject();
//        try {
//            Collection<TelNumber> tels = customer.getNumbers();
//            Collection<Car> cars = customer.getCars();
//            customerRepository.saveAndFlush(customer);
//            telNumberRepository.saveAll(tels);
//            carRepository.saveAll(cars);
//            result.put("Result", "OK");
//            return result;
//        }
//        catch (Exception e){
//            result.put("Result", "ERROR");
//            result.put("Message", e.getMessage());
//            return result;
//        }
//    }
}
