package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petstore.Category;
import petstore.Pet;
import petstore.Status;
import petstore.Tag;
import utils.PetsController;

import java.util.Collections;

public class FindPetsByStatusTest {
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

    @Test(description = "User finds pets by status available")
    public void findPetsByStatusAvailable() {
        petsController.getPetsByStatus(pet, "1","get", 200);
    }

    @Test(description = "User finds pets by status pending")
    public void findPetsByStatusPending() {
        petsController.getPetsByStatus(pet, "2","get", 200);
    }

    @Test(description = "User finds pets by status sold")
    public void findPetsByStatusSold() {
        petsController.getPetsByStatus(pet, "3","get", 200);
    }

    @Test(description = "User tries to find pets with non-existent status")
    public void findPetsByInvalidStatus() {
        pet.setStatus(Status.invalidStatus);
        petsController.getPetsByStatus(pet, "4", "get", 400);
    }

    @Test(description = "User tries to find pets with empty status")
    public void findPetsByEmptyStatus() {
        pet.setStatus(Status.nullStatus);
        petsController.getPetsByStatus(pet, "", "get", 404);
    }

    @Test(description = "User tries to find pets with valid status using invalid method")
    public void findPetsWithInvalidMethod() {
        petsController.getPetsByStatus(pet, "1", "patch", 405);
    }

}



