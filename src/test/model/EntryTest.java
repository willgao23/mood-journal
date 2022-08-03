package model;

import exceptions.EmptyContentException;
import exceptions.InvalidMoodException;
import exceptions.NegativeIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MoodType.*;
import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    Entry testEntry;

    @BeforeEach
    public void setUp() {
        try {
            testEntry = new Entry("Today, I did nothing!", 111, Happy);
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Today, I did nothing!", testEntry.getContent());
        assertEquals(111, testEntry.getIdNumber());
        assertEquals(Happy, testEntry.getMood());
    }

    @Test
    public void testSetContentOnceNothingThrown() {
        try {
            testEntry.setContent("I worked on my CPSC 210 project!");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        }

        assertEquals("I worked on my CPSC 210 project!", testEntry.getContent());
    }

    @Test
    public void testSetContentMultipleTimesNothingThrown() {
        try {
            testEntry.setContent("I wrote unit tests for my project");
            testEntry.setContent("I finished the method implementation");
        } catch (EmptyContentException e) {
            fail("Unexpected EmptyContentException");
        }

        assertEquals("I finished the method implementation", testEntry.getContent());
    }

    @Test
    public void testSetContentExpectEmptyContentException() {
        try {
            testEntry.setContent("");
            fail("EmptyContentException was not thrown!");
        } catch (EmptyContentException e) {

        }

        assertEquals("Today, I did nothing!", testEntry.getContent());
    }

    @Test
    public void testSetIDOnceNothingThrown() {
        try {
            testEntry.setIdNumber(112);
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        }

        assertEquals(112, testEntry.getIdNumber());
    }

    @Test
    public void testSetIDMultipleTimesNothingThrown() {
        try {
            testEntry.setIdNumber(112);
            testEntry.setIdNumber(113);
        } catch (NegativeIDException e) {
            fail("Unexpected NegativeIDException");
        }

        assertEquals(113, testEntry.getIdNumber());
    }

    @Test
    public void testSetIDExpectNegativeIDException() {
        try {
            testEntry.setIdNumber(-100);
            fail("NegativeIDException was not thrown!");
        } catch (NegativeIDException e) {

        }

        assertEquals(111, testEntry.getIdNumber());
    }

    @Test
    public void testSetMoodOnceNothingThrown() {
        try {
            testEntry.setMood(Sad);
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }

        assertEquals(Sad, testEntry.getMood());
    }

    @Test
    public void testSetMoodMultipleTimesNothingThrown() {
        try {
            testEntry.setMood(Angry);
            testEntry.setMood(Scared);
            testEntry.setMood(Disgusted);
        } catch (InvalidMoodException e) {
            fail("Unexpected InvalidMoodException");
        }

        assertEquals(Disgusted, testEntry.getMood());
    }

    @Test
    public void testSetMoodExpectInvalidMoodException() {
        try {
            testEntry.setMood(Invalid);
            fail("InvalidMoodException was not thrown!");
        } catch (InvalidMoodException e) {

        }

        assertEquals(Happy, testEntry.getMood());
    }
}

