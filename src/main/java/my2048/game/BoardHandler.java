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

/**
 * TODO: Comment
 *
 * @author Patrick Sy (patrick.sy@get-it.us)
 */
public class BoardHandler {


  private Board board;

  public BoardHandler() {
    init();
  }

  private void init() {

    board = spawnRandom(spawnRandom(new Board()));

  }

  public Board getBoard() {
    return board;
  }

  public Change move(Direction direction) {

    Change change = doMove(board, direction);

    if (change.isChanged()) {
      board = spawnRandom(change.getBoard());
      change.setBoard(board);
    }

    return change;

  }

  private Change doMove(Board board, Direction direction) {
    Board newBoard = new Board();

    List<Tuple> coordinates = CoordinatesGenerator.coordinates(direction);

    int score = 0;
    boolean changed = false;

    for (int i = 0; i < Board.size; i++) {
      List<Cell> row = new ArrayList<>();
      for (int j = 0; j < Board.size; j++) {
        Tuple t = coordinates.get(i * Board.size + j);
        row.add(board.get(t.x, t.y));
      }

      RowChange rowChange = lineMove(row);
      List<Cell> newRow = rowChange.getRow();
      score += rowChange.getScore();
      changed |= rowChange.isChanged();

      for (int j = 0; j < Board.size; j++) {
        Tuple t = coordinates.get(i * Board.size + j);
        newBoard.set(t.x, t.y, newRow.get(j));
      }
    }

    Change change = new Change();
    change.setChanged(changed);
    change.setScore(score);
    change.setBoard(newBoard);

    return change;
  }

  RowChange lineMove(List<Cell> row) {
    List<Cell> newRow = new ArrayList<>();

    Cell previous = null;
    int score = 0;

    for (Cell cell : row) {

      if (cell.getValue() != 0) {

        if (cell.equals(previous) && cell.getValue() != 0) {
          Cell newCell = CellFactory.getCell(cell.getValue() * 2);
          newRow.add(newCell);
          score += newCell.getValue();

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

    RowChange change = new RowChange();
    change.setRow(newRow);
    change.setScore(score);
    change.setChanged(!row.equals(newRow));

    return change;
  }

  private void fillUp(List<Cell> row) {
    int fillUp = Board.size - row.size();
    for (int i = 0; i < fillUp; i++) {
      row.add(CellFactory.getCell(0));
    }
  }

  private Board spawnRandom(Board board) {

    List<Tuple> emptyFields = new ArrayList<>();

    for (int x = 0; x < Board.size; x++) {
      for (int y = 0; y < Board.size; y++) {
        Cell cell = board.get(x, y);
        if (cell.getValue() == 0) {
          emptyFields.add(new Tuple(x, y));
        }
      }
    }

    Random r = new Random();
    int index = r.nextInt(emptyFields.size());

    int val = (r.nextInt(2) + 1) * 2;

    board.set(emptyFields.get(index).x, emptyFields.get(index).y, CellFactory.getCell(val));

    return board;
  }


  static class RowChange {

    private List<Cell> row;

    private int score = 0;

    private boolean changed = true;

    public boolean isChanged() {
      return changed;
    }

    public void setChanged(boolean changed) {
      this.changed = changed;
    }

    public List<Cell> getRow() {
      return row;
    }

    public void setRow(List<Cell> row) {
      this.row = row;
    }

    public int getScore() {
      return score;
    }

    public void setScore(int score) {
      this.score = score;
    }
  }

}
