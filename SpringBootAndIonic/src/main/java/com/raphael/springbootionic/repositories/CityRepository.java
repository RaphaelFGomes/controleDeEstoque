package com.raphael.springbootionic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphael.springbootionic.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
