package grpcserverapp;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import forum.*;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Service extends ForumGrpc.ForumImplBase {
    private ConcurrentMap<String, ConcurrentMap<String,
            StreamObserver<ForumMessage>>> topicsSubscibers = new ConcurrentHashMap<>();

    public Service(int svcPort) {
        System.out.println("Service is available on port:" + svcPort);
    }

    /** MISSING: Add mutex for competition */
    //case stream server
    @Override
    public void topicSubscribe(SubscribeUnSubscribe request, StreamObserver<ForumMessage> responseObserver) {
        System.out.println("topicSubscribe called!");
        String userName = request.getUsrName();
        String topicName = request.getTopicName();
        String message = "Welcome to " + topicName + " topic!";

        // Check if the topic exists; if it doesn't create it
        if (!topicsSubscibers.containsKey(topicName)) {
            topicsSubscibers.put(topicName, new ConcurrentHashMap<>());
        }

        //Check if username is already exists
        if (!topicsSubscibers.get(topicName).containsKey(userName)) {
            topicsSubscibers.get(topicName).put(userName, responseObserver);
            System.out.println("Subscription completed!");
        } else {
            System.out.println("You have already subscribed. If not, that username is already in use, pick another!");
        }

        responseObserver.onNext(ForumMessage.newBuilder()
                .setFromUser(userName)
                .setTopicName(topicName)
                .setTxtMsg(message)
                .build());
    }

    /** MISSING: Add mutex for competition */
    //case stream server
    @Override
    public void topicUnSubscribe(SubscribeUnSubscribe request, StreamObserver<Empty> responseObserver) {
        System.out.println("topicUnSubscribe called!");
        String userName = request.getUsrName();
        String topicName = request.getTopicName();

        //check if topic exists
        if(topicsSubscibers.containsKey(topicName)){
            //check if user is subscribed to that topic
            if (topicsSubscibers.get(topicName).containsKey(userName)) {
                topicsSubscibers.get(topicName).remove(userName);
                System.out.println("User unsubscribed from topic!");
            }else{
                System.out.println("You are not subscribed to this topic!");
            }
        }else{
            System.out.println("That Topic doesn't exist!");
        }
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    //case stream server
    @Override
    public void getAllTopics(Empty request, StreamObserver<ExistingTopics> responseObserver) {
        System.out.println("Getting all topics!");

        ExistingTopics.Builder topicListBuilder = ExistingTopics.newBuilder();
        for (String topicName : topicsSubscibers.keySet()) {
            topicListBuilder.addTopicName(topicName);
        }
        ExistingTopics topicList = topicListBuilder.build();

        responseObserver.onNext(topicList);
        responseObserver.onCompleted();
    }

    //case stream server
    @Override
    public void publishMessage(ForumMessage request, StreamObserver<Empty> responseObserver) {
        System.out.println("publishMessage called!");
        String topicName = request.getTopicName();

        //check if topic exists
        if(topicsSubscibers.containsKey(topicName)){
            //check if user is subscribed to that topic
            if (topicsSubscibers.get(topicName).containsKey(request.getFromUser())) {
                ConcurrentMap<String, StreamObserver<ForumMessage>> map = topicsSubscibers.get(topicName);
                Collection<StreamObserver<ForumMessage>> streamObserverCollection= map.values();
                /* for ( String user: map.keySet()) {
                     map.get(user).onNext(request);
                     System.out.println("Message has been sent!");
                 }*/

                for(StreamObserver<ForumMessage> observer: streamObserverCollection){
                    observer.onNext(ForumMessage.newBuilder()
                            .setFromUser(request.getFromUser())
                            .setTopicName(topicName)
                            .setTxtMsg(request.getTxtMsg())
                            .build());
                }
                System.out.println("Message published to all users!");

            }else{
                System.out.println("You are not subscribed to this topic!");
            }
        }else{
            System.out.println("That Topic doesn't exist!");
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}

