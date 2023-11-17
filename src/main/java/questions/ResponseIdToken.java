package questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseIdToken implements Question<String> {
    public static ResponseIdToken getId() {
        return new ResponseIdToken();
    }

    @Override
    public String answeredBy(Actor actor) {
        // Utilizar JSONPath para extraer el valor del campo "id"
        return SerenityRest.lastResponse().jsonPath().getString("id");
    }
}
