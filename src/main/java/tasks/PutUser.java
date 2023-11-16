package tasks;

import dto.PatchUserRecord;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class PutUser implements Task {
    private final PatchUserRecord patchUserRecord;
    private final String username;



    public PutUser(PatchUserRecord patchUserRecord, String username) {
        this.username = username;
        this.patchUserRecord = patchUserRecord;
    }

    public static PutUser withInfo(PatchUserRecord patchUserRecord, String username) {

        return new PutUser(patchUserRecord, username);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.whoCan(CallAnApi.at("http://localhost:8090/api"));

        actor.attemptsTo(
                Put.to("/usuarios/"+username)
                        .with(request -> request.
                                headers("Authorization",actor.gaveAsThe("token"))
                                .contentType(ContentType.JSON)
                                .body(patchUserRecord, ObjectMapperType.GSON)
                                .log().all())

        );
    }
}
