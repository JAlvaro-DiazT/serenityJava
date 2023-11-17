package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import tasks.GetTokenList;
import util.DataProvider;
import util.DataShared;

public class StepDefinitionsGetTokenList {
    @Shared
    private DataProvider dataProvider;
    @Shared
    private DataShared dataShared;

    @When("{actor} solicita el listado del tokens")
    public void el_admin_solicita_el_listado_del_tokens(Actor actor) {
        actor.attemptsTo(GetTokenList.withInfo(  ));
    }
}
