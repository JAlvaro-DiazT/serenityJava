package util;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

public class Url {

    private static final Dotenv dotenv = new DotenvBuilder().load();

    public static final String BASE_URL = dotenv.get("BASE_URL_API");

    public static String getBaseUrl() {
        return BASE_URL;
    }
}