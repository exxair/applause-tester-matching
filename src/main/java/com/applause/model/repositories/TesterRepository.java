package com.applause.model.repositories;

import com.applause.model.entities.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {
}
