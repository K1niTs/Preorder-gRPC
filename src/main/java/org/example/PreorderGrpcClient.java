package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import preorder.Preorder;
import preorder.PreorderServiceGrpc;

public class PreorderGrpcClient {
    private final PreorderServiceGrpc.PreorderServiceBlockingStub stub;

    public PreorderGrpcClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = PreorderServiceGrpc.newBlockingStub(channel);
    }

    public Preorder.ProductDetailsResponse getProductDetails(String productId) {
        Preorder.ProductRequest request = Preorder.ProductRequest.newBuilder()
                .setProductId(productId)
                .build();

        return stub.getProductDetails(request);
    }

}
