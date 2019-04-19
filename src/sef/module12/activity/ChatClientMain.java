package sef.module12.activity;

public class ChatClientMain {
        public static void main(String[] args) {
            ChatClient c1 = new ChatClient();
            ChatClient c2 = new ChatClient();
            c1.start();
            c2.start();
        }

    }
