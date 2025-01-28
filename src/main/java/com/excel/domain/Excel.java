package com.excel.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "excel")
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excel_id")
    private Long excelId;

    @Column(name = "file_name")
    private String fileName;

    @OneToMany(mappedBy = "excel")
    private List<Sheet> sheets;
}
