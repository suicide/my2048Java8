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

import my2048.game.Field;

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class Renderer {

  public void render(Field field, int score) {

    String separator = "-------------------------------------";
    System.out.println(separator);

    for (int x = 0; x < Field.size; x++) {
      Integer[] row = new Integer[Field.size];
      for (int y = 0; y < Field.size; y++) {

        row[y] = field.get(x, y).getValue();

      }

      System.out.printf("| %6d | %6d | %6d | %6d |\n", row);
      System.out.println(separator);
    }
    System.out.println("Score: " + score);

  }

}
