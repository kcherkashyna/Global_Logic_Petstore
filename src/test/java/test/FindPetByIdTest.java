package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petstore.Category;
import petstore.Pet;
import petstore.Status;
import petstore.Tag;
import utils.PetsController;
import utils.UnreservedMethods;

import java.util.Collections;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FindPetByIdTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private PetsController petsController;
    private Pet pet;
    private String id = "1";
    private String petName = "Baaaarsik";
    private String categoryName = "cats";
    private String tagName = "pallas's cat";
    private String status = Status.available.toString();


    @BeforeMethod
    public void beforeMethod() {
        petsController = new PetsController();
        pet = new Pet();
        pet.setId(id);
        pet.setName(petName);
        pet.setPhotoUrls(Collections.singletonList(PHOTO_URL));
        pet.setStatus(Status.available);
        pet.setTags(Collections.singletonList(new Tag(id, tagName)));
        pet.setCategory(new Category(id, categoryName));
    }

    @Test(description = "User finds pet by id")
    public void findPet() {
        Pet petResponse = petsController.findPetByIdAndCheckStatusCode(pet, HTTP_OK);
        assertThat(petResponse, equalTo(pet));
        assertThat(petResponse.getId(), is(pet.getId()));
        assertThat(petResponse.getName(), is(pet.getName()));
        assertThat(petResponse.getStatus(), is(pet.getStatus()));
    }

    @Test(description = "User tries to find pet with invalid parameter(s)")
    public void findPetWithInvalidParameter() {
        pet.setId("769769");
        petsController.findPetByIdAndCheckStatusCode(pet, HTTP_BAD_REQUEST);
    }

    @Test(description = "User tries to find pet with empty parameter(s)")
    public void findPetWithEmptyParameter() {
        pet.setName("");
        petsController.findPetByIdAndCheckStatusCode(pet, HTTP_NOT_FOUND);
    }

    @Test(description = "User tries to find pet using invalid method")
    public void findPetWithInvalidMethod() {
        petsController.findPetByIdUsingInvalidMethodAndCheckStatusCode(pet, UnreservedMethods.OPTIONS, HTTP_BAD_METHOD);
    }
}