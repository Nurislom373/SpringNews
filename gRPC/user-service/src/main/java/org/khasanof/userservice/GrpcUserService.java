package org.khasanof.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.khasanof.user_service.GetUser;
import org.khasanof.user_service.ServerResponse;
import org.khasanof.user_service.UserData;
import org.khasanof.user_service.UserServiceGrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author: Nurislom
 * <br/>
 * Date: 21.05.2023
 * <br/>
 * Time: 15:01
 * <br/>
 * Package: org.khasanof.userservice
 */
public class GrpcUserService extends UserServiceGrpc.UserServiceImplBase {

    private final Map<Long, UserData> map = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void register(UserData request, StreamObserver<ServerResponse> responseObserver) {
        long nextLong = new Random().nextLong(99999);
        map.put(nextLong, request);
        ServerResponse response = ServerResponse.newBuilder()
                .setData(String.valueOf(nextLong))
                .setSuccess(true)
                .setMessage("Successfully Registered User")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Override
    public void getById(GetUser request, StreamObserver<ServerResponse> responseObserver) {
        if (map.containsKey(request.getId())) {
            ServerResponse response = ServerResponse.newBuilder()
                    .setData(objectMapper.writeValueAsString(map.get(request.getId())))
                    .setSuccess(true)
                    .setMessage("Successfully Registered User")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("User not found!"));
            responseObserver.onCompleted();
        }
    }
}
