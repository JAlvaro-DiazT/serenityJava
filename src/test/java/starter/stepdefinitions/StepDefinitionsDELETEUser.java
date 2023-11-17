package starter.stepdefinitions;

import com.github.javafaker.Faker;
import dto.RegisterUserRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import tasks.DeleteUser;
import tasks.GetUser;
import tasks.PostRegisterUser;
import util.DataProvider;
import util.DataShared;

import java.util.List;

public class StepDefinitionsDELETEUser {
    private RegisterUserRecord registerUserRecord;

    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;


    @When("{actor} elimina un usuario")
    public void elAdminEliminaUnUsuario(Actor actor) {
        actor.attemptsTo(DeleteUser.withInfo(dataShared.usuario.usuario()));
    }


    @When("{actor} elimina un usuario que no existe")
    public void elAdminEliminaUnUsuarioQueNoExiste(Actor actor) {
        actor.attemptsTo(GetUser.withInfo(dataProvider.getUsuarioIncompleto().usuario()));
    }
}
