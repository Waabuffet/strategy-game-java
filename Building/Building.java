package Building;

import Observer.Notification;
import Resource.ResourceQuantity;
import Resource.ResourceType;

public abstract class Building implements Notification {
    private int nbr_habitants;
    private int nbr_travailleurs, nbr_travailleurs_initial, nbr_min_travailleurs, nbr_max_travailleurs;
    private boolean construction_finished;
    private int construction_days_passed;
    private int days_for_construction;
    private ResourceQuantity consumption, production;

    protected Building(int nbr_habitants, int nbr_travailleurs, int days_for_construction, ResourceQuantity consumption,
            ResourceQuantity production) {
        this.nbr_habitants = nbr_habitants;
        this.nbr_travailleurs = nbr_travailleurs;
        this.nbr_min_travailleurs = nbr_travailleurs;
        this.nbr_max_travailleurs = nbr_travailleurs + 10;
        this.nbr_travailleurs_initial = nbr_travailleurs;
        this.days_for_construction = days_for_construction;
        this.consumption = consumption;
        this.production = production;
    }

    public static ResourceQuantity getConstructionPrice() {
        return null;
    }

    public int getNbrPersonnes() {
        return this.nbr_habitants + this.nbr_travailleurs;
    }

    public int getNbrHabitants() {
        return this.nbr_habitants;
    }

    public int getNbrTravailleurs() {
        return this.nbr_travailleurs;
    }

    public ResourceQuantity getConsumption() {
        ResourceQuantity qtyWithFood = null;
        if (this.consumption != null) {
            qtyWithFood = this.consumption.clone();
        } else {
            qtyWithFood = new ResourceQuantity(0);
        }
        qtyWithFood.addFoodConsumption(this.getNbrPersonnes());
        return qtyWithFood;
    }

    public ResourceQuantity getProduction() {
        ResourceQuantity qtyBasedOnTravailleurs = null;
        if (this.production != null) {
            qtyBasedOnTravailleurs = this.production.clone();
            for (ResourceType type : qtyBasedOnTravailleurs.all.keySet()) {
                // * qty res * nbr trav act / nbr trav init
                qtyBasedOnTravailleurs.all.put(type,
                        Math.floorDiv(qtyBasedOnTravailleurs.all.get(type) * this.nbr_travailleurs,
                                this.nbr_travailleurs_initial));
            }
        }
        return qtyBasedOnTravailleurs;
    }

    public boolean getConstructionFinished() {
        return this.construction_finished;
    }

    public int getConstructionProgress(){
        return Math.floorDiv(this.construction_days_passed * 100, this.days_for_construction);
    }

    public boolean manage() {
        if (!this.construction_finished) {
            this.construction_days_passed++;

            if (this.days_for_construction == this.construction_days_passed) {
                System.out.println(this.getBuildingName() + " building construction complete");
                this.construction_finished = true;
            }
        }
        return this.construction_finished;
    }

    private String getBuildingName() {
        return this.getClass().getSimpleName();
    }

    public boolean addTravailleurs() {
        if (this.nbr_travailleurs < this.nbr_max_travailleurs) {
            this.nbr_travailleurs++;
            return true;
        }
        return false;
    }

    public boolean removeTravailleurs() {
        if (this.nbr_travailleurs > this.nbr_min_travailleurs) {
            this.nbr_travailleurs--;
            return true;
        }
        return false;
    }

    public ResourceQuantity consume() {
        ResourceQuantity qtyWithFood = this.getConsumption();
        this.declareConsumptionProduction(qtyWithFood, "consumed");
        return qtyWithFood;
    }

    public ResourceQuantity produce() {
        ResourceQuantity qtyBasedOnTravailleurs = this.getProduction();
        this.declareConsumptionProduction(qtyBasedOnTravailleurs, "produced");
        return qtyBasedOnTravailleurs;
    }

    private void declareConsumptionProduction(ResourceQuantity cp, String cpString) {
        if (cp != null) {
            for (ResourceType type : cp.all.keySet()) {
                if (cp.all.get(type) > 0) {
                    System.out.println(
                            this.getBuildingName() + " " + cpString + " " + cp.all.get(type) + " " + type.toString());
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.getBuildingName();
    }
}
