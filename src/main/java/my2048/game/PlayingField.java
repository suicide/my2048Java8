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
public class PlayingField {


  private Field field;

  private void init() {

    field = new Field();

  }

  public Field getField() {
    return field;
  }

  public Field move(Direction direction) {

    Field newField = new Field();

    newField = doMove(newField, direction);

    field = spawnRandom(newField);

    return field;

  }

  private Field doMove(Field field, Direction direction) {
    if (direction == Direction.LEFT) {

      IntStream.range(0, Field.size).forEach(x -> {
        List<Cell> cells = IntStream.range(0, Field.size).collect(ArrayList::new,
          (list, y) -> list.add(field.get(x, y)), ArrayList::addAll);


      });

    }

    return null;
  }

  List<Cell> crush(List<Cell> row) {
    List<Cell> newRow = doCrush(row);

    return merge(newRow);
  }

  private List<Cell> doCrush(List<Cell> row) {

    List<Cell> newRow = new ArrayList<>();


    for (Cell cell : row) {

      if (cell.getValue() != 0) {
        newRow.add(cell);
      }
    }

    int fillUp = Field.size - newRow.size();
    for (int i = 0; i < fillUp; i++) {
      newRow.add(CellFactory.getCell(0));
    }

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

    int fillUp = Field.size - newRow.size();
    for (int i = 0; i < fillUp; i++) {
      newRow.add(CellFactory.getCell(0));
    }


    if (row.equals(newRow)) {
      return newRow;
    } else {
      return doCrush(newRow);
    }
  }

  private Field spawnRandom(Field field) {
    return null;
  }



}
