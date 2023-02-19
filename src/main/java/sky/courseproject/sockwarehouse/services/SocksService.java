package sky.courseproject.sockwarehouse.services;

import sky.courseproject.sockwarehouse.models.Socks;

import java.util.TreeMap;

public interface SocksService {
    void upLoadList();

    Socks createSocks(Socks socks);

    Socks updateSocks(int id, Socks socks);

    Socks getSocksById(int id);

    TreeMap<Integer,Socks> getAllSocks();

    void deleteSocksById(int id);

    void saveToFile();

    void readFromFile();
}
