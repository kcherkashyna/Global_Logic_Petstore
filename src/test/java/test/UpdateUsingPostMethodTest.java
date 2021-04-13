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

public class UpdateUsingPostMethodTest {
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


    @Test(priority = 1, description = "User updates parameter(s) of the existing pet")
    public void updatePet() {
        pet.setStatus(Status.sold);
        Pet petResponse = petsController.updatePetByPost(pet);
        petsController.verifyStatusCode(pet, 200);
        Assert.assertEquals(pet.getId(), id);
        Assert.assertEquals(pet.getName(), petName);
        Assert.assertEquals(pet.getStatus(), Status.sold);
    }

    @Test(priority = 2, description = "User tries to update a pet using invalid parameter(s)")
    public void updatePetWithInvalidParameters() {
        pet.setId("-22");
        pet.setName("Garfild");
        Pet petResponse = petsController.updatePetByPost(pet);
        petsController.verifyStatusCode(pet, 400);
    }

    @Test(priority = 3, description = "User tries to update a pet using empty parameter(s)")
    public void updatePetWithEmptyParameters() {
        pet.setName("");
        Pet petResponse = petsController.updatePetByPost(pet);
        petsController.verifyStatusCode(pet, 404);
    }
}
