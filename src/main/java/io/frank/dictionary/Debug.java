package io.frank.dictionary;

import io.vertx.core.Vertx;

/**
 * Package io.frank.dictionary
 * Description: ${todo}
 * author 016039
 * date 2019/1/26上午11:58
 */
public class Debug {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }
}
