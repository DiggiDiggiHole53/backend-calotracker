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
    List<FoodDto> out = new ArrayList<>();
    String sql = "SELECT id, name, category, energyKcal, protein, carbohydrates, fat FROM food ORDER BY name";
    try (Connection c = DriverManager.getConnection(url);
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
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
      return out;
    } catch (SQLException e) {
      throw new RuntimeException("DB error", e);
    }
  }
}
