package br.com.pom;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;

import static br.com.utils.PropertiesHelper.*;
import static br.com.utils.ReporterUtils.addScreenshotToReport;
import static br.com.utils.WebDriverHelper.getDriverWeb;
import static org.testng.Assert.fail;

public class GeralPage {

    private final int TIME_WAIT = 60;
    private final int TIME_WAIT_LOADING = 150;
    private final By modalContent = By.id("dialog-confirmation-message");
    private final By btnSalvar = By.id("dialog-button-save");
    private final By btnSalvarEEncerrar = By.id("button-save-close");
    private final By btnConfirmar = By.id("dialog-button-yes");
    private final By lupaColaborador = By.id("button-search-employee");
    private final By btnSalvarColaboradorSelecionado = By.id("dialog-button-yes");
    private final By inputNomeMatriculaModalInterno = By.id("nomeMatricula");
    private final By labelTextMensagemBox = By.cssSelector("sro-message-box > div > span");
    private final By overlayOptionsSelect = By.cssSelector("div.cdk-overlay-backdrop.cdk-overlay-transparent-backdrop.cdk-overlay-backdrop-showing");
    private final By expansionSindicancia = By.id("mat-expansion-panel-header-1");
    public WebDriver driver = getDriverWeb();
    public By snackBarContent = By.id("snack-bar-content");
    public By buttonDismissSnack = By.id("button-dismiss-snack");
    Function<Integer, By> checkBoxColaborador = (Integer index) -> By.id("tbEmployee-nome-" + index);
    Function<Integer, By> tbEmployeeMatricula = (Integer index) -> By.id("tbEmployee-matricula-" + index);

    public static Instant convertDateTimeToUTC(String dateTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Instant dataHoraUTC = LocalDateTime.parse(dateTime, f).toInstant(ZoneOffset.ofHours(-3));
        return dataHoraUTC;
    }

    public static String formatToDecimal(long number) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        return formatter.format(number);
    }

    public static String formatToDecimal(BigDecimal number) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        return formatter.format(number.setScale(2, BigDecimal.ROUND_DOWN));
    }

    public static String formatToDecimal(BigDecimal number, int decimalDigits) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(decimalDigits);
        return formatter.format(number.setScale(decimalDigits, BigDecimal.ROUND_DOWN));
    }

    public void validaMensagemBox(String mensagemBox) {
        try {
            expectText(labelTextMensagemBox, mensagemBox);
            String textoSnackBar = getText(labelTextMensagemBox);
            Assert.assertEquals(textoSnackBar, mensagemBox);
            scrollToElement(labelTextMensagemBox);
            addScreenshotToReport("Validando mensagem box = " + mensagemBox);
        } catch (Exception e) {
            addScreenshotToReport("Esperava a mensagem : " + mensagemBox);
        }
    }

    public void naoImplementado() {
        fail("Não implementado");
    }

    public String getTextSnackBar() {
        return getText(snackBarContent);
    }

    public String getTextModal() {
        return getText(modalContent);
    }

    public void waitTime() {
        try {
            Thread.sleep(TIME_WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToElement(By by) {
        expectElementVisible(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public void scrollToElementWithAction(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void highlightElement(By by) {
        try {
            if (highlightElementShow != null && highlightElementShow.equalsIgnoreCase("true")) {
                for (int i = 0; i < 1; i++) {
                    WebElement element = driver.findElement(by);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px dashed red'", element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1,5'", element);
                    Thread.sleep(TIME_WAIT);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
                    Thread.sleep(TIME_WAIT);
                }
            }
        } catch (Exception e) {
            fail("The element is not visible for Highlight: " + e);
        }
    }

    public void highlightElement(WebElement element) {
        try {
            if (highlightElementShow != null && highlightElementShow.equalsIgnoreCase("true")) {
                for (int i = 0; i < 1; i++) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px dashed red'", element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1,5'", element);
                    Thread.sleep(TIME_WAIT);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
                    Thread.sleep(TIME_WAIT);
                }
            }

        } catch (Exception e) {
            fail("The element is not visible for Highlight: " + e);
        }
    }

    public void expectElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }

    }

    public void expectLoading() {
        waitTime(TIME_WAIT_LOADING);
        expectElementNotVisible(By.className("loading-backdrop"));
    }

    public void expectTableLoading() {
        waitTime(TIME_WAIT_LOADING);
        expectElementNotVisible(By.id("table-loading"));
    }

    public void expectHeaderLoading() {
        waitTime(TIME_WAIT_LOADING);
        expectElementNotVisible(By.tagName("prisma-subtle-loading"));
    }

    public void expectElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_WAIT);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            highlightElement(element);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void expectElementDisable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_WAIT);
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
            WebElement element = driver.findElement(by);
            highlightElement(element);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void expectElementDisable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_WAIT);
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(webElement)));
            highlightElement(webElement);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void expectElementVisibleWithoutHighlight(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void expectElementNotVisible(By by) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(AssertionError.class, NoSuchElementException.class);

            wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public boolean expectText(By by, String value) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(AssertionError.class, NoSuchElementException.class);

            return wait.until(driver -> getText(by).trim().toLowerCase().contains(value.trim().toLowerCase()));
        } catch (Exception e) {
            addScreenshotToReport("");
            System.out.println(e);
            fail("Expect: " + value + " Contains: " + driver.findElement(by).getText());
            return false;
        }
    }

    public boolean expectValue(By by, String value) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(AssertionError.class, NoSuchElementException.class);

            return wait.until(driver -> getValue(by).toLowerCase().contains(value.toLowerCase()));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail("Expect :" + value + "" + e.getMessage());
            return false;
        }
    }

    public boolean expectText(By by, int value) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(AssertionError.class, NoSuchElementException.class);

            return wait.until(driver -> driver.findElement(by).getText().toLowerCase().contains(("" + value).toLowerCase()));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail("Expect text : " + value + " in : " + driver.findElement(by).getText() + e.getMessage());
            return false;
        }
    }

    public void clickWithJavaScript(By by) {
        try {
            WebElement element = driver.findElement(by);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            highlightElement(by);
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void sendKeys(By by, String value) {
        try {
            expectLoading();
            expectElementVisible(by);
            highlightElement(by);
            clickWithJavaScript(by);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void sendKeys(By by, BigDecimal value) {
        try {
            expectLoading();
            expectElementVisible(by);
            highlightElement(by);
            clickWithJavaScript(by);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value.toString());
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void sendKeysWithJavaScript(By by, String value) {
        try {
            WebElement element = getWebElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", element, value);
            ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input')); ", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('blur')); ", element);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void sendKeysWithJavaScriptInCKEditor(By by, String value) {
        try {
            WebElement element = getWebElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].ckeditorInstance.setData(arguments[1]); ", element, value);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public String getDataCKEditor(By by) {
        try {
            WebElement element = getWebElement(by);
            return ((JavascriptExecutor) driver).executeScript("return arguments[0].ckeditorInstance.getData(); ", element).toString();
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
        return "";
    }

    public void sendKeys(By by, long value) {
        try {
            expectLoading();
            expectElementVisible(by);
            WebElement element = driver.findElement(by);
            highlightElement(element);
            clickWithJavaScript(by);
            element.clear();
            element.sendKeys(String.valueOf(value));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void sendKeys(By by, int value) {
        try {
            WebElement element = driver.findElement(by);
            expectElementVisible(by);
            highlightElement(element);
            element.click();
            element.clear();
            element.sendKeys("" + value);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void sendKeys(By by, Double value) {
        try {
            WebElement element = driver.findElement(by);
            expectElementVisible(by);
            highlightElement(element);
            element.click();
            element.clear();
            element.sendKeys("" + value);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public void checkedWithJavaScript(By by) {
        WebElement radioBtn = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn);
    }

    public void clickWithAction(By by) {
        scrollToElement(by);
        expectElementVisibleWithoutHighlight(by);
        WebElement targetElement = driver.findElement(by);
        Actions action = new Actions(driver);
        action.click(targetElement);
        action.perform();
    }

    public void clickWithActionWithoutScroll(By by) {
        expectElementVisibleWithoutHighlight(by);
        WebElement targetElement = driver.findElement(by);
        Actions action = new Actions(driver);
        action.click(targetElement);
        action.perform();
    }

    public void clearForce(By by) {
        expectElementVisibleWithoutHighlight(by);
        driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    public void clear(By by) {
        try {
            WebElement element = driver.findElement(by);
            expectElementVisible(by);
            highlightElement(element);
            element.clear();
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public String getText(By by) {
        try {
            return getWebElement(by).getText();
        } catch (StaleElementReferenceException ae) {
            return getText(by);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return null;
        }
    }

    public String getAttribute(By by, String attribute) {
        try {
            expectElementVisibleWithoutHighlight(by);
            WebElement element = driver.findElement(by);
            return element.getAttribute(attribute);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return null;
        }
    }

    public String getAttribute(WebElement webElement, String attribute) {
        try {
            return webElement.getAttribute(attribute);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return null;
        }
    }

    public WebElement getWebElement(By by) {
        try {
            return driver.findElement(by);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return null;
        }
    }

    public List<WebElement> getWebElements(By by) {
        try {
            return driver.findElements(by);
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void clickAndHighlight(By by) {
        try {
            expectLoading();
            expectElementVisibleWithoutHighlight(by);
            expectLoading();
            expectElementClickable(by);
            WebElement element = driver.findElement(by);
            highlightElement(element);
            element.click();
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public boolean expectTextInUrl(String text) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(AssertionError.class);

            return wait.until(driver -> driver.getCurrentUrl().contains(text));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return false;
        }
    }

    public void clickBackButton() {
        try {
            highlightElement(By.id("back-button"));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public File verifyFileExist(String fileName) {
        File newFile = new File(pathDownload);
        if (Objects.requireNonNull(newFile.listFiles()).length > 0) {
            for (File file : Objects.requireNonNull(newFile.listFiles())) {
                if (file.getName().contains(fileName)) return file;
            }
        }
        return null;
    }

    public String getValue(By by) {
        return getAttribute(by, "value");
    }

    public boolean isEmpty(By by) {
        return getText(by).equals("") && getValue(by).equals("");
    }

    public int countChildElement(By by, String tag) {
        expectElementVisibleWithoutHighlight(by);
        return getWebElement(by).findElements(By.tagName(tag)).size();
    }

    public boolean isCheckboxChecked(By by) {
        expectElementVisibleWithoutHighlight(by);
        return getAttribute(by, "class").contains("mat-checkbox-checked");
    }

    public boolean isRadioChecked(By by) {
        expectElementVisibleWithoutHighlight(by);
        return getAttribute(by, "class").contains("mat-radio-checked");
    }

    public boolean isReadOnly(By by) {
        expectElementVisibleWithoutHighlight(by);
        return getAttribute(by, "readonly").equalsIgnoreCase("true");
    }

    public boolean isDisabled(By by) {
        return !getWebElement(by).isEnabled();
    }

    public boolean isDisabled(WebElement webElement) {
        return !webElement.isEnabled();
    }

    public void isReadonlyForm(By by, String tag) {
        List<WebElement> listElement = getWebElement(by).findElements(By.tagName(tag));
        if ((listElement != null) && (listElement.size() > 0)) {
            listElement.forEach(element ->
                    Assert.assertTrue((isDisabled(element) || isAriaDisabled(element)))
            );
        }

    }

    public boolean expectElementNotDisabled(By by) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(AssertionError.class, NoSuchElementException.class);

            return wait.until(driver -> {
                        WebElement element = driver.findElement(by);
                        Boolean disable;
                        String ariaDisable;

                        if (element != null) {
                            disable = !element.isEnabled();
                            ariaDisable = element.getAttribute("disabled");

                            if (disable != null) {
                                return disable.equals(false);
                            }
                            if (ariaDisable != null) {
                                return ariaDisable.equals("false");
                            }
                        }
                        return false;
                    }
            );
        } catch (Exception e) {
            addScreenshotToReport(e.toString());
            fail(e.getMessage() + "Failed in expect element not visible");
            return false;
        }
    }

    public boolean isEnabled(By by) {
        return getWebElement(by).isEnabled();
    }

    public boolean isAriaDisabled(By by) {
        expectElementVisible(by);
        expectLoading();
        return getAttribute(by, "aria-disabled").equals("true");
    }

    public boolean isAriaDisabled(WebElement webElement) {
        return getAttribute(webElement, "aria-disabled").equals("true");
    }

    public void clicarBtnSalvarGenerico() {
        scrollToElement(btnSalvar);
        expectElementClickable(btnSalvar);
        clickAndHighlight(btnSalvar);
    }

    public void clicarBtnSalvarEEncerrarGenerico() {
        scrollToElement(btnSalvarEEncerrar);
        expectElementClickable(btnSalvarEEncerrar);
        clickWithJavaScript(btnSalvarEEncerrar);
    }

    public void clicarBtnConfirmar() {
        scrollToElement(btnConfirmar);
        expectElementClickable(btnConfirmar);
        clickAndHighlight(btnConfirmar);
    }

    public void uploadArquivo(By by, String pathArquivo) {
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public void uploadArquivo(By by) {
        WebElement webElement = driver.findElement(by);
        String pathArquivo = pathForUpload + "arquivoTesteImg.jpg";
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public void uploadArquivo(int qtdArquivos) {
        for (int i = 1; i <= qtdArquivos; i++) {
            expectLoading();
            String pathArquivo = pathForUpload + "arquivoTeste" + i + ".txt";
            WebElement webElement = driver.findElement(By.id("input-file-upload"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
            webElement.sendKeys(pathArquivo);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
        }
    }

    public void uploadArquivo(String pathArquivo) {
        WebElement webElement = driver.findElement(By.id("input-file-upload"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public void uploadImagem() {
        String pathArquivo = pathForUpload + "arquivoTesteImg.jpg";
        WebElement webElement = driver.findElement(By.id("input-file-upload"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public void uploadPDF() {
        String pathArquivo = pathForUpload + "example.pdf";
        WebElement webElement = driver.findElement(By.id("input-file-upload"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public void uploadPDF(By by) {
        String pathArquivo = pathForUpload + "example.pdf";
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public void uploadImagem(By by) {
        String pathArquivo = pathForUpload + "arquivoTesteImg.jpg";
        WebElement webElement = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", webElement);
        webElement.sendKeys(pathArquivo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", webElement);
    }

    public long getDayCount(String start, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long diff = -1;
        try {
            Date dateStart = simpleDateFormat.parse(start);
            Date dateEnd = simpleDateFormat.parse(end);

            //time is always 00:00:00, so rounding should help to ignore the missing hour when going from winter to summer time, as well as the extra hour in the other direction
            diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {
            //handle the exception according to your own situation
        }
        return diff;
    }

    public void sendKeys(By by, Float value) {
        try {
            expectLoading();
            expectElementVisible(by);
            highlightElement(by);
            clickWithJavaScript(by);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value.toString());
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
        }
    }

    public String formatToDecimal(double number) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        return formatter.format(number);
    }

    public String formatToCurrency(double number) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        return "R$ " + formatter.format(number);
    }

    public String formatToCurrency(BigDecimal number) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        return "R$ " + formatter.format(number.setScale(2, BigDecimal.ROUND_DOWN));
    }

    public String convertDateTimeToDate(String dateTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, f);
        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String convertDateTimeToTime(String dateTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, f);
        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void switchToWindow() {
        //Get all window handles
        Set<String> allHandles = driver.getWindowHandles();

        //count the handles Here count is=2
        System.out.println("Count of windows:" + allHandles.size());

        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        System.out.println("currentWindow Handle" + currentWindowHandle);

        //Remove first/default Handle
        allHandles.remove(allHandles.iterator().next());

        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        System.out.println("last window handle" + lastHandle);

        //switch to second/last window, because we know there are only two windows 1-parent window 2-other window(ad window)
        driver.close();
        driver.switchTo().window(lastHandle);
    }

    public void validaMensagemSnackBar(String msg) {
        try {
            expectText(snackBarContent, msg);
            addScreenshotToReport("Validando mensagem snackBar.");
            clickWithJavaScript(buttonDismissSnack);
        } catch (Exception e) {
            addScreenshotToReport("Esperava a mensagem : " + msg);
            fail("Esperava a mensagem : " + msg);
        }

    }

    public void validaMensagemSnackBarNotClosed(String msg) {
        try {
            expectText(snackBarContent, msg);
        } catch (Exception e) {
            addScreenshotToReport("Esperava a mensagem : " + msg);
            fail("Esperava a mensagem : " + msg);
        }

    }

    public void selecionarResponsavel(String nome) {
        sendKeys(inputNomeMatriculaModalInterno, nome);
        clickAndHighlight(lupaColaborador);
        clickAndHighlight(checkBoxColaborador.apply(0));
        clickAndHighlight(btnSalvarColaboradorSelecionado);
    }

    public String selecionarResponsavelERetornarMatricula(String nome) {
        sendKeys(inputNomeMatriculaModalInterno, nome);
        clickAndHighlight(lupaColaborador);
        clickAndHighlight(checkBoxColaborador.apply(0));
        String matricula = getText(tbEmployeeMatricula.apply(0));
        clickAndHighlight(btnSalvarColaboradorSelecionado);
        return matricula;
    }

    public String selectOptionAndReturnValue(IntFunction<By> option, int index) {

        clickWithAction(option.apply(index));

        return getValue(option.apply(index));
    }

    public String selectOptionWithValueAndReturnValue(By select, String value) {
        clickAndHighlight(select);
        clickAndHighlight(By.xpath("//*[contains(@class, 'mat-option')][@title='" + value + "']"));
        return getText(select);
    }

    public String selectOptionAndReturnValue(By select, IntFunction<By> option, int index) {
        clickAndHighlight(select);
        expectLoading();
        clickAndHighlight(option.apply(index));
        return getText(select);
    }

    public boolean expectTagName(By by, String tag) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(TIME_WAIT))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(AssertionError.class, NoSuchElementException.class);

            return wait.until(driver -> driver.findElement(by).getTagName().toLowerCase().contains(tag.toLowerCase()));
        } catch (Exception e) {
            addScreenshotToReport("");
            fail(e.getMessage());
            return false;
        }

    }

    public String selectOptionAndReturnValue(By select, int index) {
        WebElement element = getWebElement(select);
        Select select1 = new Select(element);
        select1.selectByIndex(index);
        expectLoading();
        return getText(select);
    }

    public String autoCompleteSelectOptionAndReturnValue(By autoCompleteInput, IntFunction<By> option, String text, int index) {
        sendKeys(autoCompleteInput, text);
        expectLoading();
        clickAndHighlight(option.apply(index));
        return getValue(autoCompleteInput);
    }

    public void selectOptionByText(By select, Function<String, By> option, String text) {
        clickAndHighlight(select);
        getWebElement(option.apply(text)).click();
    }

    public void mouseOver(By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event(arguments[1])); ", getWebElement(by), "mouseenter");
    }

    public void clicarExpansionSindicancia() {
        clickAndHighlight(expansionSindicancia);
    }

    public boolean data1MenorQueData2(String dataFiltro, String dataCell) {
        LocalDate d1 = LocalDate.parse(dataFiltro, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate d2 = LocalDate.parse(dataCell, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return d1.compareTo(d2) <= 0;
    }

    public boolean data1MenorQueData2Filtro(String dataFiltro, String dataCell) {
        LocalDate d1 = LocalDate.parse(dataFiltro, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate d2 = LocalDate.parse(dataCell, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (d1.compareTo(d2) > 0) {
            //Date 1 occurs after Date 2
            return false;
        } else //Both dates are equal
            if (d1.compareTo(d2) < 0) {
                //Date 1 occurs before Date 2
                return true;
            } else return d1.compareTo(d2) == 0;
    }

    public boolean data1MaiorQueData2Filtro(String dataFiltro, String dataCell) {
        LocalDate d1 = LocalDate.parse(dataFiltro, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate d2 = LocalDate.parse(dataCell, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (d1.compareTo(d2) > 0) {
            //Date 1 occurs after Date 2
            return true;
        } else //Both dates are equal
            if (d1.compareTo(d2) < 0) {
                //Date 1 occurs before Date 2
                return false;
            } else return d1.compareTo(d2) == 0;
    }

    public void closeOptionsSelect() {
        clickWithJavaScript(overlayOptionsSelect);
    }

    public void ordenarColunaDesc(By thHeader) {
        expectTableLoading();
        expectElementVisible(thHeader);
        String orderAttribute = getAttribute(thHeader, "aria-sort");

        if (orderAttribute == null) {
            clickWithJavaScript(thHeader);
            expectTableLoading();
            clickWithJavaScript(thHeader);
            expectTableLoading();
        }

        if (orderAttribute != null && orderAttribute.equalsIgnoreCase("ascending")) {
            clickWithJavaScript(thHeader);
            expectTableLoading();
        }
    }

    public void waitPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, TIME_WAIT);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
    }

    public void dragAndDropWithAction(By from, By to) {
        /*
        Caso esse metódo seja utilizado em uma recursividade, deve ser adicionado um
          waitTime(400);
          entre as iterações
         */
        WebElement _from = getWebElement(from);
        WebElement _to = getWebElement(to);
        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.clickAndHold(_from)
                .moveToElement(_to)
                .release(_to)
                .build();

        dragAndDrop.perform();
    }

}
