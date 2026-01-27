package app;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

  @GetMapping("/foods")
  public List<FoodDto> listFoods() {
    // Platzhalter: nächste Stufe ist SQLite-Anbindung
    return List.of();
  }
}
