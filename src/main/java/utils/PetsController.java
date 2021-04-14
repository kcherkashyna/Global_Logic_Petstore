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
import static org.hamcrest.Matchers.containsString;
import static utils.Constants.PET_ENDPOINT;

public class PetsController {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private static final String PET_STATUS = "status";
    private static final String FIND_BY_STATUS_LINK = "/findByStatus";
    private static final String PET_ID = "petId";
    private static final String PET_ID_LINK = "/{petId}";


    public PetsController() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(Constants.BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

    }

    public Pet addNewPetAndCheckStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .body(pet)
                .post(PET_ENDPOINT).as(Pet.class);
        responseSpecification.then().statusCode(code);
        return pet;
    }

    public Pet addNewPetUsingWrongMethodAndCheckStatusCode(final Pet pet, final UnreservedMethods method, final int code) {
        if (method.equals(UnreservedMethods.HEAD)) {
            given(requestSpecification)
                    .body(pet)
                    .head(PET_ENDPOINT).as(Pet.class);
            responseSpecification.then().statusCode(code);
        } else if (method.equals(UnreservedMethods.PATCH)) {
            given(requestSpecification)
                    .body(pet)
                    .patch(PET_ENDPOINT).as(Pet.class);
            responseSpecification.then().statusCode(code);
        } else if (method.equals(UnreservedMethods.OPTIONS)) {
            given(requestSpecification)
                    .body(pet)
                    .options(PET_ENDPOINT).as(Pet.class);
            responseSpecification.then().statusCode(code);
        }
        return pet;
    }

    public Pet updatePetAndCheckStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .body(pet)
                .put(PET_ENDPOINT).as(Pet.class);
        responseSpecification.then().statusCode(code);
        return pet;
    }

    public void getPetsByStatusAndCheckStatusCode(final Pet pet, final Status status, final int code) {
        if (status.equals(Status.available)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.available.toString())
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
            //Constant
        } else if (status.equals(Status.pending)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.pending.toString())
                    //Constant
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
        } else if (status.equals(Status.sold)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.sold.toString())
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
        } else if (status.equals(Status.invalidStatus)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.invalidStatus.toString())
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
        } else if (status.equals(Status.nullStatus)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.nullStatus)
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
        }
    }

    public void getPetsByStatusUsingWrongMethodAndCheckStatusCode(final Pet pet, final UnreservedMethods method, final int code) {
        if (method.equals(UnreservedMethods.HEAD)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.available.toString())
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().and().statusCode(code);
        } else if (method.equals(UnreservedMethods.PATCH)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.pending.toString())
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
        } else if (method.equals(UnreservedMethods.OPTIONS)) {
            given(requestSpecification)
                    .queryParam(PET_STATUS, Status.sold.toString())
                    .get(PET_ENDPOINT + FIND_BY_STATUS_LINK)
                    .then().statusCode(code);
        }
    }

    public Pet findPetByIdAndCheckStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .pathParam(PET_ID, pet.getId())
                .get(PET_ENDPOINT + PET_ID_LINK).as(Pet.class);
        responseSpecification.then().statusCode(code);
        return pet;
    }

    public Pet findPetByIdUsingInvalidMethodAndCheckStatusCode(final Pet pet, final UnreservedMethods method, final int code) {
        if (method.equals(UnreservedMethods.HEAD)) {
            given(requestSpecification)
                    .pathParam(PET_ID, pet.getId())
                    .head(PET_ENDPOINT + PET_ID_LINK).as(Pet.class);
            responseSpecification.then().statusCode(code);
        } else if (method.equals(UnreservedMethods.PATCH)) {
            given(requestSpecification)
                    .pathParam(PET_ID, pet.getId())
                    .patch(PET_ENDPOINT + PET_ID_LINK).as(Pet.class);
            responseSpecification.then().statusCode(code);
        } else if (method.equals(UnreservedMethods.OPTIONS)) {
            given(requestSpecification)
                    .pathParam(PET_ID, pet.getId())
                    .options(PET_ENDPOINT + PET_ID_LINK).as(Pet.class);
            responseSpecification.then().statusCode(code);
        }
        return pet;
    }

    public Pet updatePetWithFormDataAndCheckStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .pathParam(PET_ID, pet.getId())
                .post(PET_ENDPOINT + PET_ID_LINK).as(Pet.class);
        responseSpecification.then().statusCode(code);
        return pet;
    }

    public void deletePetAndCheckStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .pathParam(PET_ID, pet.getId())
                .delete(PET_ENDPOINT + PET_ID_LINK)
                .then().statusCode(code);
    }

    public void verifyPetDeletedAndCheckStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .pathParam(PET_ID, pet.getId())
                .get(PET_ENDPOINT + PET_ID_LINK)
                .then().statusCode(code).and().body(containsString("Pet not found"));
    }

    public void getPetAndVerifyStatusCode(final Pet pet, final int code) {
        given(requestSpecification)
                .pathParam(PET_ID, pet.getId())
                .get(PET_ENDPOINT + PET_ID_LINK)
                .then().statusCode(code);
    }

}