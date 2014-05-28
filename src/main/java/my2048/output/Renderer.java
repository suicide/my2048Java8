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

package my2048.output;

import my2048.game.Board;

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class Renderer {

  public void render(Board board, int score) {

    String separator = "-------------------------------------";
    System.out.println(separator);

    for (int x = 0; x < Board.size; x++) {
      Integer[] row = new Integer[Board.size];
      for (int y = 0; y < Board.size; y++) {

        row[y] = board.get(x, y).getValue();

      }

      System.out.printf("| %6d | %6d | %6d | %6d |\n", row);
      System.out.println(separator);
    }
    System.out.println("Score: " + score);

  }

}
