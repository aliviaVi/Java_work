package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.valueOf;

public class Main {

    static final int TCP_INPUT_PORT=2001; //  check outerServer Port ARINA
    private static final int UDP_PORT = 3001; // check udpSendingPort Lena
    private static final String SERVER_HOST = "localhost";
    public static AtomicInteger counterTasks=new AtomicInteger(0);

    public static void main(String[] args) throws IOException {




        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));//TCP_INPUT_PORT);
        PropertiesService propertiesService=new PropertiesService("config/config.props");


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> operationPath = propertiesService.getOperationPaths();
        OperationsProvider op = new OperationsProvider(operationPath);
        op.init();

        TcpReceiverHandler receiverService;


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        UdpSender udpSender = new UdpSender(counterTasks, args[1]);


        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                udpSender,
                2,
                1,
                TimeUnit.SECONDS);



        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                udpSender,
                2,
                1,
                TimeUnit.SECONDS);


        while (true) {

            Socket socket = server.accept();
 
            counterTasks.getAndIncrement();

            receiverService = new TcpReceiverHandler(op,socket, counterTasks);
            executorService.execute(receiverService);
        }


    }
}
