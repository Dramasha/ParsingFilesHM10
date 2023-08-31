package testingFilesTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Pdf;
import testingFilesTest.model.CharacterModel;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class JsonTest {
    private static final ClassLoader cl = Pdf.class.getClassLoader();

    @Test
    @DisplayName("поиск содержимого в файле .json")
    public void jsonParsingTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("avengers.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<CharacterModel> characterModel = objectMapper.readValue(stream, new TypeReference<>() {
            });

            Assertions.assertThat(characterModel).hasSize(3);

            CharacterModel firstSuperHero = characterModel.get(0);
            Assertions.assertThat(firstSuperHero.getId()).isEqualTo(1);
            Assertions.assertThat(firstSuperHero.getFirstName()).isEqualTo("Tony");
            Assertions.assertThat(firstSuperHero.getLastName()).isEqualTo("Stark");
            Assertions.assertThat(firstSuperHero.getPseudonym()).isEqualTo("Iron Man");
            Assertions.assertThat(firstSuperHero.getBorn()).isEqualTo(1970);

            CharacterModel secondSuperHero = characterModel.get(1);
            Assertions.assertThat(secondSuperHero.getId()).isEqualTo(2);
            Assertions.assertThat(secondSuperHero.getFirstName()).isEqualTo("Steve");
            Assertions.assertThat(secondSuperHero.getLastName()).isEqualTo("Rogers");
            Assertions.assertThat(secondSuperHero.getPseudonym()).isEqualTo("Captain America");
            Assertions.assertThat(secondSuperHero.getBorn()).isEqualTo(1920);

            CharacterModel thirdSuperHero = characterModel.get(2);
            Assertions.assertThat(thirdSuperHero.getId()).isEqualTo(3);
            Assertions.assertThat(thirdSuperHero.getFirstName()).isEqualTo("Bruce");
            Assertions.assertThat(thirdSuperHero.getLastName()).isEqualTo("Banner");
            Assertions.assertThat(thirdSuperHero.getPseudonym()).isEqualTo("Hulk");
            Assertions.assertThat(thirdSuperHero.getBorn()).isEqualTo(1969);
        }
    }
}