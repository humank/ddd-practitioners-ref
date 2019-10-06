package solid.humank.coffeeshop.infra.repositories;

import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemConfig {
    String tableName;
    GetItemSpec getItemSpec;
}
