package models.record.petstore;

public record PetResponseModel(Integer id, String[] photoUrls, TagModel[] tags) {
}