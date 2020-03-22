package com.tel_ran;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;

public class UdpSender implements Runnable {
    AtomicInteger integer;

    public UdpSender(AtomicInteger integer) throws SocketException, UnknownHostException {
        this.integer = integer;
    }

    private static final int INCOMING_DATAGRAM_SIZE = 1024;
    private static final int SERVER_PORT = 3000;
    private static final String SERVER_HOST = "localhost";
    public boolean isAlive = true;


    DatagramSocket socket = new DatagramSocket();
    InetAddress serverIp = InetAddress.getByName(SERVER_HOST);

    byte[] dataIn = new byte[INCOMING_DATAGRAM_SIZE];
    DatagramPacket packetIn = new DatagramPacket(dataIn, INCOMING_DATAGRAM_SIZE);

    @Override
    public void run() {
        while (isAlive) {
            byte[] dataOut = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(integer.intValue()).array();

            DatagramPacket packetOut = new DatagramPacket(dataOut,
                    dataOut.length,
                    serverIp,
                    SERVER_PORT);
            try {
                socket.send(packetOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket.close();

        }


    }
}

