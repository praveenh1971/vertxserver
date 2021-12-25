package testproj;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
public class VertxHttpServer extends AbstractVerticle {

	int port;


	public VertxHttpServer(int port) {
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.vertx.core.AbstractVerticle#start(io.vertx.core.Promise)
	 */
	@Override
	public void start(Promise<Void> startPromise) throws Exception {

		Router router = Router.router(vertx);
		
		router.route("/").handler( requestCtx -> {
			requestCtx.response().end("HELLO WORLD");
			
		});
		router.route("/askme").handler( requestCtx -> {
			requestCtx.response().end("HELLO WORLD AGAIN");
			
		});
		router.route("/products").handler( requestCtx -> {
			 
		});
		vertx.createHttpServer().requestHandler(router).listen(port, handler ->{
			
			if(handler.succeeded()){
				startPromise.complete();
				System.out.println("HTTP Server Started");
			}else{
				startPromise.fail("Unable to create HTTP Server at port " + port);
			}
			
		});
	}

		/*
	 * (non-Javadoc)
	 * 
	 * @see io.vertx.core.AbstractVerticle#stop(io.vertx.core.Promise)
	 */
	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		// TODO Auto-generated method stub
		super.stop(stopPromise);
	}

}