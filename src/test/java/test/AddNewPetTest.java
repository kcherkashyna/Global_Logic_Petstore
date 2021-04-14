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

public class AddNewPetTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private PetsController petsController;
    private Pet pet;
    //TODO replace with constants
    private static final String id = "1";
    private static final String petName = "Baaaarsik";
    private static final String categoryName = "cats";
    private static final String tagName = "pallas's cat";


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

    @Test(description = "User adds a new pet to the store with valid input parameters")
    public void addNewPet() {
        Pet petResponse = petsController.addNewPetAndCheckStatusCode(pet, HTTP_OK);
        assertThat(petResponse, equalTo(pet));
        assertThat(petResponse, is(pet));
        assertThat(petResponse.getId(), is(pet.getId()));
        assertThat(petResponse.getName(), is(pet.getName()));
        assertThat(petResponse.getStatus(), is(pet.getStatus()));
    }

    @Test(description = "User tries to add a new pet to the store with invalid parameter(s)")
    public void addNewPetWithInvalidParameter() {
        pet.setId("-7");
        petsController.addNewPetAndCheckStatusCode(pet, HTTP_BAD_REQUEST);
    }

    @Test(description = "User tries to add a new pet to the store with empty parameter(s)")
    public void addNewPetWithEmptyParameter() {
        pet.setName("");
        petsController.addNewPetAndCheckStatusCode(pet, HTTP_NOT_FOUND);
    }

    @Test(description = "User tries to add a new pet to the store with invalid method")
    public void addNewPetWithInvalidMethod() {
        petsController.addNewPetUsingWrongMethodAndCheckStatusCode(pet, UnreservedMethods.PATCH, HTTP_BAD_METHOD);
    }

}

