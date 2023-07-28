package com.example.demotaskitbootcamp.persistence.repository;

import com.example.demotaskitbootcamp.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
