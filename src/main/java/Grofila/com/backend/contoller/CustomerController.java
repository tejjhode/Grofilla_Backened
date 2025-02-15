package Grofila.com.backend.contoller;

import Grofila.com.backend.model.Customer;
import Grofila.com.backend.model.Shopkeeper;
import Grofila.com.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

    @PostMapping("/login")
    public Optional<Optional<Customer>> loginCustomer(@RequestBody Shopkeeper shopkeeper) {
        return Optional.ofNullable(customerService.loginCustomer(shopkeeper.getEmail(), shopkeeper.getPassword()));
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully!";
    }
}