package io.frank.dictionary.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


/**
 * Package io.frank.dictionary.web
 * Description: 提供HTTP服务
 * author 016039
 * date 2019/1/26下午12:01
 */
public class HttpVerticle extends AbstractVerticle {

  private EventBus eventBus;

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    this.eventBus = vertx.eventBus();

    HttpServer httpServer = vertx.createHttpServer();

    Router router = Router.router(vertx);

    // 保存单词
    router.post().handler(BodyHandler.create());
    router.post("/word").handler(this::saveWord);

    httpServer
      .requestHandler(router)
      .listen(8080, ar -> {
        if (ar.succeeded()) {
          startFuture.complete();
        } else {
          startFuture.fail(ar.cause());
        }
      });
  }

  private void saveWord(RoutingContext routingContext) {
    JsonObject jsonObject = routingContext.getBodyAsJson();
    eventBus.send("mainService.saveWord", jsonObject);
    routingContext.response().end("success");
  }
}
