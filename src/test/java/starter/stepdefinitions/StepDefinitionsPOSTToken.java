package starter.stepdefinitions;

import dto.GenerateTokenRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import org.hamcrest.Matchers;
import questions.ResponseCode;
import questions.ResponseToken;
import questions.TokenValidation;
import tasks.PostGenerateToken;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitionsPOSTToken extends UIInteractions {
    private GenerateTokenRecord generateTokenRecord;

    @Given("un usuario registrado desea acceder al sistema")
    public void un_usuario_registrado_desea_acceder_al_sistema() {
        generateTokenRecord = new GenerateTokenRecord("admin", "admin");
    }

    @Given("un usuario registrado diligencia datos incompletos")
    public void unUsuarioRegistradoDiligenciaDatosIncompletos() {
        generateTokenRecord = new GenerateTokenRecord("", "diaz");
    }

    @Given("un usuario registrado diligencia datos incorrectos")
    public void unUsuarioRegistradoDiligenciaDatosIncorrectos() {
        generateTokenRecord = new GenerateTokenRecord("Alvaro", "di");
    }

    @When("{actor} genera un token")
    public void genere_un_token(Actor actor) {
        actor.attemptsTo(
                PostGenerateToken.withInfo(generateTokenRecord)
        );
    }
    @Then("{actor} deberia obtener un codigo de estado {int}")
    public void deberia_obtener_un_codigo_de_estado(Actor actor, Integer response) {
        actor.should(
                seeThat("Codigo de solicitud de respuesta", ResponseCode.was(), equalTo(response))
        );

    }

    @And("El token de {actor} obtiene de respuesta el codigo del token")
    public void elTokenDeObtieneDeRespuestaElCodigoDelToken(Actor actor) {
        actor.should(
                seeThat(ResponseToken.get(), Matchers.not(Matchers.emptyString()))
        );
    }

    @And("El token de {actor} obtiene de respuesta el {string} del esquema")
    public void elTokenDeObtieneDeRespuestaElDelEsquema(Actor actor, String schema) {
        actor.should(
                seeThat(TokenValidation.theTokenSchemaIs(schema))
        );
    }
}
