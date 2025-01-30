package com.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.excel.domain.Excel;

@Repository
public interface ExcelRepository extends JpaRepository<Excel, Long>, JpaSpecificationExecutor<Excel> {

}
