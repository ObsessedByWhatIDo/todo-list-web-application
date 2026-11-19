package kz.mortcode.dao;

import kz.mortcode.entity.Record;
import kz.mortcode.entity.RecordStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Learn Programming", RecordStatus.ACTIVE),
                    new Record("Be happy", RecordStatus.DONE),
                    new Record("Workout", RecordStatus.ACTIVE)
            )
    );

    public List<Record> findAllRecords(){
        return new ArrayList<>(records);
    }

    public void saveRecord(Record record) {
        records.add(record);
    }

    public void updateRecordStatus(int id, RecordStatus newStatus){
        for(Record item : records){
            if (item.getId() == id) {
                item.setStatus(newStatus);
                break;
            }
        }
    }

    public void deleteRecord(int id){
        records.removeIf(item -> item.getId() == id);
    }
}
