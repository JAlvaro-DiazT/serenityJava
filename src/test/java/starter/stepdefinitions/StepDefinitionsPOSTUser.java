package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.RegisterUserRecord;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import questions.ResponseCode;
import tasks.PostRegisterUser;

import java.util.Collections;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitionsPOSTUser extends UIInteractions {
    private RegisterUserRecord registerUserRecord;
    Faker faker = new Faker();
    String user = faker.name().username();

    String password = faker.internet().password();

    @Given("existe un usuario no registrado")
    public void existe_un_usuario_no_registrado() {
        registerUserRecord = new RegisterUserRecord(user, password, Collections.singleton("user"));
    }
    @Given("intento registrarme con datos faltantes")
    public void intentoRegistrarmeConDatosFaltantes() {
        registerUserRecord = new RegisterUserRecord(user, "", Collections.singleton("user"));
    }

    @Given("intento registrarme como un usuario ya existente")
    public void intentoRegistrarmeComoUnUsuarioYaExistente() {
        registerUserRecord = new RegisterUserRecord("alvaro", "diaz", Collections.singleton("user"));
    }
    @When("{actor} utiliza el servicio de registro")
    public void alvaro_utiliza_el_servicio_de_registro(Actor actor) {
        actor.attemptsTo(
                PostRegisterUser.withInfo(registerUserRecord)
        );
    }
    @Then("{actor} deberia recibir un código de estado {int}")
    public void alvaro_deberia_recibir_un_código_de_estado(Actor actor, Integer response) {
        actor.should(
                seeThat("Codigo de solicitud de respuesta", ResponseCode.was(), equalTo(response))
        );
    }

}
