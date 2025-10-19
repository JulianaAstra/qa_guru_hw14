package models.record.petstore;

public record PetBodyModel(Integer id, String name, String status, TagBodyModel[] tags) {
}
