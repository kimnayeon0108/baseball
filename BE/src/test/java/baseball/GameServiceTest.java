package baseball;

import baseball.domain.Game;
import baseball.domain.Team;
import baseball.repository.GameRepository;
import baseball.repository.TeamRepository;
import baseball.service.GameService;
import baseball.service.dto.ScoreRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private ScoreRequest scoreRequest;

    @MockBean
    private Game game;

    @MockBean
    private Team team;

    @Test
    void saveScore() {
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(game.isTeamInGame(team)).thenReturn(true);

        gameService.saveScore(1L, 1L, scoreRequest);

        verify(scoreRequest, times(1)).toScore();
        verify(teamRepository, times(1)).save(any(Team.class));
    }
}
