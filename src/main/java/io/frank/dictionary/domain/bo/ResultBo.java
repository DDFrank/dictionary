package io.frank.dictionary.domain.bo;

import lombok.Data;

/**
 * Package io.frank.dictionary.domain.bo
 * Description: ${todo}
 * author 016039
 * date 2019/1/31上午10:32
 */
@Data
public class ResultBo<T> {
  String msg;
  boolean success;
  T data;

  public ResultBo(boolean success) {
    this.success = success;
  }

  public static ResultBo success() {
    return new ResultBo(true);
  }

}
