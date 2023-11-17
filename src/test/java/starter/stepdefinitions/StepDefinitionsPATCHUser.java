package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.PatchUserRecord;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import tasks.PatchUser;
import tasks.PostRegisterUser;
import util.DataProvider;
import util.DataShared;

import java.util.List;


public class StepDefinitionsPATCHUser {
    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    @When("{actor} solicita actualizar la clave temporalmente de un usuario")
    public void elAdminSolicitaActualizarLaClaveTemporalmenteDeUnUsuario(Actor actor) {
        String newPassword = dataProvider.getPassword();
        actor.attemptsTo(
                PatchUser.withInfo(new PatchUserRecord(dataShared.usuario.clave(), newPassword, newPassword),dataShared.usuario.usuario()));
    }

    @When("{actor} solicita actualizar la clave temporalmente con datos imcompletos")
    public void elAdminSolicitaActualizarLaClaveTemporalmenteConDatosImcompletos(Actor actor) {
        String newPassword = dataProvider.getPassword();
        actor.attemptsTo(PatchUser.withInfo(new PatchUserRecord(dataShared.usuario.clave(), "", newPassword),dataShared.usuario.usuario()));
    }

    @When("{actor} solicita actualizar usuario que no existe")
    public void elAdminSolicitaActualizarUsuarioQueNoExiste(Actor actor) {
        String newPassword = dataProvider.getPassword();
        actor.attemptsTo(PatchUser.withInfo(new PatchUserRecord(dataProvider.getPassword(), newPassword, newPassword), dataProvider.getUsuarioIncompleto().usuario()));
    }
}
