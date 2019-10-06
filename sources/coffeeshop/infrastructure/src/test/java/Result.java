import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@DynamoDBTable(tableName = "test")
public class Result {
    @DynamoDBHashKey(attributeName = "SeqNo")
    long seqNo;
}
