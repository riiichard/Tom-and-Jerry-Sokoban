package test;

import Model.Cell;
import Model.Jerry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class CellTest {
    protected final int SPACE = 20;
    protected Cell testCell;

    abstract void runBefore();

    // test constructor
    @Test
    public void testConstructor(){
        assertEquals(100,testCell.x());
        assertEquals(200,testCell.y());
    }

    @Test
    public void testNotLeft(){
        assertFalse(testCell.checkNotLeft(testCell));
    }

    @Test
    public void testNotRight(){
        assertFalse(testCell.checkNotLeft(testCell));
    }
}
