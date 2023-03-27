package com.TradinGplatforM.TradinGplatforM.repository;

import com.TradinGplatforM.TradinGplatforM.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History,Integer> {
        }
