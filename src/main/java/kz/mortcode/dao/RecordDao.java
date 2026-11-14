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
}
