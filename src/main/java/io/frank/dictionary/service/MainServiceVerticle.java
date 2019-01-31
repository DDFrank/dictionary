package io.frank.dictionary.service;

import io.frank.common.util.DateUtils;
import io.frank.dictionary.dao.DBVerticle;
import io.frank.dictionary.domain.bo.ExplainBo;
import io.frank.dictionary.domain.bo.WordBo;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;

import java.util.List;
import java.util.Objects;

/**
 * Package io.frank.dictionary.service
 * Description: ${todo}
 * author 016039
 * date 2019/1/26下午12:57
 */
public class MainServiceVerticle extends AbstractVerticle {
  private SQLClient sqlClient;
  private EventBus eventBus;

  private static final String WORD_INSERT = "INSERT INTO word (value, pronunciation, comment, update_time, create_time) VALUES (?, ? ,?, ?, ?)";
  private static final String EXPLAIN_INSERT = "INSERT INTO word_explain (value, example, word_id, update_time, create_time) VALUES (?, ? ,?, ?, ?)";

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    eventBus = vertx.eventBus();
    this.sqlClient = JDBCClient.createShared(vertx, new JsonObject(), DBVerticle.DEFAULT_DATA_SOURCE_NAME);
    if (Objects.isNull(this.sqlClient)) {
      startFuture.fail("DB组件未初始化完成，无法进行 service 的初始化");
    } else {
      // 注册 eventBus
      registerEventOnBus();
      startFuture.complete();
    }
  }

  /**
   * 存储单词
   * <p>
   * JinJunLiang
   * 2019/1/28 下午9:15
   */
  private void saveWord(JsonObject jsonObject) {
    WordBo wordBo = new WordBo(jsonObject);
    JsonArray params = new JsonArray();
    params
      .add(wordBo.getValue())
      .add(wordBo.getPronunciation())
      .add(wordBo.getComment())
      .add(DateUtils.getNowString())
      .add(DateUtils.getNowString());
    final List<ExplainBo> explainBoList = wordBo.getExplainBoList();

    sqlClient.updateWithParams(WORD_INSERT, params, ar -> {
      if (ar.succeeded()) {
        JsonArray keys = ar.result().getKeys();
        explainBoList.forEach(explainBo -> {
            final JsonArray explainParams = new JsonArray();
            explainParams
              .add(explainBo.getValue())
              .add(explainBo.getExample())
              .add(keys.getLong(0))
              .add(DateUtils.getNowString())
              .add(DateUtils.getNowString());
            sqlClient.updateWithParams(EXPLAIN_INSERT, explainParams, explainAr -> {
              if (explainAr.succeeded()) {
                System.out.println("更新成功");
              } else {
                System.out.println(explainAr.cause());
              }
            });
          }
        );

      } else {
        System.out.println(ar.cause());
      }
    });
  }


  private void registerEventOnBus() {
    eventBus.consumer("mainService.saveWord", message -> {
      this.saveWord((JsonObject) message.body());
    });
  }

}
