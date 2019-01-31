package io.frank.dictionary.domain.bo;

import io.frank.common.util.ValidationUtils;
import io.vertx.core.json.JsonObject;
import lombok.Data;

/**
 * Package io.frank.dictionary.domain.bo
 * Description: ${todo}
 * author 016039
 * date 2019/1/28下午9:23
 */
@Data
public class ExplainBo {
  private String id;
  private String value;
  private String example;

  public ExplainBo(JsonObject jsonObject) {
    if (!ValidationUtils.isEmpty(jsonObject.getString("value"))) {
      this.value = jsonObject.getString("value");
    } else {
      this.value = "";
    }

    if (!ValidationUtils.isEmpty(jsonObject.getString("example"))) {
      this.example = jsonObject.getString("example");
    } else {
      this.example = "";
    }
  }
}
