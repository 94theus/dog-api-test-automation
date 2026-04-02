package com.testeautomacaoapi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalParameters {

    private static final Properties properties = new Properties();

    public static String ENVIRONMENT;
    public static String BASE_URL;
    public static String REPORT_PATH;
    public static String REPORT_NAME;

    static {
        loadProperties();
    }

    private GlobalParameters() {
    }

    private static void loadProperties() {
        InputStream inputStream = null;

        try {
            inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("globalParameters.properties");

            if (inputStream == null) {
                throw new RuntimeException("Arquivo globalParameters.properties não encontrado no classpath.");
            }

            properties.load(inputStream);

            ENVIRONMENT = properties.getProperty("environment");
            BASE_URL = properties.getProperty("baseUrl");
            REPORT_PATH = properties.getProperty("report.path");
            REPORT_NAME = properties.getProperty("report.name");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo globalParameters.properties", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao fechar o arquivo globalParameters.properties", e);
                }
            }
        }
    }
}