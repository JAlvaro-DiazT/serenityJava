package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import questions.ResponseCode;
import questions.TokenValidation;
import tasks.PostRegisterUser;
import util.DataProvider;
import util.DataShared;

import java.util.Collections;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static questions.ResponseBody.getValue;

public class StepDefinitionsPOSTUser extends UIInteractions {


    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    @Given("existe un usuario no registrado")
    public void existe_un_usuario_no_registrado() {
        dataShared.usuario = dataProvider.getUser();
    }
    @Given("intento registrarme con datos faltantes")
    public void intentoRegistrarmeConDatosFaltantes() {
        dataShared.usuario = new RegisterUserRecord("alvaro", "", List.of("user"));
    }

    @Given("intento registrarme como un usuario ya existente")
    public void intentoRegistrarmeComoUnUsuarioYaExistente() {

        dataShared.usuario = new RegisterUserRecord("alvaro", "diaz", List.of("user"));
    }
    @When("{actor} utiliza el servicio de registro")
    public void alvaro_utiliza_el_servicio_de_registro(Actor actor) {
        actor.attemptsTo(
                PostRegisterUser.withInfo(dataShared.usuario)
        );
    }
    @Then("{actor} deberia recibir un código de estado {int}")
    public void alvaro_deberia_recibir_un_código_de_estado(Actor actor, Integer response) {
        actor.should(
                seeThat("Codigo de solicitud de respuesta", ResponseCode.was(), equalTo(response))
        );
    }

    @And("{actor} deberia recibir los datos del usuario")
    public void deberiaRecibirLosDatosDelUsuario(Actor actor) {
        actor.should(
                seeThat(getValue("usuario"),equalTo(dataShared.usuario.usuario())),
                seeThat(getValue("roles").asListOf(String.class),equalTo(dataShared.usuario.roles()))
        );
    }

    @And("{actor} obtiene el formato de respuesta {string} del esquema")
    public void obtieneElFormatoDeRespuestaDelEsquema(Actor actor, String schema) {
        actor.should(
                seeThat(TokenValidation.theTokenSchemaIs(schema))
        );
    }
}
