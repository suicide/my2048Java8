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
import java.util.stream.IntStream;

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class Field {

  private final List<List<Cell>> field;

  public static final int size = 4;

  public Field() {

    field = new ArrayList<>();

    IntStream.range(0, size).forEach(i -> {
      field.add(IntStream.range(0, size).collect(ArrayList::new, (l, j) -> l.add(CellFactory.getCell(0)), ArrayList::addAll));
    });

  }

  public Cell get(int x, int y) {
    check(x, y);
    return field.get(x).get(y);
  }

  public void set(int x, int y, Cell cell) {
    check(x, y);

    field.get(x).set(y, cell);
  }

  private void check(int x, int y) {

    if (x >= size || x < 0) {
      throw new IllegalArgumentException("x is out of bounds " + x);
    }

    if (y >= size || y < 0) {
      throw new IllegalArgumentException("y is out of bounds " + y);
    }
  }

}
