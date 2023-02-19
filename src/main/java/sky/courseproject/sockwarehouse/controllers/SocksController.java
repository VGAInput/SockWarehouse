package sky.courseproject.sockwarehouse.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.courseproject.sockwarehouse.models.Socks;
import sky.courseproject.sockwarehouse.services.SocksServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/socks")
@Tag(name = "Контроллёр работы с носками", description = "CRUD-операции и работа с файлами для работы с носками.")

public class SocksController {
    private SocksServiceImpl socksService;

    @Autowired
    public SocksController(SocksServiceImpl socksService) {
        this.socksService = socksService;
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
    Основные  CRUD - эндпоинты (Базовый уровень):
* * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление новых носков.")
    public ResponseEntity addNewSocks(@RequestBody Socks newSocks) {
        return ResponseEntity.ok("Добавлены новые носки:" + socksService.createSocks(newSocks));
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение носков по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<Socks> getSocksById(@RequestParam int id) {
        Socks socks = socksService.getSocksById(id);
        if (socks == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(socks);
    }

    @GetMapping("/all")
    @Operation(description = "Получение всех носков.")
    public ResponseEntity<Map<Integer, Socks>> getAllSocks() {
        Map<Integer, Socks> socks = socksService.getAllSocks();
        if (socks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(socks);
    }

    @PutMapping("/edit/{id}")
    @Operation(description = "Редактирование носков по ID.")

    public Socks putSocks(@RequestBody Socks socks, @PathVariable int id) {
        Socks updateSocks = socksService.updateSocks(id, socks);
        return socks;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление носков по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<String> deleteSocks(@PathVariable int id) {
        socksService.deleteSocksById(id);
        return ResponseEntity.ok("Носки №" + id + " удалены из списка");
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * *
    Эндпоинты для экспорта и импорта JSON-файлов (Средний уровень):
    * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @PostMapping("/upload")
    @Operation(description = "Экспорт данных в текущем состоянии.")
    public ResponseEntity<String>  uploadCurrentFile() {
        socksService.readFromFile();
        return ResponseEntity.ok("Данные в текущем состоянии экспортированы.");
    }

    @PostMapping("/download")
    @Operation(description = "Импорт и замена данных в JSON-файл.")
    public ResponseEntity<String>  downloadCurrentFile() {
        socksService.saveToFile();
        return ResponseEntity.ok("Данные в текущем состоянии импортированы.");
    }

}


