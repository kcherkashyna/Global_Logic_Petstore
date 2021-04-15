package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petstore.Category;
import petstore.Pet;
import petstore.Status;
import petstore.Tag;
import utils.PetsController;

import java.util.Collections;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class DeleteAPetTest {
    private static final String PHOTO_URL = "https://en.wikipedia.org/wiki/Pallas%27s_cat#/media/File:Manoel.jpg";
    private PetsController petsController;
    private Pet pet;
    private static final String ID = "1";
    private static final String PET_NAME = "Baaaarsik";
    private static final String CATEGORY_NAME = "cats";
    private static final String TAG_NAME = "pallas's cat";


    @BeforeMethod
    public void beforeMethod() {
        petsController = new PetsController();
        pet = new Pet();
        pet.setId(ID);
        pet.setName(PET_NAME);
        pet.setPhotoUrls(Collections.singletonList(PHOTO_URL));
        pet.setStatus(Status.available);
        pet.setTags(Collections.singletonList(new Tag(ID, TAG_NAME)));
        pet.setCategory(new Category(ID, CATEGORY_NAME));
        petsController.addNewPetAndCheckStatusCode(pet, HTTP_OK);
    }


    @Test(description = "User deletes pet by id")
    public void deletePetAndDoCheck() {
        petsController.deletePetAndCheckStatusCode(pet, HTTP_OK);
        petsController.verifyPetDeletedAndCheckStatusCode(pet, HTTP_NOT_FOUND);
    }

    @Test(description = "User tries to delete pet with invalid petId")
    public void deletePetWithInvalidParameter() {
        pet.setId("-7");
        pet.setName(PET_NAME);
        pet.setPhotoUrls(Collections.singletonList(PHOTO_URL));
        pet.setStatus(Status.available);
        pet.setTags(Collections.singletonList(new Tag(ID, TAG_NAME)));
        pet.setCategory(new Category(ID, CATEGORY_NAME));
        petsController.deletePetAndCheckStatusCode(pet, HTTP_NOT_FOUND);
    }

    @Test(description = "User tries to delete pet with empty petId")
    public void deletePetWithEmptyParameter() {
        pet.setId(ID);
        pet.setName("");
        pet.setPhotoUrls(Collections.singletonList(PHOTO_URL));
        pet.setStatus(Status.available);
        pet.setTags(Collections.singletonList(new Tag(ID, TAG_NAME)));
        pet.setCategory(new Category(ID, CATEGORY_NAME));
        petsController.deletePetAndCheckStatusCode(pet, HTTP_NOT_FOUND);
    }

}

