package app;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

  private final FoodRepository repo;

  public FoodController() {
    // DB-Pfad im Container: /data/calotracker.db
    // Der Container bekommt das als Volume gemountet.
    this.repo = new FoodRepository("jdbc:sqlite:/data/calotracker.db");
  }

  @GetMapping("/foods")
  public List<FoodDto> listFoods() {
    return repo.findAll();
  }
}
