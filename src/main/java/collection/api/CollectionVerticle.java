package collection.api;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.google.gson.Gson;

import collection.model.PhotosRepo;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

public class CollectionVerticle extends AbstractVerticle {

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_3).build();
	private static DynamoDBMapper mapper = new DynamoDBMapper(client);
	private String contentClient;

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		
		contentClient=FileUtils.readFileToString(
				new File(getClass().getClassLoader().getResource("index.html").getFile()),
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
		System.out.println("sauvegarde");
		try {
			mapper.save(toCreate);
			ctx.response().putHeader("Content-Type", "application/json").end("{\"status\":\"ok\"}");
		} catch (Exception e) {
			outputError(ctx, e);
		}

	}

	private void marquerTelephone(RoutingContext ctx) {

		String id = ctx.request().getParam("id");
		boolean setTel = Boolean.valueOf(ctx.request().getParam("istel"));

		try {
			PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
			toEdit.setTelephone(setTel);
			mapper.save(toEdit);
			ctx.response().putHeader("Content-Type", "application/json").end("{\"status\":\"ok\"}");
		} catch (Exception e) {
			outputError(ctx, e);
		}

	}

	private void marquerServeur(RoutingContext ctx) {

		String id = ctx.request().getParam("id");
		boolean setter = Boolean.valueOf(ctx.request().getParam("isserveur"));

		try {
			PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
			toEdit.setServeur(setter);
			mapper.save(toEdit);
			ctx.response().putHeader("Content-Type", "application/json").end("{\"status\":\"ok\"}");
		} catch (Exception e) {
			outputError(ctx, e);
		}

	}

	private void marquerHubic(RoutingContext ctx) {

		String id = ctx.request().getParam("id");
		boolean setter = Boolean.valueOf(ctx.request().getParam("ishubic"));

		try {
			PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
			toEdit.setHubic(setter);
			mapper.save(toEdit);
			ctx.response().putHeader("Content-Type", "application/json").end("{\"status\":\"ok\"}");
		} catch (Exception e) {
			outputError(ctx, e);
		}

	}

	private void marquerGlacier(RoutingContext ctx) {

		String id = ctx.request().getParam("id");
		boolean setter = Boolean.valueOf(ctx.request().getParam("isglacier"));

		try {
			PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
			toEdit.setGlacier(setter);
			mapper.save(toEdit);
			ctx.response().putHeader("Content-Type", "application/json").end("{\"status\":\"ok\"}");
		} catch (Exception e) {
			outputError(ctx, e);
		}

	}

	private void allRepos(RoutingContext ctx) {
		Gson gson = new Gson();
		List<PhotosRepo> all = mapper.scan(PhotosRepo.class, new DynamoDBScanExpression());
		ctx.response().putHeader("Content-Type", "application/json").end(gson.toJson(all));
	}

	private void outputError(RoutingContext ctx, Exception e) {
		e.printStackTrace();
		ctx.response().putHeader("Content-Type", "application/json")
				.end("{\"status\":\"ko\",\"message\":" + e.getMessage() + "}");
	}
	
	private void generateHtml(RoutingContext ctx) {
		ctx.response().putHeader("content-type", "text/html").end(contentClient);
	}

}
