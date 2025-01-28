package com.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.domain.SheetRow;

@Repository
public interface SheetRowRepository extends JpaRepository<SheetRow, Long> {

}
