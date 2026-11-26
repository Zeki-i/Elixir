package com.codebreakers.elixir;

import java.util.EnumMap;
import java.util.Map;

public class Inventory {
   private EnumMap<IngredientType, Integer> ingredients = new EnumMap(IngredientType.class);
   private EnumMap<PotionType, Integer> potions = new EnumMap(PotionType.class);

   public Inventory() {
      for(IngredientType type : IngredientType.values()) {
         this.ingredients.put(type, 0);
      }

      for(PotionType type : PotionType.values()) {
         this.potions.put(type, 0);
      }

   }

   public void addIngredient(IngredientType type, int amount) {
      this.ingredients.put(type, (Integer)this.ingredients.get(type) + amount);
   }

   public void addAllIngredients(Map<IngredientType, Integer> found) {
      for(IngredientType type : found.keySet()) {
         this.addIngredient(type, (Integer)found.get(type));
      }

   }

   public boolean hasIngredients(Map<IngredientType, Integer> required) {
      for(IngredientType type : required.keySet()) {
         if ((Integer)this.ingredients.get(type) < (Integer)required.get(type)) {
            return false;
         }
      }

      return true;
   }

   public boolean removeIngredients(Map<IngredientType, Integer> required) {
      if (!this.hasIngredients(required)) {
         return false;
      } else {
         for(IngredientType type : required.keySet()) {
            this.ingredients.put(type, (Integer)this.ingredients.get(type) - (Integer)required.get(type));
         }

         return true;
      }
   }

   public void addPotion(PotionType type) {
      this.addPotions(type, 1);
   }

   public void addPotions(PotionType type, int amount) {
      this.potions.put(type, (Integer)this.potions.get(type) + amount);
   }

   public boolean removePotion(PotionType type) {
      if (!this.hasPotion(type)) {
         return false;
      } else {
         this.potions.put(type, (Integer)this.potions.get(type) - 1);
         return true;
      }
   }

   public boolean hasPotion(PotionType type) {
      return (Integer)this.potions.get(type) > 0;
   }

   public int getIngredientCount(IngredientType type) {
      return (Integer)this.ingredients.get(type);
   }

   public int getPotionCount(PotionType type) {
      return (Integer)this.potions.get(type);
   }
}
