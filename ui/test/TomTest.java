package test;

import Model.Tom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// JUnit tests for Tom Class
public class TomTest extends CellTest {

    @BeforeEach
    public void runBefore() {
        testCell = new Tom(100, 200);
    }

    // test Tom move left from initial
    @Test
    public void testMoveLeft(){
        testCell.move(-SPACE,0);
        assertEquals(80,testCell.x());
        assertEquals(200,testCell.y());
    }

    // test Tom move right from another point
    @Test
    public void testMoveRight(){
        testCell.move(SPACE*3,0);
        assertEquals(160,testCell.x());
        assertEquals(200,testCell.y());
    }

    // test Tom move up from another point
    @Test
    public void testMoveUp(){
        testCell.move(0,-SPACE*5);
        assertEquals(100,testCell.x());
        assertEquals(100,testCell.y());
    }

}
