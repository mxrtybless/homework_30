package kg.attractor.java.homework;

import com.google.gson.Gson;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import kg.attractor.java.homework.domain.Order;
import java.util.Set;
import java.util.TreeSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RestaurantOrders {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private List<Order> orders;

    private RestaurantOrders(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            orders = List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RestaurantOrders read(String fileName) {
        var ro = new RestaurantOrders(fileName);
        ro.getOrders().forEach(Order::calculateTotal);
        return ro;
    }

    public List<Order> getOrders() {
        return orders;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------
    public void printOrders() {
        orders.forEach(System.out::println);
    }

    public List<Order> getTopExpensiveOrders(int amount) {
        return orders.stream()
                .sorted(Comparator.comparingDouble(Order::getTotal).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    public List<Order> getTopCheapestOrders(int amount) {
        return orders.stream()
                .sorted(Comparator.comparingDouble(Order::getTotal))
                .limit(amount)
                .collect(Collectors.toList());
    }
    public List<Order> getHomeDeliveryOrders() {
        return orders.stream()
                .filter(Order::isHomeDelivery)
                .collect(Collectors.toList());
    }

    public Optional<Order> getMostProfitableHomeOrder() {
        return orders.stream()
                .filter(Order::isHomeDelivery)
                .max(Comparator.comparingDouble(Order::getTotal));
    }

    public Optional<Order> getLeastProfitableHomeOrder() {
        return orders.stream()
                .filter(Order::isHomeDelivery)
                .min(Comparator.comparingDouble(Order::getTotal));
    }

    public List<Order> getOrdersInTotalRange(double min, double max) {
        return orders.stream()
                .filter(o -> o.getTotal() >= min && o.getTotal() <= max)
                .collect(Collectors.toList());
    }

    public double getTotalRevenue() {
        return orders.stream()
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public Set<String> getSortedCustomerEmails() {
        return orders.stream()
                .map(o -> o.getCustomer().getEmail())
                .collect(Collectors.toCollection(TreeSet::new));
    }
    // Наполните этот класс решением домашнего задания.
    // Вам необходимо создать все необходимые методы
    // для решения заданий из домашки :)
    // вы можете добавлять все необходимые imports
    //
}
