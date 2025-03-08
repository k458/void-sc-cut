package com.k458.void_enemies.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "indexes_global")
public class GlobalIndexCounterEntity {
    @Id
    private Long id;

    @Column
    private Long lastIndex;

    @Column(columnDefinition = "bigint[]")
    @ElementCollection
    private List<Long> recycleIndexes;

    public void addRecycleIndex(Long index){
        if (recycleIndexes.contains(index)){
            return;
        }
        recycleIndexes.add(index);
    }

    public Long getGlobalIndexNext(){
        if (recycleIndexes.isEmpty()){
            return lastIndex++;
        }
        Long ret = recycleIndexes.getLast();
        recycleIndexes.removeLast();
        return ret;
    }
}
