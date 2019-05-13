package com.raphael.springbootionic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphael.springbootionic.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
