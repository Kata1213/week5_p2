package tw.core;
import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {
    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() throws Exception {
        this.randomIntGenerator = new RandomIntGenerator();
    }

    @Test
    public void test_should_generate_4_num() {
        //Given
        String result = randomIntGenerator.generateNums(10, 4);
        int length = result.split(" ").length;
        int expectLength = 4;

        //Then
        assertThat(length, is(expectLength));
    }

    @Test
    public void test_should_have_digit() {
        //Given
        String result = randomIntGenerator.generateNums(10, 4);
        long actual = Arrays.stream(result.split(" "))
                .map(Integer::parseInt).count();
        long expect = 4;

        //Then
        assertThat(actual, is(expect));
    }

    @Test
    public void test_should_have_0_to_9_digit() {
        //Given
        String result = randomIntGenerator.generateNums(10, 4);
        long actual = Arrays.stream(result.split(" "))
                .map(Integer::parseInt)
                .filter(num -> num <= 9 && num >= 0).count();
        long expect = 4;

        //Then
        assertThat(actual, is(expect));
    }

    @Test
    public void test_should_have_4_diff_digit() {
        //Given
        String result = randomIntGenerator.generateNums(10, 4);
        long actual = Arrays.stream(result.split(" "))
                .map(Integer::parseInt).distinct()
                .filter(num -> num <= 9 && num >= 0).count();
        long expect = 4;

        //Then
        assertThat(actual, is(expect));
    }
}