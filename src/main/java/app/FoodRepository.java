package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository {
  private final String url;

  public FoodRepository(String url) {
    this.url = url;
  }

  public List<FoodDto> findAll() {
    System.out.println("[FoodRepository] findAll() using url=" + url);

    List<FoodDto> out = new ArrayList<>();
    String sql = "SELECT id, name, category, energyKcal, protein, carbohydrates, fat FROM food ORDER BY name";
    try (Connection c = DriverManager.getConnection(url);
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

      int n = 0;
      while (rs.next()) {
        n++;
        out.add(new FoodDto(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("category"),
          rs.getDouble("energyKcal"),
          rs.getDouble("protein"),
          rs.getDouble("carbohydrates"),
          rs.getDouble("fat")
        ));
      }
      System.out.println("[FoodRepository] rows=" + n);
      return out;
    } catch (SQLException e) {
      System.out.println("[FoodRepository] ERROR: " + e);
      throw new RuntimeException("DB error", e);
    }
  }
}
