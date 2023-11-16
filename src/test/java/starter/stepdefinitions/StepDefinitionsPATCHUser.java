package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.PatchUserRecord;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import tasks.PatchUser;
import tasks.PostRegisterUser;

import java.util.List;


public class StepDefinitionsPATCHUser {
    private RegisterUserRecord registerUserRecord;
    Faker faker = new Faker();
    String user = faker.name().username();
    String password = faker.internet().password();

    String newPassword = faker.internet().password();
    @And("{actor} registra un nuevo usuario")
    public void elAdminRegistraUnNuevoUsuario(Actor actor) {
        registerUserRecord = new RegisterUserRecord(user, password, List.of("user"));
        actor.attemptsTo(
                PostRegisterUser.withInfo(registerUserRecord)
        );
    }

    @When("{actor} solicita actualizar la clave temporalmente de un usuario")
    public void elAdminSolicitaActualizarLaClaveTemporalmenteDeUnUsuario(Actor actor) {
        actor.attemptsTo(PatchUser.withInfo(new PatchUserRecord(password, newPassword, newPassword),user));
    }


    @When("{actor} solicita actualizar la clave temporalmente con datos imcompletos")
    public void elAdminSolicitaActualizarLaClaveTemporalmenteConDatosImcompletos(Actor actor) {
        actor.attemptsTo(PatchUser.withInfo(new PatchUserRecord(password, "", newPassword),user));
    }

    @When("{actor} solicita actualizar usuario que no existe")
    public void elAdminSolicitaActualizarUsuarioQueNoExiste(Actor actor) {
        actor.attemptsTo(PatchUser.withInfo(new PatchUserRecord(password, newPassword, newPassword),"camilo"));
    }
}
