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

public class DeleteAPetTest {
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


    @Test(priority = 1, description = "User deletes pet by id")
    public void deletePetAndDoCheck() {
        petsController.deletePet(pet);
        petsController.verifyPetDeleted(pet);
    }

    @Test(priority = 2, description = "User tries to delete pet with invalid petId")
    public void addNewPetWithInvalidId() {
        pet.setId("-7");
        petsController.deletePet(pet);
        petsController.verifyStatusCode(pet, 400);
    }

    @Test(priority = 3, description = "User tries to delete pet with empty petId")
    public void addNewPetWithEmptyName() {
        pet.setName("");
        petsController.deletePet(pet);
        petsController.verifyStatusCode(pet, 404);
    }

}

