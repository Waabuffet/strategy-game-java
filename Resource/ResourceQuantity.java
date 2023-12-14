package Resource;

import java.util.HashMap;

public class ResourceQuantity {
    public HashMap<ResourceType, Integer> all;

    public ResourceQuantity(int food, int wood, int stone, int coal, int iron, int steel, int cement, int lumber,
            int tools) {
        this.initHashMap(food, wood, stone, coal, iron, steel, cement, lumber, tools);
    }

    public ResourceQuantity(int food) {
        this.initHashMap(food, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public ResourceQuantity(int wood, int stone) {
        this.initHashMap(0, wood, stone, 0, 0, 0, 0, 0, 0);
    }

    public ResourceQuantity(int food, int wood, int stone) {
        this.initHashMap(food, wood, stone, 0, 0, 0, 0, 0, 0);
    }

    private void initHashMap(int food, int wood, int stone, int coal, int iron, int steel, int cement, int lumber,
            int tools) {
        this.all = new HashMap<ResourceType, Integer>();
        this.all.put(ResourceType.FOOD, food);
        this.all.put(ResourceType.WOOD, wood);
        this.all.put(ResourceType.STONE, stone);
        this.all.put(ResourceType.COAL, coal);
        this.all.put(ResourceType.IRON, iron);
        this.all.put(ResourceType.STEEL, steel);
        this.all.put(ResourceType.CEMENT, cement);
        this.all.put(ResourceType.LUMBER, lumber);
        this.all.put(ResourceType.TOOLS, tools);
    }

    public void addFoodConsumption(int food) {
        this.all.put(ResourceType.FOOD, this.all.get(ResourceType.FOOD) + food);
    }

    public ResourceQuantity clone() {
        return new ResourceQuantity(this.all.get(ResourceType.FOOD),
                this.all.get(ResourceType.WOOD),
                this.all.get(ResourceType.STONE),
                this.all.get(ResourceType.COAL),
                this.all.get(ResourceType.IRON),
                this.all.get(ResourceType.STEEL),
                this.all.get(ResourceType.CEMENT),
                this.all.get(ResourceType.LUMBER),
                this.all.get(ResourceType.TOOLS));
    }

    @Override
    public String toString() {
        String format = "";
        for (ResourceType type : this.all.keySet()) {
            if (this.all.get(type) > 0) {
                format += this.all.get(type) + " " + type.toString() + ", ";
            }
        }
        if (format.length() == 0) {
            return "";
        }
        return format.substring(0, format.length() - 2);
    }
}
