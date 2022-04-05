package me.study.java.java11;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class Java11Test {

    @Test
    void string1() {
        // given
        String multilineString = "My name \n \n is \n Gump.";

        // when
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());

        // then
        assertThat(lines).containsExactly("My name", "is", "Gump.");
    }

    @Test
    void string2() {
        // given
        String string = "hello";

        // when
        String threeTimes = string.repeat(3);

        // then
        assertThat(threeTimes).isEqualTo("hellohellohello");
    }
}
