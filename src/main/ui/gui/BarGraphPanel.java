package ui.gui;

import model.Entry;
import model.Journal;
import model.MoodType;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

// Represents the panel where the bar graph is displayed
public class BarGraphPanel extends JPanel implements Observer {
    private static final Color secondary = new Color(198, 226, 233);
    private static final Color highlight = new Color(255, 215, 112);
    private static final int PNL_WIDTH = 600;
    private static final int PNL_HEIGHT = 400;
    private static final String title = "Entry Mood Tracker";
    private static final Font titleFont = new Font("Futura", Font.BOLD, 16);
    private static final Font labelFont = new Font("Futura", Font.PLAIN, 14);
    private int minValue;
    private int maxValue;
    private int titleHeight;
    private int titleY;
    private int titleX;
    private int labelHeight;
    private FontMetrics labelFontMetrics;
    private FontMetrics valueFontMetrics;
    private int labelY;
    private int barX;
    private int barY;
    private int barHeight;
    private int barWidth;
    private int scale;
    private Map<MoodType, Integer> moodsAndValues;

    //EFFECTS: constructs a bar graph panel associated with the given journal
    public BarGraphPanel(Journal j) {
        moodsAndValues = new HashMap<>();
        setBackground(secondary);
        setPreferredSize(new Dimension(PNL_WIDTH, PNL_HEIGHT));

        initializeMoodsAndValues(j);

        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: paints the bar graph, labels, and titles
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (moodsAndValues.isEmpty()) {
            return;
        }

        findMinAndMaxValue();

        barWidth = (PNL_WIDTH / moodsAndValues.size()) / 2;
        initializeTitle(graphics);
        drawTitle(graphics);
        initializeLabels(graphics);

        if (maxValue == minValue) {
            drawEmptyGraph(graphics);
        } else {
            scale = (PNL_HEIGHT - titleHeight - labelHeight) / maxValue;
            drawGraph(graphics);
        }
    }

    //EFFECTS: draws the bars and labels onto the bar graph
    private void drawGraph(Graphics graphics) {
        graphics.setFont(labelFont);
        int barNumber = 0;
        for (Map.Entry<MoodType, Integer> entry : moodsAndValues.entrySet()) {
            calculateBarDimensions(barNumber, entry.getValue());
            drawBar(graphics);

            int labelWidth = labelFontMetrics.stringWidth(String.valueOf(entry.getKey()));
            int valueWidth = valueFontMetrics.stringWidth(String.valueOf(entry.getValue()));
            int labelX = (barNumber * (barWidth * 2)) + (barWidth / 2) + (barWidth - labelWidth) / 2;
            graphics.drawString(String.valueOf(entry.getKey()), labelX, labelY);
            graphics.drawString(String.valueOf(entry.getValue()), (labelX + labelWidth / 2) - (valueWidth / 2),
                    barY - (labelHeight / 4));
            graphics.drawLine(barNumber * (barWidth * 2), barY + barHeight,
                    (barNumber + 1) * (barWidth * 2), barY + barHeight);
            barNumber++;
        }
    }

    //MODIFIES: this
    //EFFECTS: draws the bars and labels for an empty bar graph
    private void drawEmptyGraph(Graphics graphics) {
        graphics.setFont(labelFont);
        int barNumber = 0;
        barX = (barNumber * (barWidth * 2)) + (barWidth / 2);
        barY = PNL_HEIGHT - labelHeight;
        barHeight = 0;
        scale = 0;
        for (Map.Entry<MoodType, Integer> entry : moodsAndValues.entrySet()) {
            drawBar(graphics);

            int labelWidth = labelFontMetrics.stringWidth(String.valueOf(entry.getKey()));
            int valueWidth = valueFontMetrics.stringWidth(String.valueOf(entry.getValue()));
            int labelX = (barNumber * (barWidth * 2)) + (barWidth / 2) + (barWidth - labelWidth) / 2;
            graphics.drawString(String.valueOf(entry.getKey()), labelX, labelY);
            graphics.drawString(String.valueOf(entry.getValue()), (labelX + labelWidth / 2) - (valueWidth / 2),
                    barY - (labelHeight / 4));
            graphics.drawLine(barNumber * (barWidth * 2), barY + barHeight,
                    (barNumber + 1) * (barWidth * 2), barY + barHeight);
            barNumber++;
        }
    }

    //MODIFIES: this
    //EFFECTS: calculates the dimensions of a bar
    private void calculateBarDimensions(int barNumber, int value) {
        barX = (barNumber * (barWidth * 2)) + (barWidth / 2);
        barY = titleHeight;
        barHeight = Math.round(value * scale);
        barY += Math.round((maxValue - value) * scale);
    }

    //EFFECTS: draws a bar onto the panel
    private void drawBar(Graphics graphics) {
        graphics.setColor(highlight);
        graphics.fillRect(barX, barY, barWidth, barHeight);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(barX, barY, barWidth, barHeight);
    }

    //MODIFIES: this
    //EFFECTS: initializes label dimension and location variables
    private void initializeLabels(Graphics graphics) {
        labelFontMetrics = graphics.getFontMetrics(labelFont);
        valueFontMetrics = graphics.getFontMetrics(labelFont);
        labelHeight = labelFontMetrics.getHeight();
        labelY = PNL_HEIGHT - labelFontMetrics.getDescent();
    }

    //EFFECTS: draws the title centered at the top of the panel
    private void drawTitle(Graphics graphics) {
        graphics.setFont(titleFont);
        graphics.drawString(title, titleX, titleY);

    }

    //MODIFIES: this
    //EFFECTS: initializes title dimension and location variables
    private void initializeTitle(Graphics graphics) {
        FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
        int titleWidth = titleFontMetrics.stringWidth(title);
        titleY = titleFontMetrics.getAscent();
        titleX = (PNL_WIDTH - titleWidth) / 2;
        titleHeight = titleFontMetrics.getHeight() + labelHeight;
    }

    //EFFECTS: calculates the minimum and maximum value in moodsAndValues
    private void findMinAndMaxValue() {
        for (Integer value : moodsAndValues.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
            if (value < minValue) {
                minValue = value;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes moodsAndValues with moods and the number of entries
    //associated with that mood
    private void initializeMoodsAndValues(Journal j) {
        List<Entry> entries = j.getEntries();
        int numHappyEntries = countEntriesOfType(entries, MoodType.Happy);
        int numScaredEntries = countEntriesOfType(entries, MoodType.Scared);
        int numAngryEntries = countEntriesOfType(entries, MoodType.Angry);
        int numDisgustedEntries = countEntriesOfType(entries, MoodType.Disgusted);
        int numSadEntries = countEntriesOfType(entries, MoodType.Sad);

        moodsAndValues.put(MoodType.Happy, numHappyEntries);
        moodsAndValues.put(MoodType.Scared, numScaredEntries);
        moodsAndValues.put(MoodType.Angry, numAngryEntries);
        moodsAndValues.put(MoodType.Disgusted, numDisgustedEntries);
        moodsAndValues.put(MoodType.Sad, numSadEntries);
    }

    //EFFECTS: counts the number of entries with the given mood
    private int countEntriesOfType(List<Entry> entries, MoodType mood) {
        int entriesOfMoodType = 0;
        for (Entry e : entries) {
            if (e.getMood().equals(mood)) {
                entriesOfMoodType++;
            }
        }
        return entriesOfMoodType;
    }

    //EFFECTS: updates the bar graph
    @Override
    public void update(Observable o, Object arg) {
        Journal journal = (Journal) arg;
        moodsAndValues.clear();
        initializeMoodsAndValues(journal);
        repaint();
    }

}
