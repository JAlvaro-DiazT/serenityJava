package tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static util.Url.getBaseUrl;

public class GetTokenList implements Task {
    public static GetTokenList withInfo() {

        return new GetTokenList();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Get.resource("/tokens")
                        .with(requestSpecification -> requestSpecification
                                .headers("Authorization", actor.gaveAsThe("token"))

                        )
                        .with(request -> request.log().all())
        );
        actor.remember("requestTokens", SerenityRest.lastResponse().getBody().asString());
    }
}
