package com.codebreakers.elixir;

import java.util.EnumMap;
import java.util.Map;

public class Recipe {
   private PotionType potion;
   private EnumMap<IngredientType, Integer> requirements;

   public Recipe(PotionType potion) {
      this.potion = potion;
      this.requirements = new EnumMap(IngredientType.class);
   }

   public void addRequirement(IngredientType ingredient, int amount) {
      this.requirements.put(ingredient, amount);
   }

   public boolean canBrewWith(Inventory inventory) {
      return inventory.hasIngredients(this.requirements);
   }

   public Map<IngredientType, Integer> getMissing(Inventory inventory) {
      EnumMap<IngredientType, Integer> missing = new EnumMap(IngredientType.class);

      for(IngredientType type : this.requirements.keySet()) {
         int have = inventory.getIngredientCount(type);
         int need = (Integer)this.requirements.get(type);
         if (have < need) {
            missing.put(type, need - have);
         }
      }

      return missing;
   }

   public PotionType getPotion() {
      return this.potion;
   }

   public EnumMap<IngredientType, Integer> getRequirements() {
      return this.requirements;
   }
}
