package grpcclientapp;

import forum.ExistingTopics;
import io.grpc.stub.StreamObserver;

public class ExistingTopicsStream implements StreamObserver<ExistingTopics> {
    boolean completed=false;
    @Override
    public void onNext(ExistingTopics topic) {
            for(int i=0; i< topic.getTopicNameCount(); i++){
                System.out.println("Topic: "+ topic.getTopicName(i));
            }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Completed with error:"+throwable.getMessage());
        completed=true;
    }

    @Override
    public void onCompleted() {
        System.out.println("Existing topics completed!");
        completed=true;
    }
    public boolean isCompleted() {
        return completed;
    }
}
