package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static util.Url.getBaseUrl;

public class GetTokenVerificationId implements Task {
    private final String id;
    public GetTokenVerificationId(String id) {

        this.id = id;
    }

    public static GetTokenVerificationId withInfo(String id) {

        return new GetTokenVerificationId(id);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Get.resource("/tokens/"+id)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .log().all())
        );
    }
}
