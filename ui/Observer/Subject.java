package Observer;

import Model.Jerry;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<JerryObserver> observers = new ArrayList<>();

    void addObserver(JerryObserver JerryObserver);

    void notifyObservers(Jerry jerry);
}
