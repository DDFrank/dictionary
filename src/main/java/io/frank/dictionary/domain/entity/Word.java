package io.frank.dictionary.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Package io.frank.dictionary.domain.entity
 * Description: 单词的 entity
 * author 016039
 * date 2019/1/26下午1:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Word extends BaseEntity {
  private String value;
  private String pronunciation;
  private String comment;
  private List<Explain> explainList;
}
