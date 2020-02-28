//package test;
//
//import Exceptions.CollisionKeException;
//import Exceptions.CompletedKeException;
//import Exceptions.UnexpectedKeException;
//import Model.Tom;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static java.awt.event.KeyEvent.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class keyPressedTest {
//    Tom testTom;
//
//    @BeforeEach
//    public void runBefore(){
//        testTom = new Tom(100,200);
//    }
//
//    @Test
//    public void testPressLeft(){
//        try {
//            testTom.moveByKeyPressed(VK_LEFT);
//        }
//        catch (CollisionKeException e1) {
//            fail("Should not seen here.");
//        } catch (UnexpectedKeException e1) {
//            fail("Should not seen here.");
//        } catch (CompletedKeException e1) {
//            fail("Should not seen here.");
//        }
//        assertEquals(80,testTom.x());
//        assertEquals(200,testTom.y());
//    }
//
//    @Test
//    public void testPressRight(){
//        try {
//            testTom.moveByKeyPressed(VK_RIGHT);
//        }
//        catch (CollisionKeException e1) {
//            fail("Should not seen here.");
//        } catch (UnexpectedKeException e1) {
//            fail("Should not seen here.");
//        } catch (CompletedKeException e1) {
//            fail("Should not seen here.");
//        }
//        assertEquals(120,testTom.x());
//        assertEquals(200,testTom.y());
//    }
//
//    @Test
//    public void testPressUp(){
//        try {
//            testTom.moveByKeyPressed(VK_UP);
//        }
//        catch (CollisionKeException e1) {
//            fail("Should not seen here.");
//        } catch (UnexpectedKeException e1) {
//            fail("Should not seen here.");
//        } catch (CompletedKeException e1) {
//            fail("Should not seen here.");
//        }
//        assertEquals(100,testTom.x());
//        assertEquals(180,testTom.y());
//    }
//
//    @Test
//    public void testPressDown(){
//        try {
//            testTom.moveByKeyPressed(VK_DOWN);
//        }
//        catch (CollisionKeException e1) {
//            fail("Should not seen here.");
//        } catch (UnexpectedKeException e1) {
//            fail("Should not seen here.");
//        } catch (CompletedKeException e1) {
//            fail("Should not seen here.");
//        }
//        assertEquals(100,testTom.x());
//        assertEquals(220,testTom.y());
//    }
//
//    @Test
//    public void testPressT(){
//        try {
//            testTom.moveByKeyPressed(VK_T);
//            fail("Should not seen here.");
//        }
//        catch (CollisionKeException e1) {
//            fail("Should not seen here.");
//        } catch (UnexpectedKeException e1) {
//            System.out.println("Should seen here.");;
//        } catch (CompletedKeException e1) {
//            fail("Should not seen here.");
//        }
//        assertEquals(100,testTom.x());
//        assertEquals(200,testTom.y());
//    }
//
//    @Test
//    public void testPressAfterCompleted(){
//        testTom.completed = true;
//        try {
//            testTom.moveByKeyPressed(VK_LEFT);
//            fail("Should not seen here.");
//        }
//        catch (CollisionKeException e1) {
//            fail("Should not seen here.");
//        } catch (UnexpectedKeException e1) {
//            fail("Should not seen here.");
//        } catch (CompletedKeException e1) {
//            System.out.println("Should seen here.");;
//        }
//        assertEquals(100,testTom.x());
//        assertEquals(200,testTom.y());
//    }
//}
