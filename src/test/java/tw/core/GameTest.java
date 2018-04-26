package tw.core;
import org.junit.Before;
import org.junit.Test;
import tw.core.generator.AnswerGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        //Given
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");

        //When
        when(answerGenerator.generate()).thenReturn(actualAnswer);

        //Then
        game = new Game(answerGenerator);
    }

    @Test
    public void test_game_guess_should_return_1A2B_when_give_input_answer_1325() {
        //Given
        String inputAnswer = "1 3 2 5";
        Answer answer = Answer.createAnswer(inputAnswer);
        String expect = "1A2B";

        //Then
        assertThat(game.guess(answer).getResult(), is(expect));
    }

    @Test
    public void test_game_guess_guessHistory_should_have_1_item() {
        //Given
        String inputAnswer = "1 3 2 5";
        Answer answer = Answer.createAnswer(inputAnswer);
        int expect = 1;
        game.guess(answer);

        //Then
        assertThat(game.guessHistory().size(), is(expect));
    }

    @Test
    public void test_should_return_fail_when_give_6_wrong_answer() {
        //Given
        String[] answers = {"2 3 1 5", "5 3 4 6", "8 1 9 2",
                "2 3 7 4","3 1 2 5","8 1 2 6"};
        Answer answer;
        for (int i = 0; i < answers.length; i++) {
            answer = Answer.createAnswer(answers[i]);
            game.guess(answer);
        }
        String expect = "fail";

        //Then
        assertThat(game.checkStatus(), is(expect));
    }


    @Test
    public void test_should_return_continue_when_give_3_wrong_answer() {
        //Given
        String[] answers = {"2 3 1 5", "5 3 4 6", "8 1 9 2"};
        Answer answer;
        for (int i = 0; i < answers.length; i++) {
            answer = Answer.createAnswer(answers[i]);
            game.guess(answer);
        }
        String expect = "continue";

        //Then
        assertThat(game.checkStatus(), is(expect));
    }

    @Test
    public void test_should_return_true_when_give_3_wrong_answer() {
        //Given
        String[] answers = {"2 3 1 5", "5 3 4 6", "8 1 9 2"};
        Answer answer;
        for (int i = 0; i < answers.length; i++) {
            answer = Answer.createAnswer(answers[i]);
            game.guess(answer);
        }

        //Then
        assertTrue(game.checkCoutinue());
    }

    @Test
    public void test_should_return_false_when_give_6_wrong_answer() {
        //Given
        String[] answers = {"2 3 1 5", "5 3 4 6", "8 1 9 2",
                "2 3 7 4","3 1 2 5","8 1 2 6"};
        Answer answer;
        for (int i = 0; i < answers.length; i++) {
            answer = Answer.createAnswer(answers[i]);
            game.guess(answer);
        }

        //Then
        assertFalse(game.checkCoutinue());
    }
}
