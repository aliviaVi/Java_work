package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UdpSender implements Runnable {


    AtomicInteger counterTasks;
    String server_port;
    DatagramSocket socket = new DatagramSocket();


    public UdpSender(AtomicInteger counterTasks, String server_port) throws SocketException {
        this.counterTasks = counterTasks;
        this.server_port=server_port;
    }


    AtomicInteger integer;
    String server_port;
    DatagramSocket socket = new DatagramSocket();


    public UdpSender(AtomicInteger integer , String server_port) throws SocketException {
        this.integer = integer;
        this.server_port=server_port;
    }




    @Override
    public void run() {


            String integerString = counterTasks.toString();

            String integerString = integer.toString();

            String res=integerString.concat("#").concat(server_port);
            byte[] dataOut = res.getBytes();

            DatagramPacket packetOut = new DatagramPacket(
                    dataOut,
                    dataOut.length);
            try {
                socket.send(packetOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

