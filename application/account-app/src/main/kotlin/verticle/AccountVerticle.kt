package verticle

import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.config.ConfigStoreOptions
import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import org.slf4j.LoggerFactory

class AccountVerticle(private val configPath: String) : AbstractVerticle() {
  @Throws(Exception::class)
  override fun start() {

    log.info("Starting account service")

    val server = vertx.createHttpServer()

    val router = Router.router(vertx)
    router
      .route()
      .handler(BodyHandler.create())

    val file = ConfigStoreOptions()
      .setType("file")
      .setConfig(JsonObject().put("path", configPath))

    val retriever = ConfigRetriever.create(vertx, ConfigRetrieverOptions().addStore(file))

    retriever.getConfig { conf ->
      server
        .requestHandler(router)
        .listen(conf
          .result()
          .getInteger("port"))
      val json = JsonObject()
        .put("ID", conf
          .result()
          .getString("name"))
        .put("Name", "account-service")
        .put("Address", conf
          .result()
          .getString("host"))
        .put("Port", conf
          .result()
          .getInteger("port"))
        .put("Tags", JsonArray().add("http-endpoint"))

    }

    log.info("Finished to start account service")

  }

  companion object {

    val log = LoggerFactory.getLogger(AccountVerticle::class.java)
  }

}
