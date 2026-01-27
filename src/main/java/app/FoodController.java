package app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

  private static final String DB_URL = "jdbc:sqlite:/data/calotracker.db";
  private final FoodRepository repo;

  public FoodController() {
    this.repo = new FoodRepository(DB_URL);
    System.out.println("[FoodController] DB file exists: " + Files.exists(Path.of("/data/calotracker.db")));
    System.out.println("[FoodController] DB url: " + DB_URL);

    // quick count test
    try (Connection c = DriverManager.getConnection(DB_URL);
         PreparedStatement ps = c.prepareStatement("select count(*) from food");
         ResultSet rs = ps.executeQuery()) {
      rs.next();
      System.out.println("[FoodController] food_count_at_start = " + rs.getInt(1));
    } catch (Exception e) {
      System.out.println("[FoodController] COUNT FAILED: " + e);
      e.printStackTrace();
    }
  }

  @GetMapping("/foods")
  public List<FoodDto> listFoods() {
    return repo.findAll();
  }
}
