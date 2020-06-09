package br.com.utils;

import br.com.core.takescreenshot.TakeScreenShot;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import lombok.extern.java.Log;

import static br.com.utils.FileUtils.readCsv;

@Log
public class ReporterUtils extends WebDriverHelper {

    private String complementNameEvidence;

    public ReporterUtils() {
        super();
    }

    /**
     * Add json to the cucumber extent-report listener for generating ExtentReports and Klov
     *
     * @param json Text to be added to the report
     */
    public static void addJsonToReport(Object a) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = gson.toJsonTree(a);
        String prettyJsonString = gson.toJson(je);
        Markup m = MarkupHelper.createCodeBlock(prettyJsonString);
        testScenario.get().log(m.getMarkup().replace("class='code-block'", "class='code-block w-100 mh-100'"));
        log.info(prettyJsonString);
    }

    /**
     * Add comment to the cucumber-extent report listener for generating ExtentReports and Klov
     *
     * @param sMsg Text to be added to the report
     */
    public static synchronized void addLogToReport(String sMsg) {
        testScenario.get().log(sMsg);
        log.info(sMsg);
    }

    /**
     * Add comment and screenshot to the cucumber-extent report listener for generating ExtentReports and Klov
     *
     * @param sMsg Text to be added to the report
     */
    public static synchronized void addFileToReport(String sMsg, String filePath) {
        Markup m = MarkupHelper.createLabel(readCsv(filePath).toString(), ExtentColor.TRANSPARENT);
        testScenario.get().log(sMsg);
        testScenario.get().log(m.getMarkup());
    }

    /**
     * Add comment and screenshot to the cucumber-extent report listener for generating ExtentReports and Klov
     *
     * @param sMsg Text to be added to the report
     */
    public static synchronized void addScreenshotToReport(String sMsg) {
        testScenario.get().log(sMsg);
        testScenario.get().attach(
                TakeScreenShot.getImageBytes(getDriverWeb()),
                "image/png",
                getNameImage());
        log.info(sMsg);
    }

    /**
     * Add screenshot to the cucumber-extent report listener for generating ExtentReports and Klov
     */
    public static synchronized void addScreenshotToReport() {
        testScenario.get().attach(TakeScreenShot.getImageBytes(
                getDriverWeb()),
                "image/png",
                getNameImage());
        log.info("Adicionado print.");
    }

    public static void logReport() {
        if (testScenario.get().isFailed()) {
            if (driver != null) {
                addScreenshotToReport();
            } else {
                addLogToReport(testScenario.get().getStatus().toString());
            }
        }
    }

    private static String getNameImage() {
        return "";
        //TODO - Verificar qual a melhor opção para o nome da image.
//        return testScenario.get().getName().concat(" - " + new Timestamp(System.currentTimeMillis()).toString());
    }

}
