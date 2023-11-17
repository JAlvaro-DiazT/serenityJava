package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static util.Url.getBaseUrl;

public class GetUser implements Task {
    private final String usuario;
    public GetUser(String usuario) {

        this.usuario = usuario;
    }

    public static GetUser withInfo(String usuario) {

        return new GetUser(usuario);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Get.resource("/usuarios/"+usuario)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .log().all())
        );
    }
}
