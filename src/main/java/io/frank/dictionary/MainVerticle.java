package io.frank.dictionary;

import io.frank.dictionary.dao.DBVerticle;
import io.frank.dictionary.service.MainServiceVerticle;
import io.frank.dictionary.web.HttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

  /**
  * 初始化的入口，主要的逻辑是组装 Verticle
  * author jinjunliang
  * date 2019/1/26 下午12:02
  */
  @Override
  public void start(Future<Void> startFuture) throws Exception {

    /*
    * Verticle 的组装顺序
    *   - DB
    *   - 各个 service
    *   - WEB
    * */
    vertx.deployVerticle(new DBVerticle(), dbResult -> {
      if (dbResult.succeeded()) {
        System.out.println("DB组件初始化完成");
        vertx.deployVerticle(new MainServiceVerticle());

        vertx.deployVerticle(new HttpVerticle(), webResult -> {
          if (webResult.succeeded()) {
            System.out.println("Web组件初始化完成");
            System.out.println("程序启动");
            startFuture.complete();
          } else {
            System.out.println("Web组件初始化失败");
            startFuture.fail(dbResult.cause());
          }
        });
      } else {
        System.out.println("DB组件初始化失败");
        startFuture.fail(dbResult.cause());
      }
    });

  }

}
