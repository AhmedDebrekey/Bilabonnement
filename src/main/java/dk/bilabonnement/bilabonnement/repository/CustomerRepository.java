package dk.bilabonnement.bilabonnement.repository;

import dk.bilabonnement.bilabonnement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAll(){
        return jdbcTemplate.query("SELECT * FROM customers", (rs, rowNum) ->{
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getString("phone"));
            return customer;
        });
    }

    public void save(Customer customer){
        jdbcTemplate.update(
                "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)",
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhone()
                );
    }

    public Integer countTotalCustomers() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM customers",
                Integer.class
        );
    }

    public Customer getById(Integer customerId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM customers WHERE customer_id = ?",
                (rs, rowNum) -> {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setName(rs.getString("name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone(rs.getString("phone"));
                    return customer;
                },
                customerId
        );
    }
}
