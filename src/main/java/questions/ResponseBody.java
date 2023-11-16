package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseBody<T> implements Question<T> {
    private final String path;

    public ResponseBody(String path) {
        this.path = path;
    }

    public static <T> Question<T> getValue(String path) {
        return new ResponseBody<T>(path);
    }
    @Override
    public T answeredBy(Actor actor) {
        return SerenityRest.lastResponse().getBody().jsonPath().get(path);
    }
}
