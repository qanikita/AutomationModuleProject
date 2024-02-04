package data;

import libs.ConfigProvider;

// Класс для хранения тестовых данных
// которые будут использоваться в тестах МНОГО РАЗ
public class TestData {

    public final static String VALID_EMAIL = ConfigProvider.configHiddenProperties.email();
    public final static String VALID_PASSWORD = ConfigProvider.configHiddenProperties.password();
}
