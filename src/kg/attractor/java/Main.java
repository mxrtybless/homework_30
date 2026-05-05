package kg.attractor.java;

import kg.attractor.java.homework.RestaurantOrders;

public class Main {

    public static void main(String[] args) {
        // это для домашки
        // выберите любое количество заказов, какое вам нравится.

        //var orders = RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)
        var ro = RestaurantOrders.read("orders_100.json");

        System.out.println("=== ВСЕ ЗАКАЗЫ ===");
        ro.printOrders();

        System.out.println("\n=== ТОП 3 ДОРОГИХ ===");
        ro.getTopExpensiveOrders(3).forEach(System.out::println);

        System.out.println("\n=== ТОП 3 ДЕШЁВЫХ ===");
        ro.getTopCheapestOrders(3).forEach(System.out::println);

        System.out.println("\n=== ЗАКАЗЫ НА ДОМ ===");
        ro.getHomeDeliveryOrders().forEach(System.out::println);

        System.out.println("\n=== САМЫЙ ДОРОГОЙ НА ДОМ ===");
        ro.getMostProfitableHomeOrder().ifPresent(System.out::println);

        System.out.println("\n=== САМЫЙ ДЕШЁВЫЙ НА ДОМ ===");
        ro.getLeastProfitableHomeOrder().ifPresent(System.out::println);

        System.out.println("\n=== ЗАКАЗЫ В ДИАПАЗОНЕ (1000–3000) ===");
        ro.getOrdersInTotalRange(1000, 3000).forEach(System.out::println);

        System.out.println("\n=== ОБЩАЯ ВЫРУЧКА ===");
        System.out.println(ro.getTotalRevenue());

        System.out.println("\n=== EMAIL (УНИКАЛЬНЫЕ + СОРТИРОВКА) ===");
        ro.getSortedCustomerEmails().forEach(System.out::println);

        System.out.println("\n=== ГРУППИРОВКА ПО КЛИЕНТАМ ===");
        ro.getOrdersGroupedByCustomerName()
                .forEach((name, orders) ->
                        System.out.println(name + " -> " + orders.size()));

        System.out.println("\n=== СУММА ПО КЛИЕНТАМ ===");
        ro.getTotalAmountByCustomerName()
                .forEach((name, total) ->
                        System.out.println(name + " -> " + total));

        System.out.println("\n=== КЛИЕНТ С МАКС СУММОЙ ===");
        ro.getCustomerWithMaxTotalAmount().ifPresent(System.out::println);

        System.out.println("\n=== КЛИЕНТ С МИН СУММОЙ ===");
        ro.getCustomerWithMinTotalAmount().ifPresent(System.out::println);

        System.out.println("\n=== ПРОДАННЫЕ ТОВАРЫ ===");
        ro.getSoldItemsAmount()
                .forEach((item, amount) ->
                        System.out.println(item + " -> " + amount));
    }
}
