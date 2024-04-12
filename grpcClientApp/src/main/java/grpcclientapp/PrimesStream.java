package grpcclientapp;

import io.grpc.stub.StreamObserver;
import servicestubs.IntNumber;

public class PrimesStream implements StreamObserver<IntNumber> {
    boolean completed=false;
    int start =0;
    int end=0;
    public PrimesStream (int start, int end){
        this.start=start;
        this.end = end;
    }
    @Override
    public void onNext(IntNumber intNumber) {
        System.out.println("Interval ["+ start + ", "+ end + "] "+ intNumber.getIntnumber());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Completed with error:"+throwable.getMessage());
        completed=true;
    }

    @Override
    public void onCompleted() {
        System.out.println("Prime numbers completed");
        completed=true;
    }
    public boolean isCompleted() {
        return completed;
    }
}
