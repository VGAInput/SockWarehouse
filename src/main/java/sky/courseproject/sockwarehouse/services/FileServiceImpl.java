package sky.courseproject.sockwarehouse.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service

public class FileServiceImpl implements FileService {

    @Value("${path.to.data.file}")
    private String filePath;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(filePath, "/Socks.json"), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(filePath + "/Socks.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean cleanFile() {
        try {
            Path path = Path.of(filePath + "/Socks.json");
            Files.deleteIfExists(path);
            Files.createFile(path);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
