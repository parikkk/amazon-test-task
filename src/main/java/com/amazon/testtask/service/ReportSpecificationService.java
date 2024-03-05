package com.amazon.testtask.service;

import com.amazon.testtask.domain.entity.ReportSpecification;

import java.util.List;

public interface ReportSpecificationService {

    ReportSpecification getByDataEndTimeAndDataStartTimeAndMarketplaceIdsLikeAndReportType(String dataAndTime, String dataStartTime, List<String> marketplaceIds, String reportType);

    List<ReportSpecification> getByDataStartTime(String date);

    List<ReportSpecification> getAllReports();
}
