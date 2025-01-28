package com.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.domain.Sheet;

@Repository
public interface SheetRepository extends JpaRepository<Sheet, Long> {

}
