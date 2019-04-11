public class ItemCondition {
    public enum condition{

        NEW {
            @Override public String toString() {
                return "NEW";
            } },

        USED{
            @Override public String toString() {
                return "USED";
            } },

        REFURBISHED {
            @Override public String toString() {
                return "REFURBISHED";
            } }
        }
}
