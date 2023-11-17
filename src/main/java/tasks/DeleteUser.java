package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static util.Url.getBaseUrl;

public class DeleteUser implements Task {
    private final String username;



    public DeleteUser(String username) {
        this.username = username;
    }

    public static DeleteUser withInfo(String username) {

        return new DeleteUser(username);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Delete.from("/usuarios/"+username)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .log().all())

        );
    }
}
