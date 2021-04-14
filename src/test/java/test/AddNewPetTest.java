package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petstore.Category;
import petstore.Pet;
import petstore.Status;
import petstore.Tag;
import utils.PetsController;

import java.util.Collections;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddNewPetTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private PetsController petsController;
    private Pet pet;
    //TODO replace with constants
    private String id = "1";
    private String petName = "Baaaarsik";
    private String categoryName = "cats";
    private String tagName = "pallas's cat";


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


    @Test(priority = 1, description = "User adds a new pet to the store with valid input parameters")
    public void addNewPet() {
        Pet petResponse = petsController.addNewPet(pet, "post");
        petsController.verifyStatusCode(pet, 200);
        assertThat(petResponse, equalTo(pet));
        assertThat(petResponse, is(pet));
        assertThat(petResponse.getId(), is(pet.getId()));


//        Assert.assertEquals(pet.getId(), id);
//        Assert.assertEquals(pet.getName(), petName);
//        Assert.assertEquals(pet.getStatus(), Status.available);
    }

    @Test(priority = 2, description = "User tries to add a new pet to the store with invalid parameter(s)")
    public void addNewPetWithInvalidId() {
        pet.setId("-7");
        Pet petResponse = petsController.addNewPet(pet, "post");
        petsController.verifyStatusCode(pet, HTTP_BAD_REQUEST);
    }

    @Test(priority = 3, description = "User tries to add a new pet to the store with empty parameter(s)")
    public void addNewPetWithEmptyName() {
        pet.setName("");
        Pet petResponse = petsController.addNewPet(pet, "post");
        petsController.verifyStatusCode(pet, HTTP_NOT_FOUND);
    }

    @Test(priority = 4, description = "User tries to add a new pet to the store with invalid method")
    public void addNewPetWithInvalidMethod() {
        Pet petResponse = petsController.addNewPet(pet, "patch");
        petsController.verifyStatusCode(pet, 405);
    }

}

