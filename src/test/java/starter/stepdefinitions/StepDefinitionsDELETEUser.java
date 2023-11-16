package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import tasks.DeleteUser;
import tasks.GetUser;
import tasks.PostRegisterUser;

import java.util.List;

public class StepDefinitionsDELETEUser {
    private RegisterUserRecord registerUserRecord;
    Faker faker = new Faker();
    String user = faker.name().username();
    String password = faker.internet().password();
    @And("{actor} registra un usuario temporal")
    public void elAdminRegistraUnUsuarioTemporal(Actor actor) {
        registerUserRecord = new RegisterUserRecord(user, password, List.of("user"));
        actor.attemptsTo(
                PostRegisterUser.withInfo(registerUserRecord)
        );
    }

    @When("{actor} elimina un usuario")
    public void elAdminEliminaUnUsuario(Actor actor) {
        actor.attemptsTo(DeleteUser.withInfo(user));
    }


    @When("{actor} elimina un usuario que no existe")
    public void elAdminEliminaUnUsuarioQueNoExiste(Actor actor) {
        actor.attemptsTo(GetUser.withInfo("camilo"));
    }
}
