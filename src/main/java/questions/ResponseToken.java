package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseToken implements Question<String> {
    public static Question<String> get() {
        return new ResponseToken();
    }
    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().getHeader("Authorization");
    }
}
