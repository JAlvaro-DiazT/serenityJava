package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import tasks.PostRegisterUser;

import java.util.List;

public class StepDefinitionsPUTUser {
    private RegisterUserRecord registerUserRecord;
    Faker faker = new Faker();
    String user = faker.name().username();

    String password = faker.internet().password();

    @And("{actor} registra un usuario")
    public void elAdminRegistraUnUsuario(Actor actor) {
        registerUserRecord = new RegisterUserRecord(user, password, List.of("user"));
        actor.attemptsTo(
                PostRegisterUser.withInfo(registerUserRecord)
        );
    }

    @When("El admin solicita actualizar los datos del usuario")
    public void elAdminSolicitaActualizarLosDatosDelUsuario() {

    }
}
