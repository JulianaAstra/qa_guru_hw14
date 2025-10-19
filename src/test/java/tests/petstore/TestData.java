package tests.petstore;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    private static Faker faker = new Faker();
    public static Integer notExistPetId = faker.number().numberBetween(999999999, 1000000000);

    public Integer petId = faker.number().numberBetween(1000, 9999);
    public String petName = faker.funnyName().name();
    public String availableStatus = "available";
    public String petTag = "test_tag";
    public int petTagId = 1;
    public String userName = faker.name().lastName().toLowerCase(Locale.ROOT);
    public String userPassword = faker.hipster().word();
}
