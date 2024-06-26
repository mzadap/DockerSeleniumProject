package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportHelper {

    public static ExtentReports reports;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_mm_yyyy_hh_mm_ss");


    public static ExtentReports getReportsObject() {
        String reportPath = "./report/"+formatter.format(LocalDateTime.now());
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");
        sparkReporter.config().setJs("document.getElementsByClassName('col-sm-12 col-md-4')[0].style.setProperty('min-inline-size','-webkit-fill-available');");

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Tester is :", "Nachiket Zadap");
        return reports;
    }
}
