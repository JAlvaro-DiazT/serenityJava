package tasks;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;

import dto.RegisterUserRecord;

import static util.Url.getBaseUrl;

public class PostRegisterUser implements Task {
    private final RegisterUserRecord registerUserRecord;


    public PostRegisterUser(RegisterUserRecord registerUserRecord) {
        this.registerUserRecord = registerUserRecord;
    }

    public static PostRegisterUser withInfo(RegisterUserRecord registerUserRecord) {

        return new PostRegisterUser(registerUserRecord);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

            actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Post.to("/usuarios")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(registerUserRecord, ObjectMapperType.GSON)
                        )
                        .with(request -> request.log().all())
        );
    }
}
