package grpcclientapp;

import com.google.protobuf.Empty;
import forum.ForumGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import forum.*;
import java.util.*;


public class Client {
    // generic ClientApp for Calling a grpc Service
    private static String svcIP = "localhost";
    private static int svcPort = 8000;
    private static ManagedChannel channel;
    private static ForumGrpc.ForumBlockingStub blockingStub;
    private static ForumGrpc.ForumStub noBlockStub;

    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                svcIP = args[0]; svcPort = Integer.parseInt(args[1]);
            }
            System.out.println("connect to " + svcIP + ":" + svcPort);
            channel = ManagedChannelBuilder.forAddress(svcIP, svcPort)
                    // Channels are secure by default (via SSL/TLS).
                    // For the example we disable TLS to avoid
                    // needing certificates.
                    .usePlaintext()
                    .build();
            blockingStub = ForumGrpc.newBlockingStub(channel);
                noBlockStub = ForumGrpc.newStub(channel);
            // Call service operations for example ping server
            boolean end = false;
            while (!end) {
                try {
                    int option = Menu();
                    switch (option) {
                        case 1:
                            subscribeToTopic();  break;
                        case 2:
                            unsubscribeToTopic(); break;
                        case 3:
                            getAllTopics(); break;
                        case 4:
                            publishMessageToForum(); break;
                        case 99:  System.exit(0);
                    }
                } catch (Exception ex) {
                    System.out.println("Execution call Error  !");
                    ex.printStackTrace();
                }
            }
            read("prima enter to end", new Scanner(System.in));
        } catch (Exception ex) {
            System.out.println("Unhandled exception");
            ex.printStackTrace();
        }
    }

    static void subscribeToTopic() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a username:");
        String userName = scanner.nextLine();

        System.out.println("Pick a topic:");
        String topicName = scanner.nextLine();

        ForumMessageStream forumMessageStream = new ForumMessageStream();
        noBlockStub.topicSubscribe(SubscribeUnSubscribe.newBuilder().setUsrName(userName)
                .setTopicName(topicName).build(), forumMessageStream);
    }

    static void unsubscribeToTopic() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your username:");
        String userName = scanner.nextLine();

        System.out.println("Pick a topic to unsubscribe from:");
        String topicName = scanner.nextLine();

        EmptyStream emptyStream = new EmptyStream();
        SubscribeUnSubscribe req = SubscribeUnSubscribe.newBuilder().setUsrName(userName)
                .setTopicName(topicName).build();
        noBlockStub.topicUnSubscribe(req,emptyStream);

    }
    static void getAllTopics() {
        ExistingTopicsStream existingTopicsStream = new ExistingTopicsStream();
        noBlockStub.getAllTopics(Empty.newBuilder().build(), existingTopicsStream);
    }
    static void publishMessageToForum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your username:");
        String userName = scanner.nextLine();

        System.out.println("Pick a topic:");
        String topicName = scanner.nextLine();

        System.out.println("Write your message:");
        String message = scanner.nextLine();

        EmptyStream emptyStream = new EmptyStream();
        noBlockStub.publishMessage(ForumMessage.newBuilder().setFromUser(userName).setTopicName(topicName)
                .setTxtMsg(message).build(), emptyStream);
    }


    private static int Menu() {
        int op;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("    MENU");
            System.out.println(" 1 - Subscribe to a topic");
            System.out.println(" 2 - Unsubscribe to a topic");
            System.out.println(" 3 - Get all existing topics");
            System.out.println(" 4 - Publish a message");
            System.out.println("99 - Exit");
            System.out.println();
            System.out.println("Choose an Option?");
            op = scan.nextInt();
        } while (!((op >= 1 && op <= 4) || op == 99));
        return op;
    }

    private static String read(String msg, Scanner input) {
        System.out.println(msg);
        return input.nextLine();
    }
}
