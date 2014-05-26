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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PlayingFieldTest {

  private PlayingField playingField;

  @Before
  public void setUp() throws Exception {

    playingField = new PlayingField();

  }

  @Test
  public void testMove() throws Exception {

  }

  @Test
  public void testCrushEmpty() throws Exception {

    List<Cell> row = IntStream.range(0, Field.size)
      .collect(ArrayList::new, (list, i) -> list.add(CellFactory.getCell(0)), ArrayList::addAll);

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(row)));

  }

  @Test
  public void testCrushSingleAtBeginning() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(row)));

  }


  @Test
  public void testCrushSingleAt2nd() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(0));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(2));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }

  @Test
  public void testCrush2Separated() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(4));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(2));
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }


  @Test
  public void testCrushMerge2Simple() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(0));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }

  @Test
  public void testCrushMerge2End() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }


  @Test
  public void testCrushMerge2Separated() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(2));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }


  @Test
  public void testCrushMerge3Separated() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(0));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(2));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }


  @Test
  public void testCrushMerge4Separated() throws Exception {

    List<Cell> row = new ArrayList<>();
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));
    row.add(CellFactory.getCell(2));

    List<Cell> target = new ArrayList<>();
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(4));
    target.add(CellFactory.getCell(0));
    target.add(CellFactory.getCell(0));

    List<Cell> result = playingField.crush(row);

    assertThat(result, is(equalTo(target)));

  }
}