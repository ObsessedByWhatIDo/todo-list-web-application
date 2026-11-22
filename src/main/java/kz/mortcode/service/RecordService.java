package kz.mortcode.service;

import kz.mortcode.dao.RecordDao;
import kz.mortcode.entity.Record;
import kz.mortcode.entity.RecordStatus;
import kz.mortcode.entity.dto.RecordsContainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public RecordsContainerDto findAllRecords(String filterMode){
        List<Record> records = recordDao.findAllRecords();
        int numberOfDoneRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numberOfActiveRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        if(filterMode == null  || filterMode.isBlank()){
            return new RecordsContainerDto(records, numberOfDoneRecords, numberOfActiveRecords);
        }
        String filterModeInUpperCase = filterMode.toUpperCase(Locale.ROOT);
        List <String> allowedFilterMode = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .toList();
        if(allowedFilterMode.contains(filterModeInUpperCase)){
            List<Record> filteredListRecords = records.stream()
                    .filter(record -> record.getStatus() == RecordStatus.valueOf(filterModeInUpperCase))
                    .toList();
            return new RecordsContainerDto(filteredListRecords, numberOfDoneRecords, numberOfActiveRecords);
        }else{
            return new RecordsContainerDto(records, numberOfDoneRecords, numberOfActiveRecords);
        }
    }

    public void saveRecord(String title){
        if (title != null && !title.isBlank())
            recordDao.saveRecord(new Record(title.trim()));
    }

    public void updateRecordStatus(int id, RecordStatus newStatus){
        recordDao.updateRecordStatus(id, newStatus);
    }
    public void deleteRecord(int id){
        recordDao.deleteRecord(id);
    }
}
