package com.TradinGplatforM.TradinGplatforM.repository;

import com.TradinGplatforM.TradinGplatforM.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
}
