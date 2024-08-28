package org.example.stepsdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.steps.SauceLabsStep;
import org.junit.Assert;

import static org.example.core.AppiumManager.*;

public class SauceLabsStepDef {

    private SauceLabsStep sauceLabsStep(){
        return new SauceLabsStep();
    }

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicaciónDeSauceLabs() {
        Assert.assertTrue(sauceLabsStep().validateScreen());
        screenShot();
    }

    @And("valido que carguen correctamente los productos en la galería")
        public void validoQueCarguenCorrectamenteLosProductosEnLaGalería() {
    }

    @When("agrego unidades {int} del siguiente producto {string}")
        public void agregoUnidadesDelSiguienteProducto(int unidades, String producto) {
    }

    @Then("valido el carrito de compra actualice correctamente")
        public void validoElCarritoDeCompraActualiceCorrectamente() {
    }

}
