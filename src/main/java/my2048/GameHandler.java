/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package my2048;

import my2048.game.Board;
import my2048.game.Direction;
import my2048.output.Renderer;

import java.util.Scanner;

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class GameHandler {

  public void play() {

    Renderer renderer = new Renderer();

    Board board = new Board();

    Scanner scanner = new Scanner(System.in);

    renderer.render(board.getField());

    while(true) {

      System.out.println("What direction now?");
      String key = scanner.next();

      Direction d = mapDirection(key);

      renderer.render(board.move(d));
    }

  }

  private Direction mapDirection(String key) {

    Direction direction;

    switch (key) {
      case "w":
        direction = Direction.UP;
        break;
      case "a":
        direction = Direction.LEFT;
        break;
      case "s":
        direction = Direction.DOWN;
        break;
      case "d":
        direction = Direction.RIGHT;
        break;
      default:
        throw new InvalidKeyException(key + " is not a valid direction");
    }

    return direction;
  }

  private static class InvalidKeyException extends RuntimeException {

    private InvalidKeyException(String message) {
      super(message);
    }
  }

}
