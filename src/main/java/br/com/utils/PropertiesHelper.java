package br.com.utils;

import br.com.core.properties.PropertiesManager;

import java.io.File;
import java.net.URISyntaxException;

public class PropertiesHelper {

    public static final String pathForUpload = System
            .getProperty("user.dir")
            .concat(File.separator)
            .concat("src")
            .concat(File.separator)
            .concat("test")
            .concat(File.separator)
            .concat("resources")
            .concat(File.separator)
            .concat("upload")
            .concat(File.separator);
    public static String pathDownload;
    public static String globalEvidence;
    public static String highlightElementShow;
    public static String urlTokenAuthorization;
    public static String urlSinistro;
    public static String urlControlePerdasConfiguracoes;
    public static String urlControlePerdas;
    public static String urlFerroviaComum;
    public static String urlFerroviaTrem;
    public static String urlAdministracao;
    public static String urlFrontendCirculacao;
    public static String urlSeleniumGrid;
    /*
    Data Base
     */
    public static String jooqDialect;
    public static String dbControlePerdasDriver;
    public static String dbControlePerdasUrl;
    public static String dbControlePerdasUsername;
    public static String dbControlePerdasPassword;
    public static String dbTranslogicDriver;
    public static String dbTranslogicUrl;
    public static String dbTranslogicUsername;
    public static String dbTranslogicPassword;
    //     SSO
    public static String generateToken;
    public static String passSSO;
    public static String userSSO;
    //    CONTROLE_PERDAS_ADM
    public static String userAdmin;
    public static String nameAdmin;
    public static String passAdmin;
    //    CONTROLE_PERDAS_EDICAO
    public static String userEdicao;
    public static String nameEdicao;
    public static String passEdicao;
    //    CONTROLE_PERDAS_CRIACAO
    public static String userCriacao;
    public static String nameCriacao;
    public static String passCriacao;
    //    CONTROLE_PERDAS_LEITURA
    public static String userLeitura;
    public static String nameLeitura;
    public static String passLeitura;
    private static String os;
    private static String browserName;

    public static void initializeProps() {
        PropertiesManager setupProperties = null;
        try {
            setupProperties = new PropertiesManager(WebDriverHelper.class.getClassLoader().getResource("setup.properties").toURI().getPath());
        } catch (URISyntaxException e) {
            try {
                throw new Exception("Erro ao carregar o aquivo properties");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        os = System.getProperty("os.name").toLowerCase();
        browserName = System.getProperty("browser");
        pathDownload = setupProperties.getProps().getProperty("pathDownload");
        globalEvidence = setupProperties.getProps().getProperty("globalEvidence");
        urlFrontendCirculacao = setupProperties.getProps().getProperty("urlCirculacao");
        urlSeleniumGrid = setupProperties.getProps().getProperty("url.selenium.grid");
        highlightElementShow = setupProperties.getProps().getProperty("highlightElementShow");
        urlTokenAuthorization = setupProperties.getProps().getProperty("urlTokenAuthorization");
        urlSinistro = setupProperties.getProps().getProperty("urlSinistro");
        urlControlePerdasConfiguracoes = setupProperties.getProps().getProperty("urlControlePerdasConfiguracoes");
        urlControlePerdas = setupProperties.getProps().getProperty("urlControlePerdas");
        urlAdministracao = setupProperties.getProps().getProperty("urlAdministracao");
        urlFerroviaComum = setupProperties.getProps().getProperty("urlFerroviaComum");
        urlFerroviaTrem = setupProperties.getProps().getProperty("urlFerroviaTrem");
        generateToken = setupProperties.getProps().getProperty("generateToken");
        userSSO = setupProperties.getProps().getProperty("userSSO");
        passSSO = setupProperties.getProps().getProperty("passSSO");
        /*
        Dados jooq
         */
        jooqDialect = setupProperties.getProps().getProperty("jooq.sql.dialect");
        /*
        dados controle de perdas
         */
        dbControlePerdasDriver = setupProperties.getProps().getProperty("db.controle-perdas.driver");
        dbControlePerdasUrl = setupProperties.getProps().getProperty("db.controle-perdas.url");
        dbControlePerdasUsername = setupProperties.getProps().getProperty("db.controle-perdas.username");
        dbControlePerdasPassword = setupProperties.getProps().getProperty("db.controle-perdas.password");
       /*
        dados translogic
         */
        dbTranslogicDriver = setupProperties.getProps().getProperty("db.translogic.driver");
        dbTranslogicUrl = setupProperties.getProps().getProperty("db.translogic.url");
        dbTranslogicUsername = setupProperties.getProps().getProperty("db.translogic.username");
        dbTranslogicPassword = setupProperties.getProps().getProperty("db.translogic.password");

        //    CONTROLE_PERDAS_ADM
        userAdmin = setupProperties.getProps().getProperty("userAdmin");
        nameAdmin = setupProperties.getProps().getProperty("nameAdmin");
        passAdmin = setupProperties.getProps().getProperty("passAdmin");
        //    CONTROLE_PERDAS_EDICAO
        userEdicao = setupProperties.getProps().getProperty("userEdicao");
        nameEdicao = setupProperties.getProps().getProperty("nameEdicao");
        passEdicao = setupProperties.getProps().getProperty("passEdicao");
        //    CONTROLE_PERDAS_CRIACAO
        userCriacao = setupProperties.getProps().getProperty("userCriacao");
        nameCriacao = setupProperties.getProps().getProperty("nameCriacao");
        passCriacao = setupProperties.getProps().getProperty("passCriacao");
        //    CONTROLE_PERDAS_LEITURA
        userLeitura = setupProperties.getProps().getProperty("userLeitura");
        nameLeitura = setupProperties.getProps().getProperty("nameLeitura");
        passLeitura = setupProperties.getProps().getProperty("passLeitura");
    }

}
