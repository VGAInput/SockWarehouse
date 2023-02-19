package sky.courseproject.sockwarehouse.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.courseproject.sockwarehouse.models.Socks;

import javax.annotation.PostConstruct;
import java.util.TreeMap;


@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    final private FileService fileService;

    private int generateId = 0;
    private static TreeMap<Integer, Socks> socksTreeMap = new TreeMap<>();

    public SocksServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public void upLoadList() {
        saveToFile();
    }

    @Override
    public Socks createSocks(Socks socks) {
        socksTreeMap.put(generateId, socks);
        generateId++;
        return socks;
    }

    @Override
    public Socks updateSocks(int id, Socks socks) {
        socksTreeMap.put(id, socks);
        return socks;
    }

    @Override
    public Socks getSocksById(int id) {
        return socksTreeMap.get(id);
    }

    @Override
    public TreeMap<Integer, Socks> getAllSocks() {
        saveToFile();
        return socksTreeMap;
    }

    @Override
    public void deleteSocksById(int id) {
        socksTreeMap.remove(id);
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(socksTreeMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readFromFile() {
        try {
            String json = fileService.readFromFile();
            socksTreeMap = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Socks>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
