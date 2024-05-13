package com.example.carrentalexam.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DamageRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createDamageReport(int rentalContractId, String descriptionOfDamage, double repairCost, String status) {
        String query = "insert into damages(rental_contract_id, description_of_damage, repair_cost, damage_status)" +
                "values(?, ?, ?, ?);";
        jdbcTemplate.update(query, rentalContractId, descriptionOfDamage, repairCost, status);
    }
}
