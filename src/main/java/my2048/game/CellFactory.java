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

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class CellFactory {

  private static final Cell EMPTY_CELL = new EmptyCell();

  private CellFactory() {
  }

  public static Cell getCell(int value) {

    if (value % 2 != 0) {
      throw new IllegalArgumentException("Value must be mulitple of 2");
    }

    if (value <= 0) {
      return EMPTY_CELL;
    } else {
      return new ValueCell(value);
    }

  }

  private static class EmptyCell implements Cell {

    @Override
    public int getValue() {
      return 0;
    }

    @Override
    public String toString() {
      return "{ 0 }";
    }
  }

  public static class ValueCell implements Cell {

    private final int value;

    public ValueCell(int value) {
      this.value = value;
    }

    @Override
    public int getValue() {
      return value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ValueCell cell = (ValueCell) o;

      if (value != cell.value) return false;

      return true;
    }

    @Override
    public int hashCode() {
      return value;
    }

    @Override
    public String toString() {
      return "{ " + value + " }";
    }
  }

}
