package com.amazon.testtask.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reportSpecification")
public class ReportSpecification {

    @Id
    private Long id;

    private String dataEndTime;

    private String dataStartTime;

    private List<String> marketplaceIds;

    private Object reportOptions;

    private String reportType;
}
