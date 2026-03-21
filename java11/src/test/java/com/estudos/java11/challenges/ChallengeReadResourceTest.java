package com.estudos.java11.challenges;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ChallengeReadResourceTest {

    @Test
    void leArquivoUtf8(@TempDir Path dir) throws IOException {
        Path f = dir.resolve("dados.txt");
        Files.writeString(f, "conteúdo\nlinha2");
        assertThat(ChallengeReadResource.lerUtf8(f)).isEqualTo("conteúdo\nlinha2");
    }
}
