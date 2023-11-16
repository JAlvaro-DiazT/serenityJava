package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.then;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
@Subject("the response schema matches the JSON schema defined  in file '#fieldName'")
public class TokenValidation implements Question<Boolean> {

    private final String schemaFileName;

    public TokenValidation(String schemaFileName) {
        this.schemaFileName = schemaFileName;
    }

    public static TokenValidation theTokenSchemaIs(String schemaFileName) {
        return new TokenValidation(schemaFileName);
    }
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse("the response schema matches the JSON schema",
                        response -> response.assertThat()
                                .body(matchesJsonSchemaInClasspath("schemas/"+schemaFileName+".json"))
                )
        );
        then().log().ifValidationFails();

        return true;
    }
}
