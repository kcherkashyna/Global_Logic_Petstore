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

public class FindPetsByStatusTest {
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

    @Test(description = "User finds pets by status available")
    public void findPetsByStatusAvailable() {
        petsController.getPetsByStatusAndCheckStatusCode(pet, Status.available, HTTP_OK);
    }

    @Test(description = "User finds pets by status pending")
    public void findPetsByStatusPending() {
        petsController.getPetsByStatusAndCheckStatusCode(pet, Status.pending, HTTP_OK);
    }

    @Test(description = "User finds pets by status sold")
    public void findPetsByStatusSold() {
        petsController.getPetsByStatusAndCheckStatusCode(pet, Status.sold, HTTP_OK);
    }

    @Test(description = "User tries to find pets with non-existent status")
    public void findPetsByInvalidStatus() {
        pet.setStatus(Status.invalidStatus);
        petsController.getPetsByStatusAndCheckStatusCode(pet, Status.invalidStatus, HTTP_BAD_REQUEST);
    }

    @Test(description = "User tries to find pets with empty status")
    public void findPetsByEmptyStatus() {
        pet.setStatus(Status.nullStatus);
        petsController.getPetsByStatusAndCheckStatusCode(pet, Status.nullStatus, HTTP_NOT_FOUND);
    }

    @Test(description = "User tries to find pets with valid status using invalid method")
    public void findPetsWithInvalidMethod() {
        petsController.getPetsByStatusUsingWrongMethodAndCheckStatusCode(pet, UnreservedMethods.PATCH, HTTP_BAD_METHOD);
    }

}



