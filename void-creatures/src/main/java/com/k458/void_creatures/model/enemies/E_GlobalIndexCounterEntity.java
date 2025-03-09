package com.k458.void_creatures.model.enemies;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "enemies_indexes_global")
public class E_GlobalIndexCounterEntity {
    @Id
    private Long id;

    @Column
    private Long lastIndex;

    @Column(columnDefinition = "bigint[]")
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
