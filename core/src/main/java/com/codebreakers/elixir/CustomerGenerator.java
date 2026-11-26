package com.codebreakers.elixir;

import com.codebreakers.elixir.model.CustomerGenerator.1;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

public class CustomerGenerator {
   public static final int MAX_CUSTOMERS = 8;
   private Random random;

   public CustomerGenerator(Random random) {
      this.random = random;
   }

   public List<Customer> generateCustomers(int day) {
      List<Customer> customers = new ArrayList();
      int count = this.calculateCustomerCount(day);

      for(int i = 0; i < count; ++i) {
         CustomerType type = this.pickCustomerType(day);
         customers.add(createCustomer(type));
      }

      return customers;
   }

   public int calculateCustomerCount(int day) {
      int count = 2 + day / 5 + this.random.nextInt(2);
      if (count > 8) {
         count = 8;
      }

      return count;
   }

   public CustomerType pickCustomerType(int day) {
      EnumMap<CustomerType, Integer> weights = this.getSpawnWeightArray(day);
      int roll = this.random.nextInt(100);
      int total = 0;

      for(CustomerType type : CustomerType.values()) {
         total += (Integer)weights.get(type);
         if (roll < total) {
            return type;
         }
      }

      return CustomerType.DWARF;
   }

   public static Customer createCustomer(CustomerType type) {
      switch (1.$SwitchMap$com$codebreakers$elixir$model$CustomerType[type.ordinal()]) {
         case 1 -> {
            return new Goblin();
         }
         case 2 -> {
            return new Dragon();
         }
         case 3 -> {
            return new Eros();
         }
         case 4 -> {
            return new UglyWitch();
         }
         default -> {
            return new Dwarf();
         }
      }
   }

   public EnumMap<CustomerType, Integer> getSpawnWeightArray(int day) {
      EnumMap<CustomerType, Integer> weights = new EnumMap(CustomerType.class);

      for(CustomerType type : CustomerType.values()) {
         weights.put(type, 0);
      }

      if (day <= 4) {
         weights.put(CustomerType.DWARF, 100);
      } else if (day <= 8) {
         weights.put(CustomerType.DWARF, 50);
         weights.put(CustomerType.GOBLIN, 50);
      } else if (day <= 12) {
         weights.put(CustomerType.DWARF, 20);
         weights.put(CustomerType.GOBLIN, 30);
         weights.put(CustomerType.DRAGON, 50);
      } else if (day <= 16) {
         weights.put(CustomerType.DWARF, 10);
         weights.put(CustomerType.GOBLIN, 20);
         weights.put(CustomerType.DRAGON, 20);
         weights.put(CustomerType.EROS, 50);
      } else {
         weights.put(CustomerType.DWARF, 10);
         weights.put(CustomerType.GOBLIN, 10);
         weights.put(CustomerType.DRAGON, 10);
         weights.put(CustomerType.EROS, 20);
         weights.put(CustomerType.UGLY_WITCH, 50);
      }

      return weights;
   }
}
