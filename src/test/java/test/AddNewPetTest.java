package test;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petstore.Category;
import petstore.Pet;
import petstore.Status;
import petstore.Tag;
import utils.PetsController;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class AddNewPetTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private static final PetsController petsController = new PetsController();
    private static final Pet pet = new Pet();
    private static final String id = "1";
    private static final String petName = "Baaaarsik";
    private static final String categoryName = "cats";
    private static final String tagName = "pallas's cat";


    @BeforeMethod
    public void beforeMethod() {

        pet.setId(id);
        pet.setName(petName);
        pet.setPhotoUrls(Collections.singletonList(PHOTO_URL));
        pet.setStatus(Status.available);
        pet.setTags(Collections.singletonList(new Tag(id, tagName)));
        pet.setCategory(new Category(id, categoryName));
    }


    @Test(description = "User adds a new pet to the store with valid input parameters")
    public void addNewPet() {
        Pet petResponse = petsController.addNewPet(pet);
        petsController.verifyStatusCode(pet, 200);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
        Assert.assertEquals(pet.getId(), id);
        Assert.assertEquals(pet.getName(), petName);
        Assert.assertEquals(pet.getStatus(), Status.available);
    }

    @Test(description = "User tries to add a new pet to the store with invalid parameter(s)")
    public void addNewPetWithInvalidId() {
        pet.setId("-7");
        Pet petResponse = petsController.addNewPet(pet);
        petsController.verifyStatusCode(pet, 400);
    }

    @Test(description = "User tries to add a new pet to the store with empty parameter(s)")
    public void addNewPetWithEmptyName() {
        pet.setName("");
        Pet petResponse = petsController.addNewPet(pet);
        petsController.verifyStatusCode(pet, 404);
    }

    @Test(description = "User tries to add a new pet to the store with invalid method")
    public void addNewPetWithInvalidMethod() {
        Pet petResponse = petsController.addNewPet(pet);
        petsController.verifyStatusCode(pet, 405);
    }

}

