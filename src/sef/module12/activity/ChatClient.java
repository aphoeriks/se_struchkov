package sef.module12.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.*;

public class ChatClient extends Thread{
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;

        public void run() {
            Scanner scan = new Scanner(System.in);

            try {

                this.socket = new Socket(InetAddress.getLocalHost(), 9998);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);



                Resender resend = new Resender();
                resend.start();


                String str = "";
                while (!str.equals("exit")) {
                    str = scan.nextLine();
                    out.println(str);
                }
                resend.setStop();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }


        private void close() {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                System.err.println("Streams wasn`t closed");
            }
        }


        private class Resender extends Thread {

            private boolean stoped;


            public void setStop() {
                stoped = true;
            }


            @Override
            public void run() {
                try {
                    while (!stoped) {
                        String str = in.readLine();
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при получении сообщения.");
                    e.printStackTrace();
                }
            }
        }
}
