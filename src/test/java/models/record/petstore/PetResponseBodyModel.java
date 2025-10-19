package models.record.petstore;

public record PetResponseBodyModel(Integer id, String[] urls, TagBodyModel[] tags) {
}