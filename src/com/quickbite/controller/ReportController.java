package com.quickbite.controller;

import java.util.Map;

import com.quickbite.entity.Product;
import com.quickbite.service.ReportService;

public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        super();
        this.reportService = reportService;
    }

    public void printAnalyticsDashboard() {
        System.out.println("\n=================");

        System.out.println("Quickbite excecution analytics dashboard");
        System.out.println("=================");
        System.out.printf("Total platform gross revenue:%.2f\n", reportService.calculateTotalRevenue());

        System.out.println("\nCategory wise orderitems count");
        Map<String, Long> categoryReport = reportService.getCategoryCountReport();

        categoryReport.forEach((cat, count) -> System.out.printf("->%-15s: %d items(s)\n", cat, count));

        Product topProduct = reportService.getTopSellingProduct();
        System.out.println("\n Top selling products");

        if (topProduct != null) {
            System.out
                    .println("TOP SKu" + topProduct.getName() + "(" + topProduct.getCategory().getCategoryName() + ")");

        } else {
            System.out.println("No sales recoreded");

        }

        System.out.println("===================\n");
    }

}