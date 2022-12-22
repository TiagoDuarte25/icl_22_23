package util;

public class Labels {

    private static Labels single_labels = null;

    private int labelCount;

    private Labels() {
        this.labelCount = 1;
    }

    public static Labels getInstance() {
        if (single_labels == null)
            single_labels = new Labels();
        return single_labels;
    }

    public int getNewLabel() { return this.labelCount++; }

}
