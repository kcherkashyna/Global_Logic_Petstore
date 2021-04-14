package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import petstore.Pet;
import petstore.Status;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Constants.PET_ENDPOINT;

public class PetsController {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public PetsController() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(Constants.BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

    public Pet addNewPet(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .post(PET_ENDPOINT).as(Pet.class);
    }

    public Pet addNewPetUsingWrongMethod(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .patch(PET_ENDPOINT).as(Pet.class);
    }

    public void getPetsByStatus(Pet pet, String status, String method, int code) {
        if (status == "1" && method == "get") {
            given(requestSpecification)
                    .queryParam("status", Status.available.toString())
                    //Constant
                    .get(PET_ENDPOINT + "/findByStatus")
                    .then().log().all()
                    .assertThat().spec(responseSpecification).and().statusCode(code);
            //Constant
        } else if (status == "2" && method == "get") {
            given(requestSpecification)
                    .queryParam("status", Status.pending.toString())
                    //Constant
                    .get(PET_ENDPOINT + "/findByStatus")
                    .then().log().all()
                    .assertThat().spec(responseSpecification).and().statusCode(code);
        } else if (status == "3" && method == "get") {
            given(requestSpecification)
                    .queryParam("status", Status.sold.toString())
                    .get(PET_ENDPOINT + "/findByStatus")
                    .then().log().all()
                    .assertThat().spec(responseSpecification).and().statusCode(code);
        } else if (status == "4" && method == "get") {
            given(requestSpecification)
                    .queryParam("status", Status.invalidStatus.toString())
                    .get(PET_ENDPOINT + "/findByStatus")
                    .then().log().all()
                    .assertThat().spec(responseSpecification).and().statusCode(code);
        } else if (status == "" && method == "get") {
            given(requestSpecification)
                    .queryParam("status", "")
                    .get(PET_ENDPOINT + "/findByStatus")
                    .then().log().all()
                    .assertThat().spec(responseSpecification).and().statusCode(code);
        } else {
            given(requestSpecification)
                    .queryParam("status", Status.available.toString())
                    .patch(PET_ENDPOINT + "/findByStatus")
                    .then().log().all()
                    .assertThat().statusCode(code);
        }
    }

    public void deletePet(Pet pet) {
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .delete(PET_ENDPOINT + "/{petId}");
    }

    public void verifyPetDeleted(Pet pet) {
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .get(PET_ENDPOINT + "/{petId}")
                .then()
                .body(containsString("Pet not found"));
    }

    public Pet findPetById(Pet pet, String method) {
        if (method == "get") {
            given(requestSpecification)
                    .pathParam("petId", pet.getId())
                    .get(PET_ENDPOINT + "/{petId}").as(Pet.class);
        } else {
            given(requestSpecification)
                    .pathParam("petId", pet.getId())
                    .patch(PET_ENDPOINT + "/{petId}").as(Pet.class);
        }
        return pet;
    }

    public Pet updatePet(Pet pet) {
        return given(requestSpecification)
                .body(pet)
                .put(PET_ENDPOINT).as(Pet.class);
    }

    public Pet updatePetByPost(Pet pet) {
        return given(requestSpecification)
                .pathParam("petId", pet.getId())
                .post(PET_ENDPOINT + "/{petId}").as(Pet.class);
    }

    public void verifyStatusCode(Pet pet, int code) {
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .get(PET_ENDPOINT + "/{petId}")
                .then().statusCode(code);
    }

}