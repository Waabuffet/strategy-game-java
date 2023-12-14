package Observer;

import Resource.ResourceQuantity;

public interface Notification {
    ResourceQuantity consume();

    ResourceQuantity produce();
}
