package grpcclientapp;


import forum.ForumMessage;
import io.grpc.stub.StreamObserver;

public class ForumMessageStream implements StreamObserver<ForumMessage> {
    boolean completed=false;
    @Override
    public void onNext(ForumMessage forumMessage) {
        System.out.println("Message from " + forumMessage.getFromUser() + " on topic "
                + forumMessage.getTopicName() + ": " + forumMessage.getTxtMsg());
    }


    @Override
    public void onError(Throwable throwable) {
        System.out.println("Completed with error: "+throwable.getMessage());
        completed=true;
    }

    @Override
    public void onCompleted() {
        System.out.println("Forum message stream completed!");
        completed=true;
    }
    public boolean isCompleted() {
        return completed;
    }
}
