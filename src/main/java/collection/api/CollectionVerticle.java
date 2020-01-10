package collection.api;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import collection.model.PhotosRepo;
import collection.services.DaoServices;
import collection.services.impl.DaoServicesImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

public class CollectionVerticle extends AbstractVerticle {

	private DaoServices daoServices;
	private String contentClient;

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		daoServices= new DaoServicesImpl();
		contentClient=IOUtils.toString((getClass().getResourceAsStream("/index.html")),
				Charset.forName("UTF-8"));
		final Router router = Router.router(vertx);

		router.route()
				.handler(CorsHandler.create("*").allowedMethod(io.vertx.core.http.HttpMethod.GET)
						.allowedHeader("Access-Control-Request-Method")
						.allowedHeader("Access-Control-Allow-Credentials").allowedHeader("Access-Control-Allow-Origin")
						.allowedHeader("Access-Control-Allow-Headers").allowedHeader("Content-Type"));

		router.get("/create").handler(this::createPhoto);
		router.get("/telephone").handler(this::marquerTelephone);
		router.get("/serveur").handler(this::marquerServeur);
		router.get("/hubic").handler(this::marquerHubic);
		router.get("/glacier").handler(this::marquerGlacier);
		router.get("/").handler(this::allRepos);
		router.get("/html").handler(this::generateHtml);

		vertx.createHttpServer().requestHandler(router).listen(8888, res -> {
			if (res.succeeded()) {
				System.out.println("Serveur demarre sur le port " + 8888);
				startPromise.complete();
			} else {
				startPromise.fail(res.cause());
			}
		});

	}

	private void createPhoto(RoutingContext ctx) {
		String nomRepo = ctx.request().getParam("nomrepo");
		int annee = Integer.parseInt(ctx.request().getParam("annee"));
		PhotosRepo toCreate = new PhotosRepo(annee, nomRepo);
		try {
			daoServices.insert(toCreate);
			outputOk(ctx);
		} catch (Exception e) {
			outputError(ctx, e);
		}

	}
	
	private void marquerTelephone(RoutingContext ctx) {
		String id = ctx.request().getParam("id");
		boolean setTel = Boolean.valueOf(ctx.request().getParam("istel"));
		try {
			daoServices.setTel(id, setTel);
			outputOk(ctx);
		} catch (Exception e) {
			outputError(ctx, e);
		}
	}	

	private void marquerServeur(RoutingContext ctx) {
		String id = ctx.request().getParam("id");
		boolean setter = Boolean.valueOf(ctx.request().getParam("isserveur"));
		try {
			daoServices.setServeur(id, setter);
			outputOk(ctx);
		} catch (Exception e) {
			outputError(ctx, e);
		}
	}	

	private void marquerHubic(RoutingContext ctx) {
		String id = ctx.request().getParam("id");
		boolean setter = Boolean.valueOf(ctx.request().getParam("ishubic"));
		try {
			daoServices.setHubic(id, setter);
			outputOk(ctx);
		} catch (Exception e) {
			outputError(ctx, e);
		}
	}

	private void marquerGlacier(RoutingContext ctx) {
		String id = ctx.request().getParam("id");
		boolean setter = Boolean.valueOf(ctx.request().getParam("isglacier"));
		try {
			daoServices.setGlacier(id, setter);
			outputOk(ctx);
		} catch (Exception e) {
			outputError(ctx, e);
		}
	}

	private void allRepos(RoutingContext ctx) {
		Gson gson = new Gson();
		List<PhotosRepo> all = daoServices.getAllRepos();
		ctx.response().putHeader("Content-Type", "application/json").end(gson.toJson(all));
	}
	
	private void outputError(RoutingContext ctx, Exception e) {
		e.printStackTrace();
		ctx.response().putHeader("Content-Type", "application/json")
				.end("{\"status\":\"ko\",\"message\":" + e.getMessage() + "}");
	}
	
	private void outputOk(RoutingContext ctx) {
		ctx.response().putHeader("Content-Type", "application/json").end("{\"status\":\"ok\"}");
	}
	
	private void generateHtml(RoutingContext ctx) {
		ctx.response().putHeader("content-type", "text/html").end(contentClient);
	}

}
