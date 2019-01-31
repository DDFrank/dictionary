package io.frank.dictionary.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Package io.frank.dictionary.domain.entity
 * Description: ${todo}
 * author 016039
 * date 2019/1/26下午1:13
 */
@Data
public class BaseEntity {
  private Long id;
  private LocalDateTime updateTime;

  private LocalDateTime createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }
}
