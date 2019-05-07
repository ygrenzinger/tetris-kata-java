package com.kata.tetris.infra;

import com.google.gson.Gson;
import com.kata.tetris.domain.tetromino.Shape;
import com.kata.tetris.domain.tetromino.ShapeType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ShapeLoader {

    Shape loadShape(ShapeType type) {
        try {
            URL resource = Shape.class.getResource(type.name() + ".json");
            Path path = Paths.get(resource.toURI());
            return loadShape(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Impossible to find file for shape " + type, e);
        }
    }

    Shape loadShape(Path path) {
        try {
            String content = String.join("", Files.readAllLines(path));
            return new Gson().fromJson(content, Shape.class);
        } catch (IOException e) {
            throw new RuntimeException("Impossible to load shape " + path, e);
        }
    }
}
