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

import static java.net.HttpURLConnection.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateAnExistingPetTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private PetsController petsController;
    private Pet pet;
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


    @Test(description = "User updates parameter(s) of the existing pet")
    public void updatePet() {
        pet.setName("Murka");
        Pet petResponse = petsController.updatePetAndCheckStatusCode(pet, HTTP_OK);
        assertThat(petResponse, equalTo(pet));
        Assert.assertEquals(pet.getName(), "Murka");
    }

    @Test(description = "User tries to update a pet using invalid parameter(s)")
    public void updatePetWithInvalidParameters() {
        pet.setId("-2");
        pet.setName("Pushok");
        petsController.updatePetAndCheckStatusCode(pet, HTTP_BAD_REQUEST);
    }

    @Test(description = "User tries to update a pet using empty parameter(s)")
    public void updatePetWithEmptyParameters() {
        pet.setId("");
        pet.setName("");
        petsController.updatePetAndCheckStatusCode(pet, HTTP_NOT_FOUND);
    }
}
