package com.tastyfoodwebapplication.utilities;

import java.util.*;
import java.util.function.*;

public class SearchHelper<Entity> {
    List<Entity> entityList;

    public SearchHelper(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public Entity find(Predicate<Entity> predicate) {
        return this.entityList.stream().filter(predicate).findFirst().orElse(null);
    }

    public List<Entity> get(Predicate<Entity> predicate, Comparator<Entity> orderBy) {
        if (orderBy == null) {
            return this.entityList.stream().filter(predicate).toList();
        }

        return this.entityList.stream().filter(predicate).sorted(orderBy).toList();
    }

}
