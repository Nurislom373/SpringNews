package org.khasanof.userservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Author: Nurislom
 * <br/>
 * Date: 21.05.2023
 * <br/>
 * Time: 15:13
 * <br/>
 * Package: org.khasanof.userservice
 */
public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new GrpcUserService())
                .build();

        server.start();
        server.awaitTermination();
    }

}
