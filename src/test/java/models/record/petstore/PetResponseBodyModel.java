package models.record.petstore;

public record PetResponseBodyModel(Integer id, String[] photoUrls, TagBodyModel[] tags) {
}