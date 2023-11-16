package starter.stepdefinitions;

import dto.GenerateTokenRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import tasks.GetUser;
import tasks.PostGenerateToken;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static questions.ResponseBody.getValue;

public class StepDefinitionsGETUser {
    @Given("{actor} esta autenticado en el sistema")
    public void elAdminEstaAutenticadoEnElSistema(Actor actor) {
        actor.attemptsTo(PostGenerateToken.withInfo(new GenerateTokenRecord("admin","admin")));
    }

    @Given("{actor} no esta autorizado en el sistema")
    public void el_admin_no_esta_autorizado_en_el_sistema(Actor actor) {
        actor.remember("token","");
    }

    @Given("{actor} autenticado no tiene permisos")
    public void el_admin_autenticado_no_tiene_permisos(Actor actor) {
        actor.attemptsTo(PostGenerateToken.withInfo(new GenerateTokenRecord("alvaro","diaz")));
    }

    @When("{actor} solicita informacion de un usuario")
    public void elAdminSolicitaInformacionDeUnUsuario(Actor actor) {
        actor.attemptsTo(GetUser.withInfo("admin"));
    }

    @And("{actor} obtiene los datos del usuario")
    public void elAdminObtieneLosDatosDelUsuario(Actor actor) {
        actor.should(
                seeThat(getValue("usuario"),equalTo("admin")),
                seeThat(getValue("roles").asListOf(String.class),equalTo(List.of("admin")))
        );
    }

    @When("{actor} solicita informacion de usuario no existente")
    public void el_admin_solicita_informacion_en_un_recursos_no_existente(Actor actor) {
        actor.attemptsTo(GetUser.withInfo("camilo"));
    }
}
