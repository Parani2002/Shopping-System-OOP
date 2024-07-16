import java.io.*;
import java.util.*;
import java.util.ArrayList;


public class WestminsterShoppingManager implements ShoppingManager {
        static final List<Product> products = new ArrayList<>();
        public final static int MAX_PRODUCTS = 50;
        Scanner scanner = new Scanner(System.in);
        private int freeslots = MAX_PRODUCTS;


        public void addProduct(Product product) {
                if (products.contains(product)) {
                        System.out.println("The product is already in the product list");
                } else if (freeslots == 0) {
                        System.out.println("The Product list is full");
                        return;

                }
                products.add(product);
                System.out.println("The product has been successfully added");
                freeslots = freeslots - 1;
                System.out.println("Free slots remaining: " + freeslots);

        }

        public void deleteProduct(String productID) {
                boolean found = false;
                for (Product product : products){
                        if (product.getProductID().equals(productID)){
                                found = true;
                                products.remove(product);
                                System.out.println(product.toString() + "has been removed successfully");
                                freeslots = freeslots + 1;
                                System.out.println("Total number of slots left : " + freeslots);
                                break;

                        }
                }
                if (!found){
                        System.out.println("Invalid ProductID!!! Please check and try again!!!");
                }


        }
        public  void displayProducts(){
                if (products.isEmpty()) {
                        System.out.println("<-----No Products in the list----->");
                }
                else {
                        System.out.println("List of all the Products");
                        for (Product product: products){
                                System.out.println(product);
                        }
                }

        }

        public void saveFile() {
                try {
                        //FileWriter myWriter = new FileWriter("./src/Product File", true);
                        FileWriter myWriter = new FileWriter("Product File",true);

                        for (Product product : products) {
                                String ProductID = product.getProductID();
                                String ProductName = product.getProductName();
                                int num_of_available = product.getNum_of_available();
                                double price = product.getPrice();
                                System.out.println("aaaa");

                                String data = ProductID + ","
                                        + ProductName + ","
                                        + num_of_available + ","
                                        + price + ",";
                                if (product instanceof Clothing) {
                                        int size = ((Clothing) product).getSize();
                                        String colour = ((Clothing) product).getColour();
                                        data = "Clothing, " + data + size + "," + colour + "\n";
                                }
                                if (product instanceof Electronics) {
                                        String brand = ((Electronics) product).getBrand();
                                        int warranty = ((Electronics) product).getWarranty_period();
                                        data = "Electronic, " + data + brand + "," + warranty + "\n";
                                }
                                System.out.println("qqqq");
                                myWriter.write(data);
                        }
                        myWriter.close();
                        System.out.println("---Information Successfully Stored---");
                } catch (Exception exception) {
                        System.out.println("An Error Occured!!!!");
                        exception.printStackTrace();
                }
        }

        public List<Product> LoadFile() {
                try {
                        BufferedReader reader = new BufferedReader(new FileReader("Product File"));
                        String line;
                        while ((line = reader.readLine()) != null) {
                                String[] values = line.split(",");
                                if (values.length < 7) {
                                        System.out.println("Invalid data format: " + line);
                                        continue;  // Skip to the next iteration
                                }
                                String Type = values[0].trim();
                                String productID = values[1].trim();
                                String productName = values[2].trim();
                                int num_of_available = Integer.parseInt(values[3].trim());
                                double price = Double.parseDouble(values[4].trim());
                                if (Objects.equals(Type, "Clothing")) {
                                        int size = Integer.parseInt(values[5].trim());
                                        String colour = values[6].trim();
                                        Product product = new Clothing(productID, productName, num_of_available, price, size, colour);
                                        products.add(product);
                                } else if (Objects.equals(Type, "Electronics")) {
                                        String brand = values[5].trim();
                                        int warranty = Integer.parseInt(values[6].trim());
                                        Product product = new Electronics(productID, productName, num_of_available, price, brand, warranty);
                                        products.add(product);
                                } else {
                                        //System.out.println("Invalid Product Type");
                                        break;
                                }
                                for (Product product: products){
                                        System.out.println(product.toString() + "\n");
                                };

                        }
                        reader.close();
                        System.out.println("Information successfully loaded from file");

                } catch (Exception exception) {
                        System.out.println("------An Error occured while loading data-------");
                        exception.printStackTrace();
                }
                return products;

        }
}
