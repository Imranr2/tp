package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Model stub that always accepts the order being added.
 */
public class ModelStubAcceptingOrderAdded extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final ArrayList<Ingredient> ingredientsAdded = new ArrayList<>();
    private final ArrayList<Order> ordersAdded = new ArrayList<>();

    @Override
    public void addClient(Client client) {
        requireNonNull(client);
        clientsAdded.add(client);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        ingredientsAdded.add(ingredient);
    }

    @Override
    public void addOrder(Order order) {
        requireNonNull(order);
        ordersAdded.add(order);
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return FXCollections.observableList(clientsAdded);
    }

    public ArrayList<Order> getOrdersAdded() {
        return ordersAdded;
    }

    public ArrayList<Ingredient> getIngredientsAdded() {
        return ingredientsAdded;
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return ordersAdded.stream().anyMatch(order::isSameOrder);
    }

    @Override
    public void minusIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);

        int index = -1;
        Ingredient similarToTarget = null;
        for (int i = 0; i < ingredientsAdded.size(); i++) {
            Ingredient currentIngredient = ingredientsAdded.get(i);
            if (currentIngredient.isSameIngredient(target)) {
                index = i;
                similarToTarget = currentIngredient;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        ingredientsAdded.set(index, new Ingredient(similarToTarget.getName(),
                similarToTarget.getQuantity().minusQuantityBy(target.getQuantity(), multiplier),
                similarToTarget.getUnit()));
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
