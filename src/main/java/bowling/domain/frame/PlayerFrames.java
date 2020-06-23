package bowling.domain.frame;

import bowling.domain.FrameResults;
import bowling.domain.exceptions.CannotDoInEmptyPlayerFramesException;
import bowling.domain.exceptions.ExceedLimitOfFramesException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerFrames {
    private static final int MAX_SIZE = 10;

    private final List<Frame> playerFrameList;

    public PlayerFrames(List<Frame> playerFrameList) {
        this.playerFrameList = new ArrayList<>(playerFrameList);
    }

    public static PlayerFrames createEmpty() {
        return new PlayerFrames(new ArrayList<>());
    }

    public int size() {
        return this.playerFrameList.size();
    }

    public PlayerFrames lastValue(Frame frame) {
        validateSize();

        if (this.size() == 0) {
            return new PlayerFrames(Collections.singletonList(frame));
        }

        if (this.isLastCompleted()) {
            return addNewFrame(frame);
        }

        return updateLastFrame(frame);
    }

    public List<FrameResults> calculateResult() {
        return playerFrameList.stream()
                .map(Frame::calculateCurrentResults)
                .collect(Collectors.toList());
    }

    boolean isLastCompleted() {
        return this.getLast().isCompleted();
    }

    private void validateSize() {
        if (this.size() == MAX_SIZE && isLastCompleted()) {
            throw new ExceedLimitOfFramesException("더이상 프레임을 추가하거나 업데이트 할 수 없습니다.");
        }
    }

    private PlayerFrames addNewFrame(Frame frame) {
        ArrayList<Frame> resultFrames = new ArrayList<>(playerFrameList);
        resultFrames.add(frame);

        return new PlayerFrames(resultFrames);
    }

    private PlayerFrames updateLastFrame(Frame frame) {
        ArrayList<Frame> resultFrames = new ArrayList<>(playerFrameList);
        resultFrames.set(this.playerFrameList.size() - 1, frame);

        return new PlayerFrames(resultFrames);
    }

    private Frame getLast() {
        if (playerFrameList.size() == 0) {
            throw new CannotDoInEmptyPlayerFramesException("빈 컬렉션에서 할수 없는 동작입니다.");
        }

        return this.playerFrameList.get(this.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerFrames that = (PlayerFrames) o;
        return Objects.equals(playerFrameList, that.playerFrameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerFrameList);
    }

    @Override
    public String toString() {
        return "PlayerFrames{" +
                "playerFrameList=" + playerFrameList +
                '}';
    }
}
