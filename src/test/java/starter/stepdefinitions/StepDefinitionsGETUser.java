package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.Datum;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.GetUsersQuestion;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StepDefinitionsGETUser {
    private String restApiURL = "https://reqres.in/api";;
    Actor julian;
    Datum user;
    @Given("Estoy en la pagina regres.in")
    public void estoy_en_la_pagina_regres_in() {
        julian = Actor.named("Julian the trainer")
                .whoCan(CallAnApi.at(restApiURL));
    }
    @When("Envio la informacion requerida")
    public void envio_la_informacion_requerida() {
        julian.attemptsTo(
                GetUsers.fromPage(1)
        );
    }
    @Then("Deberia no estar nulo el usuario")
    public void deberia_no_estar_nulo_el_usuario() {
        user = new GetUsersQuestion().answeredBy(julian)
                .getData().stream().filter(x -> x.getId() == 1).findFirst().orElse(null);

        julian.should(
                seeThat("El usuario no es nulo", actor -> user, notNullValue())
        );
    }
    @Then("Deberia encontrar el correo con el id ingresado")
    public void deberia_encontrar_el_correo_con_el_id_ingresado() {

        julian.should(
                seeThat("El email del usuario", actor -> user.getEmail(), equalTo("george.bluth@reqres.in"))
        );

    }
}
