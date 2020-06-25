package bowling.domain;

import bowling.domain.exceptions.AlreadyStartedPlayerException;
import bowling.domain.exceptions.NotStartedPlayerException;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.Objects;

public class Player {
    private final String name;
    private Frame currentFrame;

    Player(String name, Frame currentFrame) {
        this.name = name;
        this.currentFrame = currentFrame;
    }

    public static Player createByName(String playerName) {
        return new Player(playerName, null);
    }

    public Frame bowlFirstRefactor(int numberOfHitPin) {
        validateBowlFirst();

        NormalFrame firstFrame = NormalFrame.start(numberOfHitPin);
        this.currentFrame = firstFrame;

        return firstFrame;
    }

    public boolean isCurrentFrameCompleted() {
        validateIsStarted();
        return this.currentFrame.isCompleted();
    }

    public Frame bowlCurrentFrameRefactor(int numberOfHitPin) {
        Frame bowledFrame = this.currentFrame.bowl(numberOfHitPin);
        this.currentFrame = bowledFrame;

        return bowledFrame;
    }

    public Frame toNextFrameRefactor(int numberOfHitPin) {
        Frame nextFrame = this.currentFrame.next(numberOfHitPin);
        this.currentFrame = nextFrame;

        return nextFrame;
    }

    public String getName() {
        return name;
    }

    public int getCurrentFrameIndex() {
        return this.currentFrame.getIndex();
    }

    Frame getCurrentFrame() {
        return this.currentFrame;
    }

    private void validateIsStarted() {
        if (this.currentFrame == null) {
            throw new NotStartedPlayerException("초구를 굴리지 않은 상태에서 할 수 없는 동작입니다.");
        }
    }

    private void validateBowlFirst() {
        if (this.currentFrame != null) {
            throw new AlreadyStartedPlayerException("이미 투구한 참가자는 초구를 굴릴 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(currentFrame, player.currentFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currentFrame);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", currentFrame=" + currentFrame +
                '}';
    }
}
