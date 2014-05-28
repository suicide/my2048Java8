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
import java.util.Random;
import java.util.stream.IntStream;

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class Board {


  private Field field;

  public Board() {
    init();
  }

  private void init() {

    field = spawnRandom(spawnRandom(new Field()));

  }

  public Field getField() {
    return field;
  }

  public Field move(Direction direction) {

    Field newField = doMove(field, direction);

    field = spawnRandom(newField);

    return field;

  }

  private Field doMove(Field field, Direction direction) {
    Field newField = new Field();

    List<Tuple> coordinates = CoordinatesGenerator.coordinates(direction);


    for (int i = 0; i < Field.size; i++) {
      List<Cell> row = new ArrayList<>();
      for (int j = 0; j < Field.size; j++) {
        Tuple t = coordinates.get(i * Field.size + j);
        row.add(field.get(t.x, t.y));
      }

      List<Cell> newRow = lineMove(row);

      for (int j = 0; j < Field.size; j++) {
        Tuple t = coordinates.get(i * Field.size + j);
        newField.set(t.x, t.y, newRow.get(j));
      }
    }

    return newField;
  }

  List<Cell> lineMove(List<Cell> row) {
    List<Cell> newRow = slide(row);

    return merge(newRow);
  }

  private List<Cell> slide(List<Cell> row) {

    List<Cell> newRow = new ArrayList<>();


    for (Cell cell : row) {

      if (cell.getValue() != 0) {
        newRow.add(cell);
      }
    }

    fillUp(newRow);

    return newRow;

  }

  private List<Cell> merge(List<Cell> row) {
    List<Cell> newRow = new ArrayList<>();

    Cell previous = null;

    for (Cell cell : row) {

      if (cell.getValue() != 0) {

        if (cell.equals(previous) && cell.getValue() != 0) {
          newRow.add(CellFactory.getCell(cell.getValue() * 2));

          previous = null;
        } else {

          if (previous != null) {
            newRow.add(previous);
          }
          previous = cell;

        }
      }

    }

    if (previous != null) {
      newRow.add(previous);
    }

    fillUp(newRow);


    if (row.equals(newRow)) {
      return newRow;
    } else {
      return slide(newRow);
    }
  }

  private void fillUp(List<Cell> row) {
    int fillUp = Field.size - row.size();
    for (int i = 0; i < fillUp; i++) {
      row.add(CellFactory.getCell(0));
    }
  }

  private Field spawnRandom(Field field) {

    List<Tuple> emptyFields = new ArrayList<>();

    for (int x = 0; x < Field.size; x++) {
      for (int y = 0; y < Field.size; y++) {
        Cell cell = field.get(x, y);
        if (cell.getValue() == 0) {
          emptyFields.add(new Tuple(x, y));
        }
      }
    }

    Random r = new Random();
    int index = r.nextInt(emptyFields.size());

    int val = (r.nextInt(2) + 1) * 2;

    field.set(emptyFields.get(index).x, emptyFields.get(index).y, CellFactory.getCell(val));

    return field;
  }


  private static class RowChange {

    private List<Cell> row;

    private int score = 0;

  }

}
