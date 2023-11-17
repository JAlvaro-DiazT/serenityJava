package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.PatchUserRecord;
import dto.RegisterUserRecord;
import dto.UsuarioImcompletoRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import tasks.PatchUser;
import tasks.PostRegisterUser;
import tasks.PutUser;
import util.DataProvider;
import util.DataShared;

import java.util.List;

public class StepDefinitionsPUTUser {
    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    @When("{actor} solicita actualizar un usuario")
    public void el_admin_solicita_actualizar_el_nombre_de_un_usuario(Actor actor) {

        dataShared.usuario = new RegisterUserRecord(dataShared.usuario.usuario(), dataProvider.getPassword(), dataShared.usuario.roles());
        actor.attemptsTo(PutUser.withInfo(dataShared.usuario.usuario(), dataShared.usuario));
    }

    @When("{actor} solicita actualizar un usuario con datos incompletos")
    public void elAdminSolicitaActualizarLaClaveDeUnUsuarioConDatosIncompletos(Actor actor) {
        var usuario = new UsuarioImcompletoRecord(dataShared.usuario.usuario());
        actor.attemptsTo(PutUser.withInfo(dataShared.usuario.usuario(), usuario));
    }
    /*
    @When("{actor} solicita actualizar la clave temporalmente de un usuario")
    public void elAdminSolicitaActualizarLaClaveTemporalmenteDeUnUsuario(Actor actor) {
        actor.attemptsTo(PatchUser.withInfo(new PatchUserRecord(password, newPassword, newPassword),user));
    }
?*/
}
