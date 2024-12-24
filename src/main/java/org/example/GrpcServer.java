package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GrpcServer {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(GrpcServer.class, args);

        ProductService productService = context.getBean(ProductService.class);

        Server server = ServerBuilder.forPort(8081)
                .addService(new PreorderGrpcServiceImpl(productService))
                .build();

        System.out.println("Preorder gRPC server started on port 8081...");
        server.start();
        server.awaitTermination();
    }
}
