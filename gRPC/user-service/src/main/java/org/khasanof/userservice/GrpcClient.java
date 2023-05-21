package org.khasanof.userservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.khasanof.user_service.*;

import java.util.Iterator;

/**
 * Author: Nurislom
 * <br/>
 * Date: 21.05.2023
 * <br/>
 * Time: 15:25
 * <br/>
 * Package: org.khasanof.userservice
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(managedChannel);
        Iterator<ServerResponse> iterator = stub.register(UserData.newBuilder()
                .setEmail("khasanof373@gmail.com")
                .setLanguage(LANGUAGE.RU)
                .setStatus(STATUS.ACTIVE)
                .setFirstName("Nurislom")
                .setLastName("Xasanov")
                .build());

        iterator.forEachRemaining(o -> System.out.println("o.getData() = " + o.getData()));
        managedChannel.shutdown();
    }

}
