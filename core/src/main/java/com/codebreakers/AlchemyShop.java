package com.codebreakers.elixir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlchemyShop {
   private List<Customer> todaysCustomers = new ArrayList();
   private Cauldron cauldron = new Cauldron();
   private CustomerGenerator customerGenerator;

   public AlchemyShop(Random random) {
      this.customerGenerator = new CustomerGenerator(random);
   }

   public void openForDay(int day) {
      this.todaysCustomers.clear();
      this.todaysCustomers.addAll(this.customerGenerator.generateCustomers(day));
   }

   public boolean canServe(Customer customer, Player player) {
      return player.getInventory().hasPotion(customer.getRequestedPotion());
   }

   public boolean sellCustomer(Customer customer, Player player) {
      if (!this.todaysCustomers.contains(customer)) {
         return false;
      } else if (!this.canServe(customer, player)) {
         return false;
      } else {
         player.getInventory().removePotion(customer.getRequestedPotion());
         player.earnGold(customer.getPayment());
         this.todaysCustomers.remove(customer);
         return true;
      }
   }

   public void closeForDay() {
      this.todaysCustomers.clear();
   }

   public void restoreCustomers(List<Customer> customers) {
      this.todaysCustomers.clear();
      this.todaysCustomers.addAll(customers);
   }

   public List<Customer> getTodaysCustomers() {
      return this.todaysCustomers;
   }

   public Cauldron getCauldron() {
      return this.cauldron;
   }
}

