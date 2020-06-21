package bowling.domain;

import bowling.domain.frameResult.FrameResult;

public class FinalFrame {
    private FrameResult firstFrameResult;
    private FrameResult secondFrameResult;

    FinalFrame(FrameResult firstFrameResult, FrameResult secondFrameResult) {
        this.firstFrameResult = firstFrameResult;
        this.secondFrameResult = secondFrameResult;
    }

    public static FinalFrame firstBowl(int numberOfHitPin) {
        return new FinalFrame(FrameResultFactory.create(numberOfHitPin), null);
    }

    public void bowl(int numberOfHitPin) {
        this.secondFrameResult = FrameResultFactory.create(numberOfHitPin);
    }

    public boolean isCompleted() {
        return (firstFrameResult.isCompleted() && secondFrameResult.isCompleted());
    }
}
