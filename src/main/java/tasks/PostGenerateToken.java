package tasks;

import dto.GenerateTokenRecord;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static util.Url.getBaseUrl;

public class PostGenerateToken implements Task {
    private final GenerateTokenRecord generateTokenRecord;


    public PostGenerateToken(GenerateTokenRecord generateTokenRecord) {
        this.generateTokenRecord = generateTokenRecord;
    }

    public static PostGenerateToken withInfo(GenerateTokenRecord generateTokenRecord) {

        return new PostGenerateToken(generateTokenRecord);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.whoCan(CallAnApi.at(getBaseUrl()));

        actor.attemptsTo(
                Post.to("/tokens")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(generateTokenRecord, ObjectMapperType.GSON)
                        )
                        .with(request -> request.log().all())
        );
        actor.remember("token", SerenityRest.lastResponse().getHeader("Authorization"));
        //String token = parseJsonObject(lastResponse().getBody().asString()).get("token").getAsString();
    }
}
