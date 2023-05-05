package Kolokviumski2.OnlineShop;

import java.awt.color.ProfileDataException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}


class Product {
    String category;
    String id;
    String name;
    LocalDateTime createdAt;
    double price;
    Integer quantitySold;

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        quantitySold = 0;
    }

    public Integer getSold() {
        return quantitySold;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(category, product.category) && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(createdAt, product.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, id, name, createdAt, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + quantitySold +
                '}';
    }
}


class OnlineShop {

    Map <String, Product> productByID;
    Map <String, List<Product>> productByCategory;
    OnlineShop() {
        productByID = new HashMap<>();
        productByCategory = new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price){
        Product product = new Product(category, id, name, createdAt, price);
        productByCategory.putIfAbsent(category, new ArrayList<>());
        productByCategory.get(category).add(product);
        productByID.put(id, product);
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException{
        if(!productByID.containsKey(id)){
            throw new ProductNotFoundException(String.format("Product with id %s does not exist in the online shop!", id));
        }

        productByID.get(id).quantitySold += quantity;

        return productByID.get(id).getPrice() * quantity;
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<List<Product>> result = new ArrayList<>();
        Comparator<Product> comparator = computeComparator(comparatorType);
        List<Product> products = new ArrayList<>();

        if(category == null){
            products = productByID.values().stream().collect(Collectors.toList());
        }

        if(category != null){
            products = productByCategory.get(category);
        }

        products = products.stream().sorted(comparator).collect(Collectors.toList());

        int pages = (int) Math.ceil((double)products.size()/pageSize);
        for(int i=0; i<pages; i++){
            int start = i*pageSize;
            int end = Math.min(start+pageSize, products.size());
            List<Product> page = products.subList(start, end);
            result.add(page);
        }
        return result;
    }

    private Comparator<Product> computeComparator(COMPARATOR_TYPE comparatorType) {
        Comparator<Product> byPrice = Comparator.comparing(Product::getPrice);
        Comparator<Product> byDate = Comparator.comparing(Product::getCreatedAt);
        Comparator<Product> bySold = Comparator.comparing(Product::getSold);


        if(comparatorType.equals(COMPARATOR_TYPE.HIGHEST_PRICE_FIRST)){
            return byPrice.reversed();
        } else if (comparatorType.equals(COMPARATOR_TYPE.LOWEST_PRICE_FIRST)) {
            return byPrice;
        } else if(comparatorType.equals(COMPARATOR_TYPE.NEWEST_FIRST)){
            return byDate.reversed();
        } else if (comparatorType.equals(COMPARATOR_TYPE.OLDEST_FIRST)) {
            return byDate;
        } else if (comparatorType.equals(COMPARATOR_TYPE.MOST_SOLD_FIRST)) {
            return bySold.reversed();
        } else {
            return bySold;
        }

    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category=null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}

