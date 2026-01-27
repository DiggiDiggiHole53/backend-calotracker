package app;

public record FoodDto(
  int id,
  String name,
  String category,
  double energyKcal,
  double protein,
  double carbohydrates,
  double fat
) {}
