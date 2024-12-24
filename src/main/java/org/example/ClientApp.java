package org.example;

import preorder.Preorder;

public class ClientApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8081;

        PreorderGrpcClient client = new PreorderGrpcClient(host, port);

        String productId = "1";
        Preorder.ProductDetailsResponse response = client.getProductDetails(productId);

        System.out.println("Product Details:");
        System.out.println("ID: " + response.getProductId());
        System.out.println("Name: " + response.getName());
        System.out.println("Description: " + response.getDescription());
        System.out.println("Price: " + response.getPrice());
        System.out.println("Available: " + response.getAvailable());
    }
}
