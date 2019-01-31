package io.frank.dictionary.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Package io.frank.dictionary.domain.entity
 * Description: 单词的解释
 * author 016039
 * date 2019/1/26下午1:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Explain extends BaseEntity {
  private String value;
  private String example;
  private Word word;
}
