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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class FindPetByIdTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private PetsController petsController;
    private Pet pet;
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

    @Test(description = "User finds pet by id")
    public void findPet() {
        Pet petResponse = petsController.findPetById(pet, "get");
        petsController.verifyStatusCode(pet, 200);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
        Assert.assertEquals(pet.getId(), id);
        Assert.assertEquals(pet.getName(), petName);
        Assert.assertEquals(pet.getStatus(), Status.available);
    }

    @Test(description = "User tries to find pet with invalid parameter(s)")
    public void findPetWithInvalidParameter() {
        pet.setId("7697698769878608769769757657654754365436543");
        Pet petResponse = petsController.findPetById(pet, "get");
        petsController.verifyStatusCode(pet, 400);
    }

    @Test(description = "User tries to find pet with empty parameter(s)")
    public void findPetWithEmptyParameter() {
        pet.setId("");
        Pet petResponse = petsController.findPetById(pet, "get");
        petsController.verifyStatusCode(pet, 404);
    }

    @Test(description = "User tries to find pet using invalid method")
    public void findPetWithInvalidMethod() {
        pet.setId("");
        Pet petResponse = petsController.findPetById(pet, "patch");
        petsController.verifyStatusCode(pet, 405);
    }
}