package com.amazon.testtask.repository;

import com.amazon.testtask.domain.entity.ReportSpecification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportSpecificationRepository extends MongoRepository<ReportSpecification, Long> {

    ReportSpecification findByDataEndTimeAndDataStartTimeAndMarketplaceIdsLikeAndReportType(String dataAndTime, String dataStartTime, List<String> marketplaceIds, String reportType);

    List<ReportSpecification> findByDataStartTime(String date);
}
