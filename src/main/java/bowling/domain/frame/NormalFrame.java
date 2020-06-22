package bowling.domain.frame;

import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.frameStatus.NormalFrameStatus;

import java.util.Objects;

public class NormalFrame {
    private final int index;
    private final NormalFrameStatus currentStatus;
    private final NormalFrame previousFrame;

    NormalFrame(int index, NormalFrameStatus currentStatus, NormalFrame previousFrame) {
        this.index = index;
        this.currentStatus = currentStatus;
        this.previousFrame = previousFrame;
    }

    public static NormalFrame start(int numberOfHitPin) {
        return new NormalFrame(1, NormalFrameStatus.bowlFirst(numberOfHitPin), null);
    }

    public NormalFrame bowl(int numberOfHitPin) {
        if (this.currentStatus.isCompleted()) {
            throw new InvalidTryBowlException("완료된 프레임에서 추가로 투구할 수 없습니다.");
        }
        return new NormalFrame(index, currentStatus.bowl(numberOfHitPin), previousFrame);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(currentStatus, that.currentStatus) &&
                Objects.equals(previousFrame, that.previousFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, currentStatus, previousFrame);
    }
}
