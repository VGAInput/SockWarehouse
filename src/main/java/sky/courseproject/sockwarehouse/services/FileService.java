package sky.courseproject.sockwarehouse.services;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanFile();
}
