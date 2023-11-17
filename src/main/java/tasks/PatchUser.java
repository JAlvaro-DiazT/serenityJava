package tasks;

import dto.PatchUserRecord;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import static util.Url.getBaseUrl;

public class PatchUser implements Task {
    private final PatchUserRecord patchUserRecord;
    private final String username;



    public PatchUser(PatchUserRecord patchUserRecord, String username) {
        this.username = username;
        this.patchUserRecord = patchUserRecord;
    }

    public static PatchUser withInfo(PatchUserRecord patchUserRecord, String username) {

        return new PatchUser(patchUserRecord, username);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Patch.to("/usuarios/"+username)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .contentType(ContentType.JSON)
                                .body(patchUserRecord, ObjectMapperType.GSON)
                                .log().all())

        );
    }
}
