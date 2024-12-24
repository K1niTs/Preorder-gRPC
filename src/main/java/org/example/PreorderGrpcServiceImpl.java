package org.example;

import io.grpc.stub.StreamObserver;
import org.example.DTO.ProductDTO;
import org.example.services.ProductService;
import preorder.Preorder;
import preorder.PreorderServiceGrpc;

public class PreorderGrpcServiceImpl extends PreorderServiceGrpc.PreorderServiceImplBase {

    private final ProductService productService;

    public PreorderGrpcServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void getProductDetails(Preorder.ProductRequest request, StreamObserver<Preorder.ProductDetailsResponse> responseObserver) {
        var product = productService.getProductById(Long.parseLong(request.getProductId()));

        if (product.isPresent()) {
            ProductDTO productDTO = product.get();
            Preorder.ProductDetailsResponse response = Preorder.ProductDetailsResponse.newBuilder()
                    .setProductId(productDTO.getId().toString())
                    .setName(productDTO.getName())
                    .setDescription(productDTO.getDescription())
                    .setPrice(productDTO.getPrice())
                    .setAvailable(true)
                    .build();

            responseObserver.onNext(response);
        } else {
            responseObserver.onError(new Exception("Product not found"));
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getProductAvailability(Preorder.ProductRequest request, StreamObserver<Preorder.AvailabilityResponse> responseObserver) {
        boolean available = productService.getProductById(Long.parseLong(request.getProductId())).isPresent();

        Preorder.AvailabilityResponse response = Preorder.AvailabilityResponse.newBuilder()
                .setProductId(request.getProductId())
                .setAvailable(available)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
