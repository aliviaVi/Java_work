package com.tel_ran;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final int PORT=2000; // check outerServer Port ARINA

    public static void main(String[] args) throws IOException {


        ServerSocket server = new ServerSocket(PORT);
        PropertiesService propertiesService=new PropertiesService("config/config.props");


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> operationPath = propertiesService.getOperationPaths();
        OperationsProvider op = new OperationsProvider(operationPath);
        op.init();


        while (true) {
            Socket socket = server.accept();

            TcpReceiverHandler receiverService = new TcpReceiverHandler(op,socket);

            executorService.execute(receiverService);

        }
    }
}
