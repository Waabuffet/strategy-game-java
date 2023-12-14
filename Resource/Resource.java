package Resource;

public class Resource {
    private ResourceType type;
    private int qty;

    Resource(ResourceType type, int qty) {
        this.type = type;
        this.qty = qty;
    }

    public ResourceType getType() {
        return this.type;
    }

    public void changeQty(int qty) {
        this.qty += qty;
    }

    public int getQty() {
        return this.qty;
    }
}