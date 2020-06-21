package bowling.domain;

import bowling.domain.FrameResult.StrikeFrameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeFrameResultTests {
    @DisplayName("언제나 온전한 stike FrameResult임을 보장한다.")
    @Test
    void createTest() {
        StrikeFrameResult strikeFrameResult = new StrikeFrameResult();

        assertThat(strikeFrameResult.isStrikeResult()).isTrue();
        assertThat(strikeFrameResult.isCompleted()).isTrue();
    }
}
