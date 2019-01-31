package io.frank.dictionary.domain.bo;

import io.frank.common.util.ValidationUtils;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * Package io.frank.dictionary.domain.bo
 * Description:
 * author 016039
 * date 2019/1/28下午9:16
 */
@Data
@Accessors
public class WordBo {
  private String id;
  private String value;
  private String pronunciation;
  private String comment;
  private List<ExplainBo> explainBoList;

  public WordBo(JsonObject jsonObject) {
    if (!ValidationUtils.isEmpty(jsonObject.getString("id"))) {
      this.id = jsonObject.getString("id");
    } else {
      this.id = "";
    }

    if (!ValidationUtils.isEmpty(jsonObject.getString("value"))) {
      this.value = jsonObject.getString("value");
    } else {
      this.value = "";
    }

    if (!ValidationUtils.isEmpty(jsonObject.getString("pronunciation"))) {
      this.pronunciation = jsonObject.getString("pronunciation");
    } else {
      this.pronunciation = "";
    }

    if (!ValidationUtils.isEmpty(jsonObject.getString("comment"))) {
      this.comment = jsonObject.getString("comment");
    } else {
      comment = "";
    }

    if (!jsonObject.getJsonArray("explainBoList").isEmpty()) {
      JsonArray jsonArray = jsonObject.getJsonArray("explainBoList");
      explainBoList = new ArrayList<>(jsonArray.size());
      for (int i = 0, l = jsonArray.size(); i < l; i++) {
        explainBoList.add(new ExplainBo(jsonArray.getJsonObject(i)));
      }
    }

  }
}
