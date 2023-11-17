package starter.stepdefinitions;

import dto.GenerateTokenRecord;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import tasks.GetUser;
import tasks.PostGenerateToken;
import tasks.PostRegisterUser;
import util.DataProvider;
import util.DataShared;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static questions.ResponseBody.getValue;

public class StepDefinitionsGETUser {
    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;
    @Given("{actor} esta autenticado en el sistema")
    public void elAdminEstaAutenticadoEnElSistema(Actor actor) {
        actor.attemptsTo(PostGenerateToken.withInfo(dataProvider.getAdminCredential()));
    }

    @Given("{actor} no esta autorizado en el sistema")
    public void el_admin_no_esta_autorizado_en_el_sistema(Actor actor) {
        actor.remember("token","");
        dataShared.usuario = dataProvider.getUser();
    }

    @Given("{actor} autenticado no tiene permisos")
    public void el_admin_autenticado_no_tiene_permisos(Actor actor) {
        dataShared.usuario = dataProvider.getUser();
        actor.attemptsTo(PostGenerateToken.withInfo(new GenerateTokenRecord("alvaro","diaz")));
    }

    @And("{actor} registra un usuario")
    public void elAdminRegistraUnUsuario(Actor actor) {
        dataShared.usuario = dataProvider.getUser();
        actor.attemptsTo(
                PostRegisterUser.withInfo(dataShared.usuario)
        );
    }

    @When("{actor} solicita informacion del usuario")
    public void elAdminSolicitaInformacionDeUnUsuario(Actor actor) {

        actor.attemptsTo(GetUser.withInfo(dataShared.usuario.usuario()));
    }

    @And("{actor} obtiene los datos del usuario")
    public void elAdminObtieneLosDatosDelUsuario(Actor actor) {
        actor.should(
                seeThat(getValue("usuario"),equalTo(dataShared.usuario.usuario())),
                seeThat(getValue("roles").asListOf(String.class),equalTo(dataShared.usuario.roles()))
        );
    }

    @When("{actor} solicita informacion de usuario no existente")
    public void el_admin_solicita_informacion_en_un_recursos_no_existente(Actor actor) {
        actor.attemptsTo(GetUser.withInfo(dataProvider.getUsername()));
    }
}
