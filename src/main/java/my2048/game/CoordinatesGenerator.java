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

package my2048.game;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class CoordinatesGenerator {

  private CoordinatesGenerator() {}

  public static List<Tuple> coordinates(Direction direction) {
    // this sucks :(

    List<Tuple> coordinates = new ArrayList<>();

    switch (direction) {
      case LEFT:
        for (int x = 0; x < Board.SIZE; x++) {
          for (int y = 0; y < Board.SIZE; y++) {
            coordinates.add(new Tuple(x,y));
          }
        }
        break;
      case RIGHT:
        for (int x = 0; x < Board.SIZE; x++) {
          for (int y = Board.SIZE - 1; y >= 0; y--) {
            coordinates.add(new Tuple(x, y));
          }
        }
        break;
      case UP:
        for (int y = 0; y < Board.SIZE; y++) {
          for (int x = 0; x < Board.SIZE; x++) {
            coordinates.add(new Tuple(x,y));
          }
        }
        break;
      case DOWN:
        for (int y = 0; y < Board.SIZE; y++) {
          for (int x = Board.SIZE - 1; x >= 0; x--) {
            coordinates.add(new Tuple(x,y));
          }
        }
        break;
    }

    return coordinates;
  }

}
