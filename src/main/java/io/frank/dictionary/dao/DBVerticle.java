package io.frank.dictionary.dao;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

/**
 * Package io.frank.dictionary.dao
 * Description: DB组件的初始化，利用 HIKALI 连接池来
 * author 016039
 * date 2019/1/26下午12:01
 */
public class DBVerticle extends AbstractVerticle {

  public static final String DEFAULT_DATA_SOURCE_NAME = "DEFAULT_NAME";

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    JDBCClient.createShared(vertx, initDbConfiguration(), DEFAULT_DATA_SOURCE_NAME);
    startFuture.complete();
  }

  /**
  * 数据库的配置信息
  *
  * author jinjunliang
  * date 2019/1/26 下午12:45
  */
  private JsonObject initDbConfiguration() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.put("url", "jdbc:mysql://127.0.0.1:3306/word")
              .put("driver_class","com.mysql.jdbc.Driver")
              .put("user", "root")
              .put("password", "123456")
    ;
    return jsonObject;
  }


}
