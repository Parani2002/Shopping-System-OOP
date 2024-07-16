import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class ConsoleMenu {
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static User user = new User("Parani","sssss");


    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        menuloop:
        while (true){
            displayMenu();
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> deleteProduct();
                case 3 -> manager.displayProducts();
                case 4 -> manager.saveFile();
                case 5 -> new MainGUI();
                case 6 -> manager.LoadFile();
                case 0 -> {
                    System.out.println("Exit the Product Management System.Thank You...");
                    break menuloop;
                }
                default -> System.out.println("Invalid choice...Please try again");
            }
        }
    }

    public static void displayMenu(){
        System.out.println("-----Welcome to the Shopping Management Menu-----");
        System.out.println("1.Add Product");
        System.out.println("2.Delete Product");
        System.out.println("3.Print the list of All Products");
        System.out.println("4.Save to file");
        System.out.println("5.Open the GUI");
        System.out.println("6.Load from file");
        System.out.println("0.Exit");
    }
    public static void deleteProduct(){
        System.out.println("Please enter the productID :(CL-XXXX/EL-XXXX)");
        String productID = scanner.next();
        manager.deleteProduct(productID);
    }
    public static void addProduct(){
        Product product;

        System.out.println("Please enter the type of product : electronics/clothing ");
        String type = scanner.next().toLowerCase();

        System.out.println("Please enter the productID:(CL-XXXX/EL-XXXX) ");
        String productID = scanner.next().toUpperCase();
        String regex = "^(EL|CL)-\\d{4}";
        if (productID.matches(regex)){

        }else{
            System.out.println("Please enter valid productID!!!");
            return;
        }
        name:
        System.out.println("Please enter the product name :");
        String productName = scanner.next();

        System.out.println("Please enter the number of available items: ");
        int num_of_available = scanner.nextInt();

        System.out.println("Please enter the price of the product: ");
        double price = scanner.nextDouble();

        if (type.equals("clothing")){
            System.out.println("Please enter the size of the cloth: ");
            int size = scanner.nextInt();

            System.out.println("Please enter the color of the clothing: ");
            String colour = scanner.next();

            product = new Clothing(productID,productName,num_of_available,price,size,colour);
            manager.addProduct(product);


        }else if (type.equals("electronics")) {
            System.out.println("Please enter the brand of the product: ");
            String brand = scanner.next();

            System.out.println("Please enter the warranty period the product: ");
            int warranty_period = scanner.nextInt();

            product = new Electronics(productID,productName,num_of_available,price,brand,warranty_period);
            manager.addProduct(product);
        }



    }

}
