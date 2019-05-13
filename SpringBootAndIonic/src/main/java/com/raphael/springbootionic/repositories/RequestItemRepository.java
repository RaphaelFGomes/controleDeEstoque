package com.raphael.springbootionic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphael.springbootionic.domain.RequestItem;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer>{

}
