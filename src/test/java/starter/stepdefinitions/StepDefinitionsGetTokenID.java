package starter.stepdefinitions;

import dto.GenerateTokenRecord;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Shared;
import net.serenitybdd.screenplay.Actor;
import questions.ResponseIdToken;
import tasks.GetTokenVerificationId;
import tasks.GetUser;
import tasks.PostGenerateToken;
import util.DataProvider;
import util.DataShared;

public class StepDefinitionsGetTokenID {
    @Shared
    private DataShared dataShared;
    @Shared
    private DataProvider dataProvider;

    @When("{actor} genere un token para el nuevo usuario")
    public void genere_un_token_nuevo_usuario(Actor actor) {
        dataShared.credenciales = new GenerateTokenRecord(dataShared.usuario.usuario(), dataShared.usuario.clave());
        actor.attemptsTo(
                PostGenerateToken.withInfo(dataShared.credenciales)
        );
    }

    @And("{actor} obtenga la informacion por medio del id")
    public void elAdminObtengaLaInformacionPorMedioDelId(Actor actor) {
        String id = actor.asksFor(ResponseIdToken.getId());
        actor.attemptsTo(GetTokenVerificationId.withInfo(id));
    }
}
