import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyStepdefs {
    @Дано("^Я, как пользователь, ввел путь \"([^\"]*)\" к файлу$")
    public void яКакПользовательВвелПутьКФайлу(String path){
        InputStream in = new ByteArrayInputStream(path.getBytes());
        System.setIn(in);
        TestContext.file = MyFile.getInstance();
        TestContext.file.setPath(Paths.get(path));
    }

    @Когда("^Я выбираю в меню пункт Создание файла$")
    public void яВыбираюВМенюПунктСозданиеФайла() throws IOException {
        TestContext.file.createFile();
    }

    @Тогда("По указанному пути создается файл")
    public void поУказанномуПутиСоздаетсяФайл() {
        Assert.assertTrue(Files.exists(TestContext.file.getPath()));
    }
}
