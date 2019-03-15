import io.vertx.core.Vertx.vertx
import verticle.AccountVerticle
import org.slf4j.LoggerFactory


class AccountApp {

  private fun printHelp() {
    log.info("Usage: java app.jar <CONFIG> ")
  }

  fun start(args: Array<String>) {
//    if (args.size != 1) {
//      log.error("Invalid number of arguments: <CONFIG>")
//      printHelp()
//      System.exit(1)
//    } else {
//      val vertx = Vertx.vertx()
//      vertx.deployVerticle(AccountVerticle(args[0]))
//    }
    val vertx = vertx()
    vertx.deployVerticle(AccountVerticle("account-app-1.json"))
  }

  companion object {
    val log = LoggerFactory.getLogger(AccountApp::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
      val app = AccountApp()
      app.start(args)
    }
  }
}
