package Grofila.com.backend.service;

import Grofila.com.backend.model.Customer;
import Grofila.com.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Optional<Customer> loginCustomer(String email, String password) {
        return customerRepository.findByEmail(email)
                .filter(c -> passwordEncoder.matches(password, c.getPassword()));
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setEmail(updatedCustomer.getEmail());
                    if (!updatedCustomer.getPassword().isEmpty()) {
                        customer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
                    }
                    customer.setAddress(updatedCustomer.getAddress());
                    customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    return customerRepository.save(customer);
                }).orElse(null);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}