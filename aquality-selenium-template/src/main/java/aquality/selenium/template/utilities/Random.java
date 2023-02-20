package aquality.selenium.template.utilities;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Random {
    private static final int MIN = 10;
    private static final int MAX = 100;
    private static Faker faker = new Faker();

    public static String getRandomText(int min, int max){
        String text = faker.internet().password(min, max);
        return text;
    }

    public static String getRandomText(){
        return getRandomText(MIN, MAX);
    }

    public static int getRandomIntNumber(int min, int max){
        return faker.number().numberBetween(min, max);
    }

    public static int getRandomIntNumber(){
        return getRandomIntNumber(MIN, MAX);
    }

    public String getRandomPassword(){
        return faker.internet().password(10, 20, true, true, true);
    }

    public static String getRandomName() {
        return faker.name().firstName();
    }

    public static String getRandomDomainWord() {
        return faker.internet().domainWord();
    }

    public static String getRandomDomainSuffix(){
        return faker.internet().domainSuffix();
    }
}
