package starter.stepdefinitions;

import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import tasks.GetTokenList;
import tasks.GetUserList;
import util.DataProvider;
import util.DataShared;

public class StepDefinitionsGetUserList {
    @Shared
    private DataProvider dataProvider;
    @Shared
    private DataShared dataShared;

    @When("{actor} solicita el listado de los usuarios")
    public void el_admin_solicita_el_listado_de_los_usuarios(Actor actor) {
        actor.attemptsTo(GetUserList.withInfo(  ));
    }
}
