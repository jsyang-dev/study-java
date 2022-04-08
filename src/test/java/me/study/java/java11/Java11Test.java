package me.study.java.java11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Java 11 신규 기능")
public class Java11Test {

    @Test
    @DisplayName("String 클래스 메서드 추가 1")
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
    @DisplayName("String 클래스 메서드 추가 2")
    void string2() {
        // given
        String string = "hello";

        // when
        String threeTimes = string.repeat(3);

        // then
        assertThat(threeTimes).isEqualTo("hellohellohello");
    }

    @Test
    @DisplayName("File 클래스 메서드 추가")
    void file() throws IOException {
        // given
        Path tempFile = Files.createTempFile(Paths.get("target"), "demo", ".txt");

        // when
        Path path = Files.writeString(tempFile, "Sample text");
        String content = Files.readString(path);

        // then
        assertThat(content).isEqualTo("Sample text");
    }

    @Test
    @DisplayName("Collection 인터페이스 메서드 추가")
    void collection() {
        // given
        List<String> sampleList = List.of("Java", "Kotlin");

        // when
        String[] sampleArray = sampleList.toArray(String[]::new);

        // then
        assertThat(sampleArray).containsExactly("Java", "Kotlin");
    }

    @Test
    @DisplayName("Predicate 인터페이스 메서드 추가")
    void predicate() {
        // given
        List<String> sampleList = List.of("Java", "\n \n", "Kotlin", " ");

        // when
        List<String> withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());

        // then
        assertThat(withoutBlanks).containsExactly("Java", "Kotlin");
    }

    @Test
    @DisplayName("람다에서 var 사용")
    void lambda() {
        // given
        List<String> sampleList = List.of("Java", "Kotlin");

        // when
        String resultString = sampleList.stream()
                .map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));

        // then
        assertThat(resultString).isEqualTo("JAVA, KOTLIN");
    }

    @Test
    @DisplayName("HTTP Client")
    void httpClient() throws IOException, InterruptedException {
        // given
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.ip.pe.kr/"))
                .build();

        // when
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // then
        assertThat(httpResponse.body()).isNotEmpty();
    }

    @Test
    @DisplayName("Nest-Based Access Control")
    void nestmate() {
        // when
        String name = Outer.Inner.class.getNestHost().getName();
        boolean nestmateOf = Outer.Inner.class.isNestmateOf(Outer.class);
        Set<String> nestMembers = Arrays.stream(Outer.Inner.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toSet());

        // then
        assertThat(name).isEqualTo("me.study.java.java11.Outer");
        assertThat(nestmateOf).isTrue();
        assertThat(nestMembers).contains("me.study.java.java11.Outer", "me.study.java.java11.Outer$Inner");
    }
}
