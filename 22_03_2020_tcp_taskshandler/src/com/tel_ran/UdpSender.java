package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;

public class UdpSender implements Runnable {

    AtomicInteger integer;
    String server_port;
    DatagramSocket socket = new DatagramSocket();


    public UdpSender(AtomicInteger integer , String server_port) throws SocketException {
        this.integer = integer;
        this.server_port=server_port;
    }


   // private static final int SERVER_PORT = 3001;


    @Override
    public void run() {

            String integerString = integer.toString();
            String res=integerString.concat("#").concat(String.valueOf(server_port));
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

