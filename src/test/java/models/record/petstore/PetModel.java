package models.record.petstore;

public record PetModel(Integer id, String name, String status, TagModel[] tags) {
}
