package com.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.domain.SheetColumn;

@Repository
public interface SheetColumnRepository extends JpaRepository<SheetColumn, Long> {

}
