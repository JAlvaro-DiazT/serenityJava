package tasks;

import dto.PatchUserRecord;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static util.Url.getBaseUrl;

public class PutUser<T> implements Task {
    private final String currentUser;
    private final T user;

    public PutUser(String currentUser, T user) {
        this.user = user;
        this.currentUser = currentUser;
    }

    public static <T> PutUser<T> withInfo(String currentUser, T user) {

        return new PutUser<T>(currentUser, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Put.to("/usuarios/"+currentUser)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .contentType(ContentType.JSON)
                                .body(user, ObjectMapperType.GSON)
                                .log().all())

        );
    }
}
